package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutRemoveEntityEffect implements Packet<PacketListenerPlayOut> {

    private int a;
    private int b;

    public PacketPlayOutRemoveEntityEffect() {}

    public PacketPlayOutRemoveEntityEffect(int i, MobEffect mobeffect) {
        this.a = i;
        this.b = mobeffect.getEffectId();
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.e();
        this.b = packetdataserializer.readUnsignedByte();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.b(this.a);
        packetdataserializer.writeByte(this.b);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
