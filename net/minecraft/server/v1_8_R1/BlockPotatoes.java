package net.minecraft.server;

public class BlockPotatoes extends BlockCrops {

    public BlockPotatoes() {}

    protected Item j() {
        return Items.POTATO;
    }

    protected Item l() {
        return Items.POTATO;
    }

    public void dropNaturally(World world, BlockPosition blockposition, IBlockData iblockdata, float f, int i) {
        super.dropNaturally(world, blockposition, iblockdata, f, i);
        if (!world.isStatic) {
            if (((Integer) iblockdata.get(BlockPotatoes.AGE)).intValue() >= 7 && world.random.nextInt(50) == 0) {
                a(world, blockposition, new ItemStack(Items.POISONOUS_POTATO));
            }

        }
    }
}
