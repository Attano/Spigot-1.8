package net.minecraft.server;

public class PacketPlayOutTileEntityData implements Packet {

    private BlockPosition a;
    private int b;
    private NBTTagCompound c;

    public PacketPlayOutTileEntityData() {}

    public PacketPlayOutTileEntityData(BlockPosition blockposition, int i, NBTTagCompound nbttagcompound) {
        this.a = blockposition;
        this.b = i;
        this.c = nbttagcompound;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.c();
        this.b = packetdataserializer.readUnsignedByte();
        this.c = packetdataserializer.h();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.a(this.a);
        packetdataserializer.writeByte((byte) this.b);
        packetdataserializer.a(this.c);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
