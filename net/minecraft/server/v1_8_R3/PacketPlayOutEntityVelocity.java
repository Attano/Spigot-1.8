package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutEntityVelocity implements Packet<PacketListenerPlayOut> {

    private int a;
    private int b;
    private int c;
    private int d;

    public PacketPlayOutEntityVelocity() {}

    public PacketPlayOutEntityVelocity(Entity entity) {
        this(entity.getId(), entity.motX, entity.motY, entity.motZ);
    }

    public PacketPlayOutEntityVelocity(int i, double d0, double d1, double d2) {
        this.a = i;
        double d3 = 3.9D;

        if (d0 < -d3) {
            d0 = -d3;
        }

        if (d1 < -d3) {
            d1 = -d3;
        }

        if (d2 < -d3) {
            d2 = -d3;
        }

        if (d0 > d3) {
            d0 = d3;
        }

        if (d1 > d3) {
            d1 = d3;
        }

        if (d2 > d3) {
            d2 = d3;
        }

        this.b = (int) (d0 * 8000.0D);
        this.c = (int) (d1 * 8000.0D);
        this.d = (int) (d2 * 8000.0D);
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.e();
        this.b = packetdataserializer.readShort();
        this.c = packetdataserializer.readShort();
        this.d = packetdataserializer.readShort();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.b(this.a);
        packetdataserializer.writeShort(this.b);
        packetdataserializer.writeShort(this.c);
        packetdataserializer.writeShort(this.d);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
