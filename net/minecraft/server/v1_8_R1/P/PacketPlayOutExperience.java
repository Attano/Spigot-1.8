package net.minecraft.server;

public class PacketPlayOutExperience implements Packet {

    private float a;
    private int b;
    private int c;

    public PacketPlayOutExperience() {}

    public PacketPlayOutExperience(float f, int i, int j) {
        this.a = f;
        this.b = i;
        this.c = j;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readFloat();
        this.c = packetdataserializer.e();
        this.b = packetdataserializer.e();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeFloat(this.a);
        packetdataserializer.b(this.c);
        packetdataserializer.b(this.b);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
