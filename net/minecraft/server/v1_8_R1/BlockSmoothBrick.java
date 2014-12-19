package net.minecraft.server;

public class BlockSmoothBrick extends Block {

    public static final BlockStateEnum VARIANT = BlockStateEnum.of("variant", EnumStonebrickType.class);
    public static final int b = EnumStonebrickType.DEFAULT.a();
    public static final int M = EnumStonebrickType.MOSSY.a();
    public static final int N = EnumStonebrickType.CRACKED.a();
    public static final int O = EnumStonebrickType.CHISELED.a();

    public BlockSmoothBrick() {
        super(Material.STONE);
        this.j(this.blockStateList.getBlockData().set(BlockSmoothBrick.VARIANT, EnumStonebrickType.DEFAULT));
        this.a(CreativeModeTab.b);
    }

    public int getDropData(IBlockData iblockdata) {
        return ((EnumStonebrickType) iblockdata.get(BlockSmoothBrick.VARIANT)).a();
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockSmoothBrick.VARIANT, EnumStonebrickType.a(i));
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((EnumStonebrickType) iblockdata.get(BlockSmoothBrick.VARIANT)).a();
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockSmoothBrick.VARIANT});
    }
}
