package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutTileEntityData implements Packet<PacketListenerPlayOut> {

    private BlockPosition a;
    private int b;
    private NBTTagCompound c;

    public PacketPlayOutTileEntityData() {}

    public PacketPlayOutTileEntityData(BlockPosition blockposition, int i, NBTTagCompound nbttagcompound) {
        this.a = blockposition;
        this.b = i;
        this.c = nbttagcompound;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.c();
        this.b = packetdataserializer.readUnsignedByte();
        this.c = packetdataserializer.h();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
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
