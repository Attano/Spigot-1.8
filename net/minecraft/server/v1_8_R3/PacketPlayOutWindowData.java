package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutWindowData implements Packet<PacketListenerPlayOut> {

    private int a;
    private int b;
    private int c;

    public PacketPlayOutWindowData() {}

    public PacketPlayOutWindowData(int i, int j, int k) {
        this.a = i;
        this.b = j;
        this.c = k;
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.readUnsignedByte();
        this.b = packetdataserializer.readShort();
        this.c = packetdataserializer.readShort();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.writeByte(this.a);
        packetdataserializer.writeShort(this.b);
        packetdataserializer.writeShort(this.c);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
