package net.minecraft.server;

import java.io.IOException;
import java.util.Collection;

public class PacketPlayOutMap implements Packet<PacketListenerPlayOut> {

    private int a;
    private byte b;
    private MapIcon[] c;
    private int d;
    private int e;
    private int f;
    private int g;
    private byte[] h;

    public PacketPlayOutMap() {}

    public PacketPlayOutMap(int i, byte b0, Collection<MapIcon> collection, byte[] abyte, int j, int k, int l, int i1) {
        this.a = i;
        this.b = b0;
        this.c = (MapIcon[]) collection.toArray(new MapIcon[collection.size()]);
        this.d = j;
        this.e = k;
        this.f = l;
        this.g = i1;
        this.h = new byte[l * i1];

        for (int j1 = 0; j1 < l; ++j1) {
            for (int k1 = 0; k1 < i1; ++k1) {
                this.h[j1 + k1 * l] = abyte[j + j1 + (k + k1) * 128];
            }
        }

    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.e();
        this.b = packetdataserializer.readByte();
        this.c = new MapIcon[packetdataserializer.e()];

        for (int i = 0; i < this.c.length; ++i) {
            short short0 = (short) packetdataserializer.readByte();

            this.c[i] = new MapIcon((byte) (short0 >> 4 & 15), packetdataserializer.readByte(), packetdataserializer.readByte(), (byte) (short0 & 15));
        }

        this.f = packetdataserializer.readUnsignedByte();
        if (this.f > 0) {
            this.g = packetdataserializer.readUnsignedByte();
            this.d = packetdataserializer.readUnsignedByte();
            this.e = packetdataserializer.readUnsignedByte();
            this.h = packetdataserializer.a();
        }

    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.b(this.a);
        packetdataserializer.writeByte(this.b);
        packetdataserializer.b(this.c.length);
        MapIcon[] amapicon = this.c;
        int i = amapicon.length;

        for (int j = 0; j < i; ++j) {
            MapIcon mapicon = amapicon[j];

            packetdataserializer.writeByte((mapicon.getType() & 15) << 4 | mapicon.getRotation() & 15);
            packetdataserializer.writeByte(mapicon.getX());
            packetdataserializer.writeByte(mapicon.getY());
        }

        packetdataserializer.writeByte(this.f);
        if (this.f > 0) {
            packetdataserializer.writeByte(this.g);
            packetdataserializer.writeByte(this.d);
            packetdataserializer.writeByte(this.e);
            packetdataserializer.a(this.h);
        }

    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
