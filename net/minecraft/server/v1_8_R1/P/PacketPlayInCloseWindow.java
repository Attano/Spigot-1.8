package net.minecraft.server;

public class PacketPlayInCloseWindow implements Packet {

    private int id;

    public PacketPlayInCloseWindow() {}

    // CraftBukkit start
    @Override
    public void a(PacketListener pl) {
        a((PacketListenerPlayIn) pl);
    }    
    
    public PacketPlayInCloseWindow(int id) {
        this.id = id;
    }
    // CraftBukkit end

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.id = packetdataserializer.readByte();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeByte(this.id);
    }
}
