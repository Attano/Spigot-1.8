package net.minecraft.server;

import java.io.IOException;
import java.util.List;

public class PacketPlayOutSpawnEntityLiving implements Packet<PacketListenerPlayOut> {

    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private byte i;
    private byte j;
    private byte k;
    private DataWatcher l;
    private List<DataWatcher.WatchableObject> m;

    public PacketPlayOutSpawnEntityLiving() {}

    public PacketPlayOutSpawnEntityLiving(EntityLiving entityliving) {
        this.a = entityliving.getId();
        this.b = (byte) EntityTypes.a(entityliving);
        this.c = MathHelper.floor(entityliving.locX * 32.0D);
        this.d = MathHelper.floor(entityliving.locY * 32.0D);
        this.e = MathHelper.floor(entityliving.locZ * 32.0D);
        this.i = (byte) ((int) (entityliving.yaw * 256.0F / 360.0F));
        this.j = (byte) ((int) (entityliving.pitch * 256.0F / 360.0F));
        this.k = (byte) ((int) (entityliving.aK * 256.0F / 360.0F));
        double d0 = 3.9D;
        double d1 = entityliving.motX;
        double d2 = entityliving.motY;
        double d3 = entityliving.motZ;

        if (d1 < -d0) {
            d1 = -d0;
        }

        if (d2 < -d0) {
            d2 = -d0;
        }

        if (d3 < -d0) {
            d3 = -d0;
        }

        if (d1 > d0) {
            d1 = d0;
        }

        if (d2 > d0) {
            d2 = d0;
        }

        if (d3 > d0) {
            d3 = d0;
        }

        this.f = (int) (d1 * 8000.0D);
        this.g = (int) (d2 * 8000.0D);
        this.h = (int) (d3 * 8000.0D);
        this.l = entityliving.getDataWatcher();
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.e();
        this.b = packetdataserializer.readByte() & 255;
        this.c = packetdataserializer.readInt();
        this.d = packetdataserializer.readInt();
        this.e = packetdataserializer.readInt();
        this.i = packetdataserializer.readByte();
        this.j = packetdataserializer.readByte();
        this.k = packetdataserializer.readByte();
        this.f = packetdataserializer.readShort();
        this.g = packetdataserializer.readShort();
        this.h = packetdataserializer.readShort();
        this.m = DataWatcher.b(packetdataserializer);
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.b(this.a);
        packetdataserializer.writeByte(this.b & 255);
        packetdataserializer.writeInt(this.c);
        packetdataserializer.writeInt(this.d);
        packetdataserializer.writeInt(this.e);
        packetdataserializer.writeByte(this.i);
        packetdataserializer.writeByte(this.j);
        packetdataserializer.writeByte(this.k);
        packetdataserializer.writeShort(this.f);
        packetdataserializer.writeShort(this.g);
        packetdataserializer.writeShort(this.h);
        this.l.a(packetdataserializer);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
