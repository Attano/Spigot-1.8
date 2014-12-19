package net.minecraft.server;

public class SourceBlock implements ISourceBlock {

    private final World a;
    private final BlockPosition b;

    public SourceBlock(World world, BlockPosition blockposition) {
        this.a = world;
        this.b = blockposition;
    }

    public World i() {
        return this.a;
    }

    public double getX() {
        return (double) this.b.getX() + 0.5D;
    }

    public double getY() {
        return (double) this.b.getY() + 0.5D;
    }

    public double getZ() {
        return (double) this.b.getZ() + 0.5D;
    }

    public BlockPosition getBlockPosition() {
        return this.b;
    }

    public Block e() {
        return this.a.getType(this.b).getBlock();
    }

    public int f() {
        IBlockData iblockdata = this.a.getType(this.b);

        return iblockdata.getBlock().toLegacyData(iblockdata);
    }

    public TileEntity getTileEntity() {
        return this.a.getTileEntity(this.b);
    }
}
