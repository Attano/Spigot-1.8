package net.minecraft.server;

import com.google.common.collect.Lists;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GenericFutureListener;
import java.net.InetAddress;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerConnection {

    private static final Logger d = LogManager.getLogger();
    public static final LazyInitVar a = new LazyInitNioEventLoopGroup();
    public static final LazyInitVar b = new LazyInitLocalEventLoopGroup();
    private final MinecraftServer e;
    public volatile boolean c;
    private final List f = Collections.synchronizedList(Lists.newArrayList());
    private final List g = Collections.synchronizedList(Lists.newArrayList());

    public ServerConnection(MinecraftServer minecraftserver) {
        this.e = minecraftserver;
        this.c = true;
    }

    public void a(InetAddress inetaddress, int i) {
        List list = this.f;

        synchronized (this.f) {
            this.f.add(((ServerBootstrap) ((ServerBootstrap) (new ServerBootstrap()).channel(NioServerSocketChannel.class)).childHandler(new ServerConnectionChannel(this)).group((EventLoopGroup) ServerConnection.a.c()).localAddress(inetaddress, i)).bind().syncUninterruptibly());
        }
    }

    public void b() {
        this.c = false;
        Iterator iterator = this.f.iterator();

        while (iterator.hasNext()) {
            ChannelFuture channelfuture = (ChannelFuture) iterator.next();

            try {
                channelfuture.channel().close().sync();
            } catch (InterruptedException interruptedexception) {
                ServerConnection.d.error("Interrupted whilst closing channel");
            }
        }

    }

    public void c() {
        List list = this.g;

        synchronized (this.g) {
            // Spigot Start
            // This prevents players from 'gaming' the server, and strategically relogging to increase their position in the tick order
            if ( org.spigotmc.SpigotConfig.playerShuffle > 0 && MinecraftServer.currentTick % org.spigotmc.SpigotConfig.playerShuffle == 0 )
            {
                Collections.shuffle( this.g );
            }
            // Spigot End
            Iterator iterator = this.g.iterator();

            while (iterator.hasNext()) {
                NetworkManager networkmanager = (NetworkManager) iterator.next();

                if (!networkmanager.h()) {
                    if (!networkmanager.g()) {
                        // Spigot Start
                        // Fix a race condition where a NetworkManager could be unregistered just before connection.
                        if (networkmanager.preparing) continue;
                        // Spigot End
                        iterator.remove();
                        networkmanager.l();
                    } else {
                        try {
                            networkmanager.a();
                        } catch (Exception exception) {
                            if (networkmanager.c()) {
                                CrashReport crashreport = CrashReport.a(exception, "Ticking memory connection");
                                CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Ticking connection");

                                crashreportsystemdetails.a("Connection", (Callable) (new CrashReportNetworkManager(this, networkmanager)));
                                throw new ReportedException(crashreport);
                            }

                            ServerConnection.d.warn("Failed to handle packet for " + networkmanager.getSocketAddress(), exception);
                            ChatComponentText chatcomponenttext = new ChatComponentText("Internal server error");

                            networkmanager.a(new PacketPlayOutKickDisconnect(chatcomponenttext), new NetworkManagerCloseFuture(this, networkmanager, chatcomponenttext), new GenericFutureListener[0]);
                            networkmanager.k();
                        }
                    }
                }
            }

        }
    }

    public MinecraftServer d() {
        return this.e;
    }

    static List a(ServerConnection serverconnection) {
        return serverconnection.g;
    }

    static MinecraftServer b(ServerConnection serverconnection) {
        return serverconnection.e;
    }

}
