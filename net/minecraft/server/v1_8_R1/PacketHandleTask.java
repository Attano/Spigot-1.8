package net.minecraft.server;

final class PacketHandleTask implements Runnable {

    final Packet a;
    final PacketListener b;

    PacketHandleTask(Packet packet, PacketListener packetlistener) {
        this.a = packet;
        this.b = packetlistener;
    }

    public void run() {
        this.a.a(this.b);
    }
}
