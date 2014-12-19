package net.minecraft.server;

public class BlockPrismarine extends Block {

    public static final BlockStateEnum VARIANT = BlockStateEnum.of("variant", EnumPrismarineVariant.class);
    public static final int b = EnumPrismarineVariant.ROUGH.a();
    public static final int M = EnumPrismarineVariant.BRICKS.a();
    public static final int N = EnumPrismarineVariant.DARK.a();

    public BlockPrismarine() {
        super(Material.STONE);
        this.j(this.blockStateList.getBlockData().set(BlockPrismarine.VARIANT, EnumPrismarineVariant.ROUGH));
        this.a(CreativeModeTab.b);
    }

    public int getDropData(IBlockData iblockdata) {
        return ((EnumPrismarineVariant) iblockdata.get(BlockPrismarine.VARIANT)).a();
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((EnumPrismarineVariant) iblockdata.get(BlockPrismarine.VARIANT)).a();
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockPrismarine.VARIANT});
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockPrismarine.VARIANT, EnumPrismarineVariant.a(i));
    }
}
