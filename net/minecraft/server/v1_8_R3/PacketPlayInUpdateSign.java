package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInUpdateSign implements Packet<PacketListenerPlayIn> {

    private BlockPosition a;
    private IChatBaseComponent[] b;

    public PacketPlayInUpdateSign() {}

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.c();
        this.b = new IChatBaseComponent[4];

        for (int i = 0; i < 4; ++i) {
            this.b[i] = packetdataserializer.d();
        }

    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a(this.a);

        for (int i = 0; i < 4; ++i) {
            packetdataserializer.a(this.b[i]);
        }

    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public BlockPosition a() {
        return this.a;
    }

    public IChatBaseComponent[] b() {
        return this.b;
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayIn) packetlistener);
    }
}
