package net.minecraft.server;

public class PacketPlayOutOpenSignEditor implements Packet {

    private BlockPosition a;

    public PacketPlayOutOpenSignEditor() {}

    public PacketPlayOutOpenSignEditor(BlockPosition blockposition) {
        this.a = blockposition;
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.c();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.a(this.a);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
