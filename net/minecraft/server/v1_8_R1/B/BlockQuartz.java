package net.minecraft.server;

public class BlockQuartz extends Block {

    public static final BlockStateEnum VARIANT = BlockStateEnum.of("variant", EnumQuartzVariant.class);

    public BlockQuartz() {
        super(Material.STONE);
        this.j(this.blockStateList.getBlockData().set(BlockQuartz.VARIANT, EnumQuartzVariant.DEFAULT));
        this.a(CreativeModeTab.b);
    }

    public IBlockData getPlacedState(World world, BlockPosition blockposition, EnumDirection enumdirection, float f, float f1, float f2, int i, EntityLiving entityliving) {
        if (i == EnumQuartzVariant.LINES_Y.a()) {
            switch (SwitchHelperAxis.a[enumdirection.k().ordinal()]) {
            case 1:
                return this.getBlockData().set(BlockQuartz.VARIANT, EnumQuartzVariant.LINES_Z);

            case 2:
                return this.getBlockData().set(BlockQuartz.VARIANT, EnumQuartzVariant.LINES_X);

            case 3:
            default:
                return this.getBlockData().set(BlockQuartz.VARIANT, EnumQuartzVariant.LINES_Y);
            }
        } else {
            return i == EnumQuartzVariant.CHISELED.a() ? this.getBlockData().set(BlockQuartz.VARIANT, EnumQuartzVariant.CHISELED) : this.getBlockData().set(BlockQuartz.VARIANT, EnumQuartzVariant.DEFAULT);
        }
    }

    public int getDropData(IBlockData iblockdata) {
        EnumQuartzVariant enumquartzvariant = (EnumQuartzVariant) iblockdata.get(BlockQuartz.VARIANT);

        return enumquartzvariant != EnumQuartzVariant.LINES_X && enumquartzvariant != EnumQuartzVariant.LINES_Z ? enumquartzvariant.a() : EnumQuartzVariant.LINES_Y.a();
    }

    protected ItemStack i(IBlockData iblockdata) {
        EnumQuartzVariant enumquartzvariant = (EnumQuartzVariant) iblockdata.get(BlockQuartz.VARIANT);

        return enumquartzvariant != EnumQuartzVariant.LINES_X && enumquartzvariant != EnumQuartzVariant.LINES_Z ? super.i(iblockdata) : new ItemStack(Item.getItemOf(this), 1, EnumQuartzVariant.LINES_Y.a());
    }

    public MaterialMapColor g(IBlockData iblockdata) {
        return MaterialMapColor.p;
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockQuartz.VARIANT, EnumQuartzVariant.a(i));
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((EnumQuartzVariant) iblockdata.get(BlockQuartz.VARIANT)).a();
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockQuartz.VARIANT});
    }
}
