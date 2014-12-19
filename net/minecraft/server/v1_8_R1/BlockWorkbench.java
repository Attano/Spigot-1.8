package net.minecraft.server;

public class BlockWorkbench extends Block {

    protected BlockWorkbench() {
        super(Material.WOOD);
        this.a(CreativeModeTab.c);
    }

    public boolean interact(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman, EnumDirection enumdirection, float f, float f1, float f2) {
        if (world.isStatic) {
            return true;
        } else {
            entityhuman.openTileEntity(new TileEntityContainerWorkbench(world, blockposition));
            return true;
        }
    }
}
