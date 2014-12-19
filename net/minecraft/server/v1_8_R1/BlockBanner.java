package net.minecraft.server;

import com.google.common.base.Predicate;
import java.util.Random;

public class BlockBanner extends BlockContainer {

    public static final BlockStateDirection FACING = BlockStateDirection.of("facing", (Predicate) EnumDirectionLimit.HORIZONTAL);
    public static final BlockStateInteger ROTATION = BlockStateInteger.of("rotation", 0, 15);

    protected BlockBanner() {
        super(Material.WOOD);
        float f = 0.25F;
        float f1 = 1.0F;

        this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
    }

    public AxisAlignedBB a(World world, BlockPosition blockposition, IBlockData iblockdata) {
        return null;
    }

    public boolean d() {
        return false;
    }

    public boolean b(IBlockAccess iblockaccess, BlockPosition blockposition) {
        return true;
    }

    public boolean c() {
        return false;
    }

    public TileEntity a(World world, int i) {
        return new TileEntityBanner();
    }

    public Item getDropType(IBlockData iblockdata, Random random, int i) {
        return Items.BANNER;
    }

    public void dropNaturally(World world, BlockPosition blockposition, IBlockData iblockdata, float f, int i) {
        TileEntity tileentity = world.getTileEntity(blockposition);

        if (tileentity instanceof TileEntityBanner) {
            ItemStack itemstack = new ItemStack(Items.BANNER, 1, ((TileEntityBanner) tileentity).b());
            NBTTagCompound nbttagcompound = new NBTTagCompound();

            tileentity.b(nbttagcompound);
            nbttagcompound.remove("x");
            nbttagcompound.remove("y");
            nbttagcompound.remove("z");
            nbttagcompound.remove("id");
            itemstack.a("BlockEntityTag", (NBTBase) nbttagcompound);
            a(world, blockposition, itemstack);
        } else {
            super.dropNaturally(world, blockposition, iblockdata, f, i);
        }

    }

    public void a(World world, EntityHuman entityhuman, BlockPosition blockposition, IBlockData iblockdata, TileEntity tileentity) {
        if (tileentity instanceof TileEntityBanner) {
            ItemStack itemstack = new ItemStack(Items.BANNER, 1, ((TileEntityBanner) tileentity).b());
            NBTTagCompound nbttagcompound = new NBTTagCompound();

            tileentity.b(nbttagcompound);
            nbttagcompound.remove("x");
            nbttagcompound.remove("y");
            nbttagcompound.remove("z");
            nbttagcompound.remove("id");
            itemstack.a("BlockEntityTag", (NBTBase) nbttagcompound);
            a(world, blockposition, itemstack);
        } else {
            super.a(world, entityhuman, blockposition, iblockdata, (TileEntity) null);
        }

    }
}
