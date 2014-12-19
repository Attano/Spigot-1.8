package net.minecraft.server;

public class BlockSandStone extends Block {

    public static final BlockStateEnum TYPE = BlockStateEnum.of("type", EnumSandstoneVariant.class);

    public BlockSandStone() {
        super(Material.STONE);
        this.j(this.blockStateList.getBlockData().set(BlockSandStone.TYPE, EnumSandstoneVariant.DEFAULT));
        this.a(CreativeModeTab.b);
    }

    public int getDropData(IBlockData iblockdata) {
        return ((EnumSandstoneVariant) iblockdata.get(BlockSandStone.TYPE)).a();
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockSandStone.TYPE, EnumSandstoneVariant.a(i));
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((EnumSandstoneVariant) iblockdata.get(BlockSandStone.TYPE)).a();
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockSandStone.TYPE});
    }
}
