package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutWorldParticles implements Packet<PacketListenerPlayOut> {

    private EnumParticle a;
    private float b;
    private float c;
    private float d;
    private float e;
    private float f;
    private float g;
    private float h;
    private int i;
    private boolean j;
    private int[] k;

    public PacketPlayOutWorldParticles() {}

    public PacketPlayOutWorldParticles(EnumParticle enumparticle, boolean flag, float f, float f1, float f2, float f3, float f4, float f5, float f6, int i, int... aint) {
        this.a = enumparticle;
        this.j = flag;
        this.b = f;
        this.c = f1;
        this.d = f2;
        this.e = f3;
        this.f = f4;
        this.g = f5;
        this.h = f6;
        this.i = i;
        this.k = aint;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = EnumParticle.a(packetdataserializer.readInt());
        if (this.a == null) {
            this.a = EnumParticle.BARRIER;
        }

        this.j = packetdataserializer.readBoolean();
        this.b = packetdataserializer.readFloat();
        this.c = packetdataserializer.readFloat();
        this.d = packetdataserializer.readFloat();
        this.e = packetdataserializer.readFloat();
        this.f = packetdataserializer.readFloat();
        this.g = packetdataserializer.readFloat();
        this.h = packetdataserializer.readFloat();
        this.i = packetdataserializer.readInt();
        int i = this.a.d();

        this.k = new int[i];

        for (int j = 0; j < i; ++j) {
            this.k[j] = packetdataserializer.e();
        }

    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.writeInt(this.a.c());
        packetdataserializer.writeBoolean(this.j);
        packetdataserializer.writeFloat(this.b);
        packetdataserializer.writeFloat(this.c);
        packetdataserializer.writeFloat(this.d);
        packetdataserializer.writeFloat(this.e);
        packetdataserializer.writeFloat(this.f);
        packetdataserializer.writeFloat(this.g);
        packetdataserializer.writeFloat(this.h);
        packetdataserializer.writeInt(this.i);
        int i = this.a.d();

        for (int j = 0; j < i; ++j) {
            packetdataserializer.b(this.k[j]);
        }

    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
