package net.minecraft.server;

public class BlockRedSandstone extends Block {

    public static final BlockStateEnum TYPE = BlockStateEnum.of("type", EnumRedSandstoneVariant.class);

    public BlockRedSandstone() {
        super(Material.STONE);
        this.j(this.blockStateList.getBlockData().set(BlockRedSandstone.TYPE, EnumRedSandstoneVariant.DEFAULT));
        this.a(CreativeModeTab.b);
    }

    public int getDropData(IBlockData iblockdata) {
        return ((EnumRedSandstoneVariant) iblockdata.get(BlockRedSandstone.TYPE)).a();
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockRedSandstone.TYPE, EnumRedSandstoneVariant.a(i));
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((EnumRedSandstoneVariant) iblockdata.get(BlockRedSandstone.TYPE)).a();
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockRedSandstone.TYPE});
    }
}
