package net.minecraft.server;

public class PacketStatusInStart implements Packet {

    public PacketStatusInStart() {}

    public void a(PacketDataSerializer packetdataserializer) {}

    public void b(PacketDataSerializer packetdataserializer) {}

    public void a(PacketStatusInListener packetstatusinlistener) {
        packetstatusinlistener.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketStatusInListener) packetlistener);
    }
}
