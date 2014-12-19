package net.minecraft.server;

public class PacketPlayOutSpawnPosition implements Packet {

    public BlockPosition position;

    public PacketPlayOutSpawnPosition() {}

    public PacketPlayOutSpawnPosition(BlockPosition blockposition) {
        this.position = blockposition;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.position = packetdataserializer.c();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.a(this.position);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
