package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutHeldItemSlot implements Packet<PacketListenerPlayOut> {

    private int a;

    public PacketPlayOutHeldItemSlot() {}

    public PacketPlayOutHeldItemSlot(int i) {
        this.a = i;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.readByte();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.writeByte(this.a);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
