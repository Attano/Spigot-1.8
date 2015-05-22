package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutUpdateEntityNBT implements Packet<PacketListenerPlayOut> {

    private int a;
    private NBTTagCompound b;

    public PacketPlayOutUpdateEntityNBT() {}

    public PacketPlayOutUpdateEntityNBT(int i, NBTTagCompound nbttagcompound) {
        this.a = i;
        this.b = nbttagcompound;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.e();
        this.b = packetdataserializer.h();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.b(this.a);
        packetdataserializer.a(this.b);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
