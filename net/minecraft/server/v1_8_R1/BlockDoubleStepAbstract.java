package net.minecraft.server;

import java.util.Random;

public abstract class BlockDoubleStepAbstract extends BlockStepAbstract {

    public static final BlockStateBoolean SEAMLESS = BlockStateBoolean.of("seamless");
    public static final BlockStateEnum VARIANT = BlockStateEnum.of("variant", EnumStoneSlabVariant.class);

    public BlockDoubleStepAbstract() {
        super(Material.STONE);
        IBlockData iblockdata = this.blockStateList.getBlockData();

        if (this.j()) {
            iblockdata = iblockdata.set(BlockDoubleStepAbstract.SEAMLESS, Boolean.valueOf(false));
        } else {
            iblockdata = iblockdata.set(BlockDoubleStepAbstract.HALF, EnumSlabHalf.BOTTOM);
        }

        this.j(iblockdata.set(BlockDoubleStepAbstract.VARIANT, EnumStoneSlabVariant.STONE));
        this.a(CreativeModeTab.b);
    }

    public Item getDropType(IBlockData iblockdata, Random random, int i) {
        return Item.getItemOf(Blocks.STONE_SLAB);
    }

    public String b(int i) {
        return super.a() + "." + EnumStoneSlabVariant.a(i).c();
    }

    public IBlockState l() {
        return BlockDoubleStepAbstract.VARIANT;
    }

    public Object a(ItemStack itemstack) {
        return EnumStoneSlabVariant.a(itemstack.getData() & 7);
    }

    public IBlockData fromLegacyData(int i) {
        IBlockData iblockdata = this.getBlockData().set(BlockDoubleStepAbstract.VARIANT, EnumStoneSlabVariant.a(i & 7));

        if (this.j()) {
            iblockdata = iblockdata.set(BlockDoubleStepAbstract.SEAMLESS, Boolean.valueOf((i & 8) != 0));
        } else {
            iblockdata = iblockdata.set(BlockDoubleStepAbstract.HALF, (i & 8) == 0 ? EnumSlabHalf.BOTTOM : EnumSlabHalf.TOP);
        }

        return iblockdata;
    }

    public int toLegacyData(IBlockData iblockdata) {
        byte b0 = 0;
        int i = b0 | ((EnumStoneSlabVariant) iblockdata.get(BlockDoubleStepAbstract.VARIANT)).a();

        if (this.j()) {
            if (((Boolean) iblockdata.get(BlockDoubleStepAbstract.SEAMLESS)).booleanValue()) {
                i |= 8;
            }
        } else if (iblockdata.get(BlockDoubleStepAbstract.HALF) == EnumSlabHalf.TOP) {
            i |= 8;
        }

        return i;
    }

    protected BlockStateList getStateList() {
        return this.j() ? new BlockStateList(this, new IBlockState[] { BlockDoubleStepAbstract.SEAMLESS, BlockDoubleStepAbstract.VARIANT}) : new BlockStateList(this, new IBlockState[] { BlockDoubleStepAbstract.HALF, BlockDoubleStepAbstract.VARIANT});
    }

    public int getDropData(IBlockData iblockdata) {
        return ((EnumStoneSlabVariant) iblockdata.get(BlockDoubleStepAbstract.VARIANT)).a();
    }
}
