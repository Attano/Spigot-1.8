package net.minecraft.server;

public class PacketPlayOutBed implements Packet {

    private int a;
    private BlockPosition b;

    public PacketPlayOutBed() {}

    public PacketPlayOutBed(EntityHuman entityhuman, BlockPosition blockposition) {
        this.a = entityhuman.getId();
        this.b = blockposition;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.e();
        this.b = packetdataserializer.c();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.b(this.a);
        packetdataserializer.a(this.b);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
