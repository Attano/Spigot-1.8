package net.minecraft.server;

import io.netty.util.concurrent.GenericFutureListener;

class QueuedPacket {

    private final Packet a;
    private final GenericFutureListener[] b;

    public QueuedPacket(Packet packet, GenericFutureListener... agenericfuturelistener) {
        this.a = packet;
        this.b = agenericfuturelistener;
    }

    public static Packet a(QueuedPacket queuedpacket) {
        return queuedpacket.a;
    }

    public static GenericFutureListener[] b(QueuedPacket queuedpacket) {
        return queuedpacket.b;
    }
}
