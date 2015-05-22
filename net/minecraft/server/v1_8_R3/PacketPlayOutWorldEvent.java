package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutWorldEvent implements Packet<PacketListenerPlayOut> {

    private int a;
    private BlockPosition b;
    private int c;
    private boolean d;

    public PacketPlayOutWorldEvent() {}

    public PacketPlayOutWorldEvent(int i, BlockPosition blockposition, int j, boolean flag) {
        this.a = i;
        this.b = blockposition;
        this.c = j;
        this.d = flag;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.readInt();
        this.b = packetdataserializer.c();
        this.c = packetdataserializer.readInt();
        this.d = packetdataserializer.readBoolean();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.writeInt(this.a);
        packetdataserializer.a(this.b);
        packetdataserializer.writeInt(this.c);
        packetdataserializer.writeBoolean(this.d);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
