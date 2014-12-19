package net.minecraft.server;

public class BlockStandingBanner extends BlockBanner {

    public BlockStandingBanner() {
        this.j(this.blockStateList.getBlockData().set(BlockStandingBanner.ROTATION, Integer.valueOf(0)));
    }

    public void doPhysics(World world, BlockPosition blockposition, IBlockData iblockdata, Block block) {
        if (!world.getType(blockposition.down()).getBlock().getMaterial().isBuildable()) {
            this.b(world, blockposition, iblockdata, 0);
            world.setAir(blockposition);
        }

        super.doPhysics(world, blockposition, iblockdata, block);
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockStandingBanner.ROTATION, Integer.valueOf(i));
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((Integer) iblockdata.get(BlockStandingBanner.ROTATION)).intValue();
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockStandingBanner.ROTATION});
    }
}
