package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInWindowClick implements Packet<PacketListenerPlayIn> {

    private int a;
    private int slot;
    private int button;
    private short d;
    private ItemStack item;
    private int shift;

    public PacketPlayInWindowClick() {}

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.readByte();
        this.slot = packetdataserializer.readShort();
        this.button = packetdataserializer.readByte();
        this.d = packetdataserializer.readShort();
        this.shift = packetdataserializer.readByte();
        this.item = packetdataserializer.i();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.writeByte(this.a);
        packetdataserializer.writeShort(this.slot);
        packetdataserializer.writeByte(this.button);
        packetdataserializer.writeShort(this.d);
        packetdataserializer.writeByte(this.shift);
        packetdataserializer.a(this.item);
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.slot;
    }

    public int c() {
        return this.button;
    }

    public short d() {
        return this.d;
    }

    public ItemStack e() {
        return this.item;
    }

    public int f() {
        return this.shift;
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayIn) packetlistener);
    }
}
