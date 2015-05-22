package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutTransaction implements Packet<PacketListenerPlayOut> {

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

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.readUnsignedByte();
        this.b = packetdataserializer.readShort();
        this.c = packetdataserializer.readBoolean();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.writeByte(this.a);
        packetdataserializer.writeShort(this.b);
        packetdataserializer.writeBoolean(this.c);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
