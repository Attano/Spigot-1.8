package net.minecraft.server;

public class MultiBlockChangeInfo {

    private final short b;
    private final IBlockData c;
    final PacketPlayOutMultiBlockChange a;

    public MultiBlockChangeInfo(PacketPlayOutMultiBlockChange packetplayoutmultiblockchange, short short0, IBlockData iblockdata) {
        this.a = packetplayoutmultiblockchange;
        this.b = short0;
        this.c = iblockdata;
    }

    public MultiBlockChangeInfo(PacketPlayOutMultiBlockChange packetplayoutmultiblockchange, short short0, Chunk chunk) {
        this.a = packetplayoutmultiblockchange;
        this.b = short0;
        this.c = chunk.getBlockData(this.a());
    }

    public BlockPosition a() {
        return new BlockPosition(PacketPlayOutMultiBlockChange.a(this.a).a(this.b >> 12 & 15, this.b & 255, this.b >> 8 & 15));
    }

    public short b() {
        return this.b;
    }

    public IBlockData c() {
        return this.c;
    }
}
