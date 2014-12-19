package net.minecraft.server;

public class PacketStatusOutPong implements Packet {

    private long a;

    public PacketStatusOutPong() {}

    public PacketStatusOutPong(long i) {
        this.a = i;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readLong();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeLong(this.a);
    }

    public void a(PacketStatusOutListener packetstatusoutlistener) {
        packetstatusoutlistener.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketStatusOutListener) packetlistener);
    }
}
