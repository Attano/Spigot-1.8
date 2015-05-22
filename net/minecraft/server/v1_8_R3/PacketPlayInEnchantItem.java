package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInEnchantItem implements Packet<PacketListenerPlayIn> {

    private int a;
    private int b;

    public PacketPlayInEnchantItem() {}

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.readByte();
        this.b = packetdataserializer.readByte();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.writeByte(this.a);
        packetdataserializer.writeByte(this.b);
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayIn) packetlistener);
    }
}
