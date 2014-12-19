package net.minecraft.server;

public class BlockWood extends Block {

    public static final BlockStateEnum VARIANT = BlockStateEnum.of("variant", EnumLogVariant.class);

    public BlockWood() {
        super(Material.WOOD);
        this.j(this.blockStateList.getBlockData().set(BlockWood.VARIANT, EnumLogVariant.OAK));
        this.a(CreativeModeTab.b);
    }

    public int getDropData(IBlockData iblockdata) {
        return ((EnumLogVariant) iblockdata.get(BlockWood.VARIANT)).a();
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockWood.VARIANT, EnumLogVariant.a(i));
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((EnumLogVariant) iblockdata.get(BlockWood.VARIANT)).a();
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockWood.VARIANT});
    }
}
