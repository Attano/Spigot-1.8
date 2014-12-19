package net.minecraft.server;

public class BlockSand extends BlockFalling {

    public static final BlockStateEnum VARIANT = BlockStateEnum.of("variant", EnumSandVariant.class);

    public BlockSand() {
        this.j(this.blockStateList.getBlockData().set(BlockSand.VARIANT, EnumSandVariant.SAND));
    }

    public int getDropData(IBlockData iblockdata) {
        return ((EnumSandVariant) iblockdata.get(BlockSand.VARIANT)).a();
    }

    public MaterialMapColor g(IBlockData iblockdata) {
        return ((EnumSandVariant) iblockdata.get(BlockSand.VARIANT)).c();
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockSand.VARIANT, EnumSandVariant.a(i));
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((EnumSandVariant) iblockdata.get(BlockSand.VARIANT)).a();
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockSand.VARIANT});
    }
}
