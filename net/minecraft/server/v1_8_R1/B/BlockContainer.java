package net.minecraft.server;

public abstract class BlockContainer extends Block implements IContainer {

    protected BlockContainer(Material material) {
        super(material);
        this.isTileEntity = true;
    }

    public int b() {
        return -1;
    }

    public void remove(World world, BlockPosition blockposition, IBlockData iblockdata) {
        super.remove(world, blockposition, iblockdata);
        world.t(blockposition);
    }

    public boolean a(World world, BlockPosition blockposition, IBlockData iblockdata, int i, int j) {
        super.a(world, blockposition, iblockdata, i, j);
        TileEntity tileentity = world.getTileEntity(blockposition);

        return tileentity == null ? false : tileentity.c(i, j);
    }
}
