package org.spigotmc;

import com.google.common.base.Charsets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class CachedMojangAPIConnection extends HttpURLConnection
{
    private final CachedStreamHandlerFactory.CachedStreamHandler cachedStreamHandler;
    private final Proxy proxy;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private ByteArrayInputStream inputStream;
    private InputStream errorStream;
    private boolean outClosed = false;

    private static final Cache<String, String> cache = CacheBuilder.newBuilder()
            .maximumSize( 10000 )
            .expireAfterAccess( 1, TimeUnit.HOURS )
            .build();

    public CachedMojangAPIConnection(CachedStreamHandlerFactory.CachedStreamHandler cachedStreamHandler, URL url, Proxy proxy)
    {
        super( url );
        this.cachedStreamHandler = cachedStreamHandler;
        this.proxy = proxy;
    }

    @Override
    public void disconnect()
    {

    }

    @Override
    public boolean usingProxy()
    {
        return proxy != null;
    }

    @Override
    public void connect() throws IOException
    {

    }

    @Override
    public InputStream getInputStream() throws IOException
    {
        if ( inputStream == null )
        {
            outClosed = true;
            JsonArray users = new JsonParser().parse( new String( outputStream.toByteArray(), Charsets.UTF_8 ) ).getAsJsonArray();
            StringBuilder reply = new StringBuilder( "[" );
            StringBuilder missingUsers = new StringBuilder( "[" );
            for ( JsonElement user : users )
            {
                String username = user.getAsString().toLowerCase();
                String info = cache.getIfPresent( username );
                if ( info != null )
                {
                    reply.append( info ).append( "," );
                } else
                {
                    missingUsers
                            .append( "\"" )
                            .append( username )
                            .append( "\"" )
                            .append( "," );
                }
            }
            if ( missingUsers.length() > 1 )
            {
                missingUsers.deleteCharAt( missingUsers.length() - 1 ).append( "]" );
            }
            if ( missingUsers.length() > 2 )
            {
                HttpURLConnection connection;
                if ( proxy == null )
                {
                    connection = (HttpURLConnection) cachedStreamHandler.getDefaultConnection( url );
                } else
                {
                    connection = (HttpURLConnection) cachedStreamHandler.getDefaultConnection( url, proxy );
                }
                connection.setRequestMethod( "POST" );
                connection.setRequestProperty( "Content-Type", "application/json" );
                connection.setDoInput( true );
                connection.setDoOutput( true );
                OutputStream out = connection.getOutputStream();
                out.write( missingUsers.toString().getBytes( Charsets.UTF_8 ) );
                out.flush();
                out.close();
                JsonArray newUsers = new JsonParser().parse( new InputStreamReader( connection.getInputStream(), Charsets.UTF_8 ) ).getAsJsonArray();
                for ( JsonElement user : newUsers )
                {
                    JsonObject u = user.getAsJsonObject();
                    cache.put( u.get( "name" ).getAsString(), u.toString() );
                    reply.append( u.toString() ).append( "," );
                }
                responseCode = connection.getResponseCode();
                errorStream = connection.getErrorStream();
            } else
            {
                responseCode = HTTP_OK;
            }
            if ( reply.length() > 1 )
            {
                reply.deleteCharAt( reply.length() - 1 );
            }
            inputStream = new ByteArrayInputStream( reply.append( "]" ).toString().getBytes( Charsets.UTF_8 ) );
        }
        return inputStream;
    }

    @Override
    public InputStream getErrorStream()
    {
        return errorStream;
    }

    @Override
    public OutputStream getOutputStream() throws IOException
    {
        if ( outClosed )
        {
            throw new RuntimeException( "Write after send" );
        }
        return outputStream;
    }
}
