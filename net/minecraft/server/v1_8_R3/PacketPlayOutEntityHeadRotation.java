package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutEntityHeadRotation implements Packet<PacketListenerPlayOut> {

    private int a;
    private byte b;

    public PacketPlayOutEntityHeadRotation() {}

    public PacketPlayOutEntityHeadRotation(Entity entity, byte b0) {
        this.a = entity.getId();
        this.b = b0;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.e();
        this.b = packetdataserializer.readByte();
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
