package net.minecraft.server;

public class PacketPlayOutTransaction implements Packet {

    private int a;
    private short b;
    private boolean c;

    public PacketPlayOutTransaction() {}

    public PacketPlayOutTransaction(int i, short short0, boolean flag) {
        this.a = i;
        this.b = short0;
        this.c = flag;
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readUnsignedByte();
        this.b = packetdataserializer.readShort();
        this.c = packetdataserializer.readBoolean();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeByte(this.a);
        packetdataserializer.writeShort(this.b);
        packetdataserializer.writeBoolean(this.c);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
