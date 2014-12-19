package net.minecraft.server;

public class PacketPlayInEnchantItem implements Packet {

    private int a;
    private int b;

    public PacketPlayInEnchantItem() {}

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readByte();
        this.b = packetdataserializer.readByte();
    }

    public void b(PacketDataSerializer packetdataserializer) {
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
