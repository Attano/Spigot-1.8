package net.minecraft.server;

import java.util.Set;

public class PacketPlayOutPosition implements Packet {

    private double a;
    private double b;
    private double c;
    private float d;
    private float e;
    private Set f;

    public PacketPlayOutPosition() {}

    public PacketPlayOutPosition(double d0, double d1, double d2, float f, float f1, Set set) {
        this.a = d0;
        this.b = d1;
        this.c = d2;
        this.d = f;
        this.e = f1;
        this.f = set;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readDouble();
        this.b = packetdataserializer.readDouble();
        this.c = packetdataserializer.readDouble();
        this.d = packetdataserializer.readFloat();
        this.e = packetdataserializer.readFloat();
        this.f = EnumPlayerTeleportFlags.a(packetdataserializer.readUnsignedByte());
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeDouble(this.a);
        packetdataserializer.writeDouble(this.b);
        packetdataserializer.writeDouble(this.c);
        packetdataserializer.writeFloat(this.d);
        packetdataserializer.writeFloat(this.e);
        packetdataserializer.writeByte(EnumPlayerTeleportFlags.a(this.f));
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
