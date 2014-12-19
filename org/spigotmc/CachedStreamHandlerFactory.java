package org.spigotmc;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

public class CachedStreamHandlerFactory implements URLStreamHandlerFactory
{
    public static boolean isSet = false;

    @Override
    public URLStreamHandler createURLStreamHandler(String protocol)
    {
        if ( protocol.equals( "http" ) || protocol.equals( "https" ) )
        {
            return new CachedStreamHandler( protocol );
        }
        return null;
    }

    public class CachedStreamHandler extends URLStreamHandler
    {
        private final String protocol;
        private final URLStreamHandler handler;
        private final Method openCon;
        private final Method openConProxy;

        public CachedStreamHandler(String protocol)
        {
            this.protocol = protocol;
            if ( protocol.equals( "http" ) )
            {
                handler = new sun.net.www.protocol.http.Handler();
            } else
            {
                handler = new sun.net.www.protocol.https.Handler();
            }
            try
            {
                openCon = handler.getClass().getDeclaredMethod( "openConnection", URL.class );
                openCon.setAccessible( true );
                openConProxy = handler.getClass().getDeclaredMethod( "openConnection", URL.class, Proxy.class );
                openConProxy.setAccessible( true );
            } catch ( NoSuchMethodException e )
            {
                throw new RuntimeException( e );
            }
        }

        @Override
        protected URLConnection openConnection(URL u) throws IOException
        {
            if ( u.getHost().equals( "api.mojang.com" )
                    || u.getPath().startsWith( "/profiles/minecraft" ) )
            {
                return cachedConnection( u );
            }
            return getDefaultConnection( u );
        }

        @Override
        protected URLConnection openConnection(URL u, Proxy p) throws IOException
        {
            if ( u.getHost().equals( "api.mojang.com" )
                    || u.getPath().startsWith( "/profiles/minecraft" ) )
            {
                return cachedConnection( u, p );
            }
            return getDefaultConnection( u, p );
        }

        private URLConnection cachedConnection(URL u)
        {
            return cachedConnection( u, null );
        }

        private URLConnection cachedConnection(URL u, Proxy p)
        {
            return new CachedMojangAPIConnection( this, u, p );
        }

        public URLConnection getDefaultConnection(URL u)
        {
            try
            {
                return (URLConnection) openCon.invoke( handler, u );
            } catch ( IllegalAccessException e )
            {
                e.printStackTrace();
            } catch ( InvocationTargetException e )
            {
                e.printStackTrace();
            }
            return null;
        }

        public URLConnection getDefaultConnection(URL u, Proxy p)
        {
            try
            {
                return (URLConnection) openConProxy.invoke( handler, u, p );
            } catch ( IllegalAccessException e )
            {
                e.printStackTrace();
            } catch ( InvocationTargetException e )
            {
                e.printStackTrace();
            }
            return null;
        }
    }
}
