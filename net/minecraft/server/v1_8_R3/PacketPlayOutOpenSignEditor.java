package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutOpenSignEditor implements Packet<PacketListenerPlayOut> {

    private BlockPosition a;

    public PacketPlayOutOpenSignEditor() {}

    public PacketPlayOutOpenSignEditor(BlockPosition blockposition) {
        this.a = blockposition;
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.c();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a(this.a);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
