package net.minecraft.server;

public class PacketPlayOutUpdateEntityNBT implements Packet {

    private int a;
    private NBTTagCompound b;

    public PacketPlayOutUpdateEntityNBT() {}

    public PacketPlayOutUpdateEntityNBT(int i, NBTTagCompound nbttagcompound) {
        this.a = i;
        this.b = nbttagcompound;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.e();
        this.b = packetdataserializer.h();
    }

    public void b(PacketDataSerializer packetdataserializer) {
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
