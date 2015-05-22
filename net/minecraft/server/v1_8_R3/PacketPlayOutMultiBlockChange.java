package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutMultiBlockChange implements Packet<PacketListenerPlayOut> {

    private ChunkCoordIntPair a;
    private PacketPlayOutMultiBlockChange.MultiBlockChangeInfo[] b;

    public PacketPlayOutMultiBlockChange() {}

    public PacketPlayOutMultiBlockChange(int i, short[] ashort, Chunk chunk) {
        this.a = new ChunkCoordIntPair(chunk.locX, chunk.locZ);
        this.b = new PacketPlayOutMultiBlockChange.MultiBlockChangeInfo[i];

        for (int j = 0; j < this.b.length; ++j) {
            this.b[j] = new PacketPlayOutMultiBlockChange.MultiBlockChangeInfo(ashort[j], chunk);
        }

    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = new ChunkCoordIntPair(packetdataserializer.readInt(), packetdataserializer.readInt());
        this.b = new PacketPlayOutMultiBlockChange.MultiBlockChangeInfo[packetdataserializer.e()];

        for (int i = 0; i < this.b.length; ++i) {
            this.b[i] = new PacketPlayOutMultiBlockChange.MultiBlockChangeInfo(packetdataserializer.readShort(), (IBlockData) Block.d.a(packetdataserializer.e()));
        }

    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.writeInt(this.a.x);
        packetdataserializer.writeInt(this.a.z);
        packetdataserializer.b(this.b.length);
        PacketPlayOutMultiBlockChange.MultiBlockChangeInfo[] apacketplayoutmultiblockchange_multiblockchangeinfo = this.b;
        int i = apacketplayoutmultiblockchange_multiblockchangeinfo.length;

        for (int j = 0; j < i; ++j) {
            PacketPlayOutMultiBlockChange.MultiBlockChangeInfo packetplayoutmultiblockchange_multiblockchangeinfo = apacketplayoutmultiblockchange_multiblockchangeinfo[j];

            packetdataserializer.writeShort(packetplayoutmultiblockchange_multiblockchangeinfo.b());
            packetdataserializer.b(Block.d.b(packetplayoutmultiblockchange_multiblockchangeinfo.c()));
        }

    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }

    public class MultiBlockChangeInfo {

        private final short b;
        private final IBlockData c;

        public MultiBlockChangeInfo(short short0, IBlockData iblockdata) {
            this.b = short0;
            this.c = iblockdata;
        }

        public MultiBlockChangeInfo(short short0, Chunk chunk) {
            this.b = short0;
            this.c = chunk.getBlockData(this.a());
        }

        public BlockPosition a() {
            return new BlockPosition(PacketPlayOutMultiBlockChange.this.a.a(this.b >> 12 & 15, this.b & 255, this.b >> 8 & 15));
        }

        public short b() {
            return this.b;
        }

        public IBlockData c() {
            return this.c;
        }
    }
}
