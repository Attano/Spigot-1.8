package net.minecraft.server;

import java.util.Random;

public abstract class BlockWoodenStep extends BlockStepAbstract {

    public static final BlockStateEnum VARIANT = BlockStateEnum.of("variant", EnumLogVariant.class);

    public BlockWoodenStep() {
        super(Material.WOOD);
        IBlockData iblockdata = this.blockStateList.getBlockData();

        if (!this.j()) {
            iblockdata = iblockdata.set(BlockWoodenStep.HALF, EnumSlabHalf.BOTTOM);
        }

        this.j(iblockdata.set(BlockWoodenStep.VARIANT, EnumLogVariant.OAK));
        this.a(CreativeModeTab.b);
    }

    public Item getDropType(IBlockData iblockdata, Random random, int i) {
        return Item.getItemOf(Blocks.WOODEN_SLAB);
    }

    public String b(int i) {
        return super.a() + "." + EnumLogVariant.a(i).c();
    }

    public IBlockState l() {
        return BlockWoodenStep.VARIANT;
    }

    public Object a(ItemStack itemstack) {
        return EnumLogVariant.a(itemstack.getData() & 7);
    }

    public IBlockData fromLegacyData(int i) {
        IBlockData iblockdata = this.getBlockData().set(BlockWoodenStep.VARIANT, EnumLogVariant.a(i & 7));

        if (!this.j()) {
            iblockdata = iblockdata.set(BlockWoodenStep.HALF, (i & 8) == 0 ? EnumSlabHalf.BOTTOM : EnumSlabHalf.TOP);
        }

        return iblockdata;
    }

    public int toLegacyData(IBlockData iblockdata) {
        byte b0 = 0;
        int i = b0 | ((EnumLogVariant) iblockdata.get(BlockWoodenStep.VARIANT)).a();

        if (!this.j() && iblockdata.get(BlockWoodenStep.HALF) == EnumSlabHalf.TOP) {
            i |= 8;
        }

        return i;
    }

    protected BlockStateList getStateList() {
        return this.j() ? new BlockStateList(this, new IBlockState[] { BlockWoodenStep.VARIANT}) : new BlockStateList(this, new IBlockState[] { BlockWoodenStep.HALF, BlockWoodenStep.VARIANT});
    }

    public int getDropData(IBlockData iblockdata) {
        return ((EnumLogVariant) iblockdata.get(BlockWoodenStep.VARIANT)).a();
    }
}
