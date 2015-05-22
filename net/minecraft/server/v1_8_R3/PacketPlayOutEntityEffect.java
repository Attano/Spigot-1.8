package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutEntityEffect implements Packet<PacketListenerPlayOut> {

    private int a;
    private byte b;
    private byte c;
    private int d;
    private byte e;

    public PacketPlayOutEntityEffect() {}

    public PacketPlayOutEntityEffect(int i, MobEffect mobeffect) {
        this.a = i;
        this.b = (byte) (mobeffect.getEffectId() & 255);
        this.c = (byte) (mobeffect.getAmplifier() & 255);
        if (mobeffect.getDuration() > 32767) {
            this.d = 32767;
        } else {
            this.d = mobeffect.getDuration();
        }

        this.e = (byte) (mobeffect.isShowParticles() ? 1 : 0);
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.e();
        this.b = packetdataserializer.readByte();
        this.c = packetdataserializer.readByte();
        this.d = packetdataserializer.e();
        this.e = packetdataserializer.readByte();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.b(this.a);
        packetdataserializer.writeByte(this.b);
        packetdataserializer.writeByte(this.c);
        packetdataserializer.b(this.d);
        packetdataserializer.writeByte(this.e);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
