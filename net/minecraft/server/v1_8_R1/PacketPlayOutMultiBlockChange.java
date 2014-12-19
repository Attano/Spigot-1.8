package net.minecraft.server;

public class PacketPlayOutMultiBlockChange implements Packet {

    private ChunkCoordIntPair a;
    private MultiBlockChangeInfo[] b;

    public PacketPlayOutMultiBlockChange() {}

    public PacketPlayOutMultiBlockChange(int i, short[] ashort, Chunk chunk) {
        this.a = new ChunkCoordIntPair(chunk.locX, chunk.locZ);
        this.b = new MultiBlockChangeInfo[i];

        for (int j = 0; j < this.b.length; ++j) {
            this.b[j] = new MultiBlockChangeInfo(this, ashort[j], chunk);
        }

    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = new ChunkCoordIntPair(packetdataserializer.readInt(), packetdataserializer.readInt());
        this.b = new MultiBlockChangeInfo[packetdataserializer.e()];

        for (int i = 0; i < this.b.length; ++i) {
            this.b[i] = new MultiBlockChangeInfo(this, packetdataserializer.readShort(), (IBlockData) Block.d.a(packetdataserializer.e()));
        }

    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeInt(this.a.x);
        packetdataserializer.writeInt(this.a.z);
        packetdataserializer.b(this.b.length);
        MultiBlockChangeInfo[] amultiblockchangeinfo = this.b;
        int i = amultiblockchangeinfo.length;

        for (int j = 0; j < i; ++j) {
            MultiBlockChangeInfo multiblockchangeinfo = amultiblockchangeinfo[j];

            packetdataserializer.writeShort(multiblockchangeinfo.b());
            packetdataserializer.b(Block.d.b(multiblockchangeinfo.c()));
        }

    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }

    static ChunkCoordIntPair a(PacketPlayOutMultiBlockChange packetplayoutmultiblockchange) {
        return packetplayoutmultiblockchange.a;
    }
}
