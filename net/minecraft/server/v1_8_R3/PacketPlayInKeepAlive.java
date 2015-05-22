package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInKeepAlive implements Packet<PacketListenerPlayIn> {

    private int a;

    public PacketPlayInKeepAlive() {}

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.e();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.b(this.a);
    }

    public int a() {
        return this.a;
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayIn) packetlistener);
    }
}
