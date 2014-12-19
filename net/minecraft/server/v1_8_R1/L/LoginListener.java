package net.minecraft.server;

import com.google.common.base.Charsets;
import com.mojang.authlib.GameProfile;
import io.netty.util.concurrent.GenericFutureListener;
import java.security.PrivateKey;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import javax.crypto.SecretKey;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginListener implements PacketLoginInListener, IUpdatePlayerListBox {

    private static final AtomicInteger b = new AtomicInteger(0);
    private static final Logger c = LogManager.getLogger();
    private static final Random random = new Random();
    private final byte[] e = new byte[4];
    private final MinecraftServer server;
    public final NetworkManager networkManager;
    private EnumProtocolState g;
    private int h;
    private GameProfile i;
    private String j;
    private SecretKey loginKey;
    public String hostname = ""; // CraftBukkit - add field

    public LoginListener(MinecraftServer minecraftserver, NetworkManager networkmanager) {
        this.g = EnumProtocolState.HELLO;
        this.j = "";
        this.server = minecraftserver;
        this.networkManager = networkmanager;
        LoginListener.random.nextBytes(this.e);
    }

    public void c() {
        if (this.g == EnumProtocolState.READY_TO_ACCEPT) {
            this.b();
        }

        if (this.h++ == 600) {
            this.disconnect("Took too long to log in");
        }

    }

    public void disconnect(String s) {
        try {
            LoginListener.c.info("Disconnecting " + this.d() + ": " + s);
            ChatComponentText chatcomponenttext = new ChatComponentText(s);

            this.networkManager.handle(new PacketLoginOutDisconnect(chatcomponenttext));
            this.networkManager.close(chatcomponenttext);
        } catch (Exception exception) {
            LoginListener.c.error("Error whilst disconnecting player", exception);
        }

    }

    // Spigot start
    public void initUUID()
    {
        UUID uuid;
        if ( networkManager.spoofedUUID != null )
        {
            uuid = networkManager.spoofedUUID;
        } else
        {
            uuid = UUID.nameUUIDFromBytes( ( "OfflinePlayer:" + this.i.getName() ).getBytes( Charsets.UTF_8 ) );
        }

        this.i = new GameProfile( uuid, this.i.getName() );

        if (networkManager.spoofedProfile != null)
        {
            for ( com.mojang.authlib.properties.Property property : networkManager.spoofedProfile )
            {
                this.i.getProperties().put( property.getName(), property );
            }
        }
    }
    // Spigot end

    public void b() {
        // Spigot start - Moved to initUUID
        /*
        if (!this.i.isComplete()) {
            this.i = this.a(this.i);
        }
        */
        // Spigot end

        // CraftBukkit start - fire PlayerLoginEvent
        EntityPlayer s = this.server.getPlayerList().attemptLogin(this, this.i, hostname);

        if (s == null) {
            // this.disconnect(s);
            // CraftBukkit end
        } else {
            this.g = EnumProtocolState.ACCEPTED;
            if (this.server.aI() >= 0 && !this.networkManager.c()) {
                this.networkManager.a(new PacketLoginOutSetCompression(this.server.aI()), new LoginListenerInnerClass1(this), new GenericFutureListener[0]);
            }

            this.networkManager.handle(new PacketLoginOutSuccess(this.i));
            this.server.getPlayerList().a(this.networkManager, this.server.getPlayerList().processLogin(this.i, s)); // CraftBukkit - add player reference
        }

    }

    public void a(IChatBaseComponent ichatbasecomponent) {
        LoginListener.c.info(this.d() + " lost connection: " + ichatbasecomponent.c());
    }

    public String d() {
        return this.i != null ? this.i.toString() + " (" + this.networkManager.getSocketAddress().toString() + ")" : String.valueOf(this.networkManager.getSocketAddress());
    }

    public void a(PacketLoginInStart packetlogininstart) {
        Validate.validState(this.g == EnumProtocolState.HELLO, "Unexpected hello packet", new Object[0]);
        this.i = packetlogininstart.a();
        if (this.server.getOnlineMode() && !this.networkManager.c()) {
            this.g = EnumProtocolState.KEY;
            this.networkManager.handle(new PacketLoginOutEncryptionBegin(this.j, this.server.P().getPublic(), this.e));
        } else {
            (new ThreadPlayerLookupUUID(this, "User Authenticator #" + b.incrementAndGet())).start(); // Spigot
        }

    }

    public void a(PacketLoginInEncryptionBegin packetlogininencryptionbegin) {
        Validate.validState(this.g == EnumProtocolState.KEY, "Unexpected key packet", new Object[0]);
        PrivateKey privatekey = this.server.P().getPrivate();

        if (!Arrays.equals(this.e, packetlogininencryptionbegin.b(privatekey))) {
            throw new IllegalStateException("Invalid nonce!");
        } else {
            this.loginKey = packetlogininencryptionbegin.a(privatekey);
            this.g = EnumProtocolState.AUTHENTICATING;
            this.networkManager.a(this.loginKey);
            (new ThreadPlayerLookupUUID(this, "User Authenticator #" + LoginListener.b.incrementAndGet())).start();
        }
    }

    protected GameProfile a(GameProfile gameprofile) {
        UUID uuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + gameprofile.getName()).getBytes(Charsets.UTF_8));

        return new GameProfile(uuid, gameprofile.getName());
    }

    static MinecraftServer a(LoginListener loginlistener) {
        return loginlistener.server;
    }

    static GameProfile b(LoginListener loginlistener) {
        return loginlistener.i;
    }

    static String c(LoginListener loginlistener) {
        return loginlistener.j;
    }

    static SecretKey d(LoginListener loginlistener) {
        return loginlistener.loginKey;
    }

    static GameProfile a(LoginListener loginlistener, GameProfile gameprofile) {
        return loginlistener.i = gameprofile;
    }

    static Logger e() {
        return LoginListener.c;
    }

    static EnumProtocolState a(LoginListener loginlistener, EnumProtocolState enumprotocolstate) {
        return loginlistener.g = enumprotocolstate;
    }
}
