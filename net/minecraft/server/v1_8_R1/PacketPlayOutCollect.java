package net.minecraft.server;

public class PacketPlayOutCollect implements Packet {

    private int a;
    private int b;

    public PacketPlayOutCollect() {}

    public PacketPlayOutCollect(int i, int j) {
        this.a = i;
        this.b = j;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.e();
        this.b = packetdataserializer.e();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.b(this.a);
        packetdataserializer.b(this.b);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
