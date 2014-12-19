package net.minecraft.server;

import com.google.common.collect.Queues;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.local.LocalChannel;
import io.netty.channel.local.LocalServerChannel;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GenericFutureListener;
import java.net.SocketAddress;
import java.util.Queue;
import javax.crypto.SecretKey;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class NetworkManager extends SimpleChannelInboundHandler {

    private static final Logger f = LogManager.getLogger();
    public static final Marker a = MarkerManager.getMarker("NETWORK");
    public static final Marker b = MarkerManager.getMarker("NETWORK_PACKETS", NetworkManager.a);
    public static final AttributeKey c = AttributeKey.valueOf("protocol");
    public static final LazyInitVar d = new LazyInitNioEventLoopGroup2();
    public static final LazyInitVar e = new LocalEventLoopGroup2();
    private final EnumProtocolDirection g;
    private final Queue h = Queues.newConcurrentLinkedQueue();
    private Channel i;
    // Spigot Start
    public SocketAddress j;
    public java.util.UUID spoofedUUID;
    public com.mojang.authlib.properties.Property[] spoofedProfile;
    public boolean preparing = true;
    // Spigot End
    private PacketListener k;
    private IChatBaseComponent l;
    private boolean m;
    private boolean n;

    public NetworkManager(EnumProtocolDirection enumprotocoldirection) {
        this.g = enumprotocoldirection;
    }

    public void channelActive(ChannelHandlerContext channelhandlercontext) throws Exception { // CraftBukkit - added throws
        super.channelActive(channelhandlercontext);
        this.i = channelhandlercontext.channel();
        this.j = this.i.remoteAddress();
        // Spigot Start
        this.preparing = false;
        // Spigot End

        try {
            this.a(EnumProtocol.HANDSHAKING);
        } catch (Throwable throwable) {
            NetworkManager.f.fatal(throwable);
        }

    }

    public void a(EnumProtocol enumprotocol) {
        this.i.attr(NetworkManager.c).set(enumprotocol);
        this.i.config().setAutoRead(true);
        NetworkManager.f.debug("Enabled auto read");
    }

    public void channelInactive(ChannelHandlerContext channelhandlercontext) {
        this.close(new ChatMessage("disconnect.endOfStream", new Object[0]));
    }

    public void exceptionCaught(ChannelHandlerContext channelhandlercontext, Throwable throwable) {
        NetworkManager.f.debug("Disconnecting " + this.getSocketAddress(), throwable);
        this.close(new ChatMessage("disconnect.genericReason", new Object[] { "Internal Exception: " + throwable}));
        if (MinecraftServer.getServer().isDebugging()) throwable.printStackTrace(); // Spigot
    }

    protected void a(ChannelHandlerContext channelhandlercontext, Packet packet) {
        if (this.i.isOpen()) {
            try {
                packet.a(this.k);
            } catch (CancelledPacketHandleException cancelledpackethandleexception) {
                ;
            }
        }

    }

    public void a(PacketListener packetlistener) {
        Validate.notNull(packetlistener, "packetListener", new Object[0]);
        NetworkManager.f.debug("Set listener of {} to {}", new Object[] { this, packetlistener});
        this.k = packetlistener;
    }

    public void handle(Packet packet) {
        if (this.i != null && this.i.isOpen()) {
            this.m();
            this.a(packet, (GenericFutureListener[]) null);
        } else {
            this.h.add(new QueuedPacket(packet, (GenericFutureListener[]) null));
        }

    }

    public void a(Packet packet, GenericFutureListener genericfuturelistener, GenericFutureListener... agenericfuturelistener) {
        if (this.i != null && this.i.isOpen()) {
            this.m();
            this.a(packet, (GenericFutureListener[]) ArrayUtils.add(agenericfuturelistener, 0, genericfuturelistener));
        } else {
            this.h.add(new QueuedPacket(packet, (GenericFutureListener[]) ArrayUtils.add(agenericfuturelistener, 0, genericfuturelistener)));
        }

    }

    private void a(Packet packet, GenericFutureListener[] agenericfuturelistener) {
        EnumProtocol enumprotocol = EnumProtocol.a(packet);
        EnumProtocol enumprotocol1 = (EnumProtocol) this.i.attr(NetworkManager.c).get();

        if (enumprotocol1 != enumprotocol) {
            NetworkManager.f.debug("Disabled auto read");
            this.i.config().setAutoRead(false);
        }

        if (this.i.eventLoop().inEventLoop()) {
            if (enumprotocol != enumprotocol1) {
                this.a(enumprotocol);
            }

            ChannelFuture channelfuture = this.i.writeAndFlush(packet);

            if (agenericfuturelistener != null) {
                channelfuture.addListeners(agenericfuturelistener);
            }

            channelfuture.addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
        } else {
            this.i.eventLoop().execute(new QueuedProtocolSwitch(this, enumprotocol, enumprotocol1, packet, agenericfuturelistener));
        }

    }

    private void m() {
        if (this.i != null && this.i.isOpen()) {
            while (!this.h.isEmpty()) {
                QueuedPacket queuedpacket = (QueuedPacket) this.h.poll();

                this.a(QueuedPacket.a(queuedpacket), QueuedPacket.b(queuedpacket));
            }

        }
    }

    public void a() {
        this.m();
        if (this.k instanceof IUpdatePlayerListBox) {
            ((IUpdatePlayerListBox) this.k).c();
        }

        this.i.flush();
    }

    public SocketAddress getSocketAddress() {
        return this.j;
    }

    public void close(IChatBaseComponent ichatbasecomponent) {
        // Spigot Start
        this.preparing = false;
        this.h.clear();
        // Spigot End
        if (this.i.isOpen()) {
            this.i.close(); // We can't wait as this may be called from an event loop.
            this.l = ichatbasecomponent;
        }

    }

    public boolean c() {
        return this.i instanceof LocalChannel || this.i instanceof LocalServerChannel;
    }

    public void a(SecretKey secretkey) {
        this.m = true;
        this.i.pipeline().addBefore("splitter", "decrypt", new PacketDecrypter(MinecraftEncryption.a(2, secretkey)));
        this.i.pipeline().addBefore("prepender", "encrypt", new PacketEncrypter(MinecraftEncryption.a(1, secretkey)));
    }

    public boolean g() {
        return this.i != null && this.i.isOpen();
    }

    public boolean h() {
        return this.i == null;
    }

    public PacketListener getPacketListener() {
        return this.k;
    }

    public IChatBaseComponent j() {
        return this.l;
    }

    public void k() {
        this.i.config().setAutoRead(false);
    }

    public void a(int i) {
        if (i >= 0) {
            if (this.i.pipeline().get("decompress") instanceof PacketDecompressor) {
                ((PacketDecompressor) this.i.pipeline().get("decompress")).a(i);
            } else {
                this.i.pipeline().addBefore("decoder", "decompress", new PacketDecompressor(i));
            }

            if (this.i.pipeline().get("compress") instanceof PacketCompressor) {
                ((PacketCompressor) this.i.pipeline().get("decompress")).a(i);
            } else {
                this.i.pipeline().addBefore("encoder", "compress", new PacketCompressor(i));
            }
        } else {
            if (this.i.pipeline().get("decompress") instanceof PacketDecompressor) {
                this.i.pipeline().remove("decompress");
            }

            if (this.i.pipeline().get("compress") instanceof PacketCompressor) {
                this.i.pipeline().remove("compress");
            }
        }

    }

    public void l() {
        if (!this.h() && !this.g() && !this.n) {
            this.n = true;
            if (this.j() != null) {
                this.getPacketListener().a(this.j());
            } else if (this.getPacketListener() != null) {
                this.getPacketListener().a(new ChatComponentText("Disconnected"));
            }
        }

    }

    protected void channelRead0(ChannelHandlerContext channelhandlercontext, Object object) {
        this.a(channelhandlercontext, (Packet) object);
    }

    static Channel a(NetworkManager networkmanager) {
        return networkmanager.i;
    }

    // Spigot Start
    public SocketAddress getRawAddress()
    {
        return this.i.remoteAddress();
    }
    // Spigot End
}
