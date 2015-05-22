package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInHeldItemSlot implements Packet<PacketListenerPlayIn> {

    private int itemInHandIndex;

    public PacketPlayInHeldItemSlot() {}

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.itemInHandIndex = packetdataserializer.readShort();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.writeShort(this.itemInHandIndex);
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public int a() {
        return this.itemInHandIndex;
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayIn) packetlistener);
    }
}
