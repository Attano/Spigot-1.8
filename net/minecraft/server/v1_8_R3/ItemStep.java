package net.minecraft.server;

public class ItemStep extends ItemBlock {

    private final BlockStepAbstract b;
    private final BlockStepAbstract c;

    public ItemStep(Block block, BlockStepAbstract blockstepabstract, BlockStepAbstract blockstepabstract1) {
        super(block);
        this.b = blockstepabstract;
        this.c = blockstepabstract1;
        this.setMaxDurability(0);
        this.a(true);
    }

    public int filterData(int i) {
        return i;
    }

    public String e_(ItemStack itemstack) {
        return this.b.b(itemstack.getData());
    }

    public boolean interactWith(ItemStack itemstack, EntityHuman entityhuman, World world, BlockPosition blockposition, EnumDirection enumdirection, float f, float f1, float f2) {
        if (itemstack.count == 0) {
            return false;
        } else if (!entityhuman.a(blockposition.shift(enumdirection), enumdirection, itemstack)) {
            return false;
        } else {
            Object object = this.b.a(itemstack);
            IBlockData iblockdata = world.getType(blockposition);

            if (iblockdata.getBlock() == this.b) {
                IBlockState iblockstate = this.b.n();
                Comparable comparable = iblockdata.get(iblockstate);
                BlockStepAbstract.EnumSlabHalf blockstepabstract_enumslabhalf = (BlockStepAbstract.EnumSlabHalf) iblockdata.get(BlockStepAbstract.HALF);

                if ((enumdirection == EnumDirection.UP && blockstepabstract_enumslabhalf == BlockStepAbstract.EnumSlabHalf.BOTTOM || enumdirection == EnumDirection.DOWN && blockstepabstract_enumslabhalf == BlockStepAbstract.EnumSlabHalf.TOP) && comparable == object) {
                    IBlockData iblockdata1 = this.c.getBlockData().set(iblockstate, comparable);

                    if (world.b(this.c.a(world, blockposition, iblockdata1)) && world.setTypeAndData(blockposition, iblockdata1, 3)) {
                        world.makeSound((double) ((float) blockposition.getX() + 0.5F), (double) ((float) blockposition.getY() + 0.5F), (double) ((float) blockposition.getZ() + 0.5F), this.c.stepSound.getPlaceSound(), (this.c.stepSound.getVolume1() + 1.0F) / 2.0F, this.c.stepSound.getVolume2() * 0.8F);
                        --itemstack.count;
                    }

                    return true;
                }
            }

            return this.a(itemstack, world, blockposition.shift(enumdirection), object) ? true : super.interactWith(itemstack, entityhuman, world, blockposition, enumdirection, f, f1, f2);
        }
    }

    private boolean a(ItemStack itemstack, World world, BlockPosition blockposition, Object object) {
        IBlockData iblockdata = world.getType(blockposition);

        if (iblockdata.getBlock() == this.b) {
            Comparable comparable = iblockdata.get(this.b.n());

            if (comparable == object) {
                IBlockData iblockdata1 = this.c.getBlockData().set(this.b.n(), comparable);

                if (world.b(this.c.a(world, blockposition, iblockdata1)) && world.setTypeAndData(blockposition, iblockdata1, 3)) {
                    world.makeSound((double) ((float) blockposition.getX() + 0.5F), (double) ((float) blockposition.getY() + 0.5F), (double) ((float) blockposition.getZ() + 0.5F), this.c.stepSound.getPlaceSound(), (this.c.stepSound.getVolume1() + 1.0F) / 2.0F, this.c.stepSound.getVolume2() * 0.8F);
                    --itemstack.count;
                }

                return true;
            }
        }

        return false;
    }
}
