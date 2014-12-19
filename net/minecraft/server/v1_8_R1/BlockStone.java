package net.minecraft.server;

import java.util.Random;

public class BlockStone extends Block {

    public static final BlockStateEnum VARIANT = BlockStateEnum.of("variant", EnumStoneVariant.class);

    public BlockStone() {
        super(Material.STONE);
        this.j(this.blockStateList.getBlockData().set(BlockStone.VARIANT, EnumStoneVariant.STONE));
        this.a(CreativeModeTab.b);
    }

    public Item getDropType(IBlockData iblockdata, Random random, int i) {
        return iblockdata.get(BlockStone.VARIANT) == EnumStoneVariant.STONE ? Item.getItemOf(Blocks.COBBLESTONE) : Item.getItemOf(Blocks.STONE);
    }

    public int getDropData(IBlockData iblockdata) {
        return ((EnumStoneVariant) iblockdata.get(BlockStone.VARIANT)).a();
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockStone.VARIANT, EnumStoneVariant.a(i));
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((EnumStoneVariant) iblockdata.get(BlockStone.VARIANT)).a();
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockStone.VARIANT});
    }
}
