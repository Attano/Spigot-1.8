package net.minecraft.server;

public class BlockDirt extends Block {

    public static final BlockStateEnum VARIANT = BlockStateEnum.of("variant", EnumDirtVariant.class);
    public static final BlockStateBoolean SNOWY = BlockStateBoolean.of("snowy");

    protected BlockDirt() {
        super(Material.EARTH);
        this.j(this.blockStateList.getBlockData().set(BlockDirt.VARIANT, EnumDirtVariant.DIRT).set(BlockDirt.SNOWY, Boolean.valueOf(false)));
        this.a(CreativeModeTab.b);
    }

    public IBlockData updateState(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
        if (iblockdata.get(BlockDirt.VARIANT) == EnumDirtVariant.PODZOL) {
            Block block = iblockaccess.getType(blockposition.up()).getBlock();

            iblockdata = iblockdata.set(BlockDirt.SNOWY, Boolean.valueOf(block == Blocks.SNOW || block == Blocks.SNOW_LAYER));
        }

        return iblockdata;
    }

    public int getDropData(World world, BlockPosition blockposition) {
        IBlockData iblockdata = world.getType(blockposition);

        return iblockdata.getBlock() != this ? 0 : ((EnumDirtVariant) iblockdata.get(BlockDirt.VARIANT)).a();
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockDirt.VARIANT, EnumDirtVariant.a(i));
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((EnumDirtVariant) iblockdata.get(BlockDirt.VARIANT)).a();
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockDirt.VARIANT, BlockDirt.SNOWY});
    }

    public int getDropData(IBlockData iblockdata) {
        EnumDirtVariant enumdirtvariant = (EnumDirtVariant) iblockdata.get(BlockDirt.VARIANT);

        if (enumdirtvariant == EnumDirtVariant.PODZOL) {
            enumdirtvariant = EnumDirtVariant.DIRT;
        }

        return enumdirtvariant.a();
    }
}
