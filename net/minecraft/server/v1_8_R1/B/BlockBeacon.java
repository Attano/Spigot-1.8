package net.minecraft.server;

public class BlockBeacon extends BlockContainer {

    public BlockBeacon() {
        super(Material.SHATTERABLE);
        this.c(3.0F);
        this.a(CreativeModeTab.f);
    }

    public TileEntity a(World world, int i) {
        return new TileEntityBeacon();
    }

    public boolean interact(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman, EnumDirection enumdirection, float f, float f1, float f2) {
        if (world.isStatic) {
            return true;
        } else {
            TileEntity tileentity = world.getTileEntity(blockposition);

            if (tileentity instanceof TileEntityBeacon) {
                entityhuman.openContainer((TileEntityBeacon) tileentity);
            }

            return true;
        }
    }

    public boolean c() {
        return false;
    }

    public boolean d() {
        return false;
    }

    public int b() {
        return 3;
    }

    public void postPlace(World world, BlockPosition blockposition, IBlockData iblockdata, EntityLiving entityliving, ItemStack itemstack) {
        super.postPlace(world, blockposition, iblockdata, entityliving, itemstack);
        if (itemstack.hasName()) {
            TileEntity tileentity = world.getTileEntity(blockposition);

            if (tileentity instanceof TileEntityBeacon) {
                ((TileEntityBeacon) tileentity).a(itemstack.getName());
            }
        }

    }

    public void doPhysics(World world, BlockPosition blockposition, IBlockData iblockdata, Block block) {
        TileEntity tileentity = world.getTileEntity(blockposition);

        if (tileentity instanceof TileEntityBeacon) {
            ((TileEntityBeacon) tileentity).m();
            world.playBlockAction(blockposition, this, 1, 0);
        }

    }

    public static void d(World world, BlockPosition blockposition) {
        HttpUtilities.a.submit(new BlockBeaconInnerClass1(world, blockposition));
    }
}
