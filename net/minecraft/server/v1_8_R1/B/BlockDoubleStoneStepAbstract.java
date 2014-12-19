package net.minecraft.server;

import java.util.Random;

public abstract class BlockDoubleStoneStepAbstract extends BlockStepAbstract {

    public static final BlockStateBoolean SEAMLESS = BlockStateBoolean.of("seamless");
    public static final BlockStateEnum VARIANT = BlockStateEnum.of("variant", EnumStoneSlab2Variant.class);

    public BlockDoubleStoneStepAbstract() {
        super(Material.STONE);
        IBlockData iblockdata = this.blockStateList.getBlockData();

        if (this.j()) {
            iblockdata = iblockdata.set(BlockDoubleStoneStepAbstract.SEAMLESS, Boolean.valueOf(false));
        } else {
            iblockdata = iblockdata.set(BlockDoubleStoneStepAbstract.HALF, EnumSlabHalf.BOTTOM);
        }

        this.j(iblockdata.set(BlockDoubleStoneStepAbstract.VARIANT, EnumStoneSlab2Variant.RED_SANDSTONE));
        this.a(CreativeModeTab.b);
    }

    public Item getDropType(IBlockData iblockdata, Random random, int i) {
        return Item.getItemOf(Blocks.STONE_SLAB2);
    }

    public String b(int i) {
        return super.a() + "." + EnumStoneSlab2Variant.a(i).c();
    }

    public IBlockState l() {
        return BlockDoubleStoneStepAbstract.VARIANT;
    }

    public Object a(ItemStack itemstack) {
        return EnumStoneSlab2Variant.a(itemstack.getData() & 7);
    }

    public IBlockData fromLegacyData(int i) {
        IBlockData iblockdata = this.getBlockData().set(BlockDoubleStoneStepAbstract.VARIANT, EnumStoneSlab2Variant.a(i & 7));

        if (this.j()) {
            iblockdata = iblockdata.set(BlockDoubleStoneStepAbstract.SEAMLESS, Boolean.valueOf((i & 8) != 0));
        } else {
            iblockdata = iblockdata.set(BlockDoubleStoneStepAbstract.HALF, (i & 8) == 0 ? EnumSlabHalf.BOTTOM : EnumSlabHalf.TOP);
        }

        return iblockdata;
    }

    public int toLegacyData(IBlockData iblockdata) {
        byte b0 = 0;
        int i = b0 | ((EnumStoneSlab2Variant) iblockdata.get(BlockDoubleStoneStepAbstract.VARIANT)).a();

        if (this.j()) {
            if (((Boolean) iblockdata.get(BlockDoubleStoneStepAbstract.SEAMLESS)).booleanValue()) {
                i |= 8;
            }
        } else if (iblockdata.get(BlockDoubleStoneStepAbstract.HALF) == EnumSlabHalf.TOP) {
            i |= 8;
        }

        return i;
    }

    protected BlockStateList getStateList() {
        return this.j() ? new BlockStateList(this, new IBlockState[] { BlockDoubleStoneStepAbstract.SEAMLESS, BlockDoubleStoneStepAbstract.VARIANT}) : new BlockStateList(this, new IBlockState[] { BlockDoubleStoneStepAbstract.HALF, BlockDoubleStoneStepAbstract.VARIANT});
    }

    public int getDropData(IBlockData iblockdata) {
        return ((EnumStoneSlab2Variant) iblockdata.get(BlockDoubleStoneStepAbstract.VARIANT)).a();
    }
}
