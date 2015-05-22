package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutUpdateSign implements Packet<PacketListenerPlayOut> {

    private World a;
    private BlockPosition b;
    private IChatBaseComponent[] c;

    public PacketPlayOutUpdateSign() {}

    public PacketPlayOutUpdateSign(World world, BlockPosition blockposition, IChatBaseComponent[] aichatbasecomponent) {
        this.a = world;
        this.b = blockposition;
        this.c = new IChatBaseComponent[] { aichatbasecomponent[0], aichatbasecomponent[1], aichatbasecomponent[2], aichatbasecomponent[3]};
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.b = packetdataserializer.c();
        this.c = new IChatBaseComponent[4];

        for (int i = 0; i < 4; ++i) {
            this.c[i] = packetdataserializer.d();
        }

    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a(this.b);

        for (int i = 0; i < 4; ++i) {
            packetdataserializer.a(this.c[i]);
        }

    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
