package net.minecraft.server;

public class PacketPlayOutBlockBreakAnimation implements Packet {

    private int a;
    private BlockPosition b;
    private int c;

    public PacketPlayOutBlockBreakAnimation() {}

    public PacketPlayOutBlockBreakAnimation(int i, BlockPosition blockposition, int j) {
        this.a = i;
        this.b = blockposition;
        this.c = j;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.e();
        this.b = packetdataserializer.c();
        this.c = packetdataserializer.readUnsignedByte();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.b(this.a);
        packetdataserializer.a(this.b);
        packetdataserializer.writeByte(this.c);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
