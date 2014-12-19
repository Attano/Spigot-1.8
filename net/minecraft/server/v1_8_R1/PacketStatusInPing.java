package net.minecraft.server;

public class PacketStatusInPing implements Packet {

    private long a;

    public PacketStatusInPing() {}

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readLong();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeLong(this.a);
    }

    public void a(PacketStatusInListener packetstatusinlistener) {
        packetstatusinlistener.a(this);
    }

    public long a() {
        return this.a;
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketStatusInListener) packetlistener);
    }
}
