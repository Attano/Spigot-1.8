package net.minecraft.server;

public class PacketPlayOutSetCompression implements Packet {

    private int a;

    public PacketPlayOutSetCompression() {}

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.e();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.b(this.a);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
