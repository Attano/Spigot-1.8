package net.minecraft.server;

public class ItemSnow extends ItemBlock {

    public ItemSnow(Block block) {
        super(block);
        this.setMaxDurability(0);
        this.a(true);
    }

    public boolean interactWith(ItemStack itemstack, EntityHuman entityhuman, World world, BlockPosition blockposition, EnumDirection enumdirection, float f, float f1, float f2) {
        if (itemstack.count == 0) {
            return false;
        } else if (!entityhuman.a(blockposition, enumdirection, itemstack)) {
            return false;
        } else {
            IBlockData iblockdata = world.getType(blockposition);
            Block block = iblockdata.getBlock();

            if (block != this.a && enumdirection != EnumDirection.UP) {
                blockposition = blockposition.shift(enumdirection);
                iblockdata = world.getType(blockposition);
                block = iblockdata.getBlock();
            }

            if (block == this.a) {
                int i = ((Integer) iblockdata.get(BlockSnow.LAYERS)).intValue();

                if (i <= 7) {
                    IBlockData iblockdata1 = iblockdata.set(BlockSnow.LAYERS, Integer.valueOf(i + 1));

                    if (world.b(this.a.a(world, blockposition, iblockdata1)) && world.setTypeAndData(blockposition, iblockdata1, 2)) {
                        world.makeSound((double) ((float) blockposition.getX() + 0.5F), (double) ((float) blockposition.getY() + 0.5F), (double) ((float) blockposition.getZ() + 0.5F), this.a.stepSound.getPlaceSound(), (this.a.stepSound.getVolume1() + 1.0F) / 2.0F, this.a.stepSound.getVolume2() * 0.8F);
                        --itemstack.count;
                        return true;
                    }
                }
            }

            return super.interactWith(itemstack, entityhuman, world, blockposition, enumdirection, f, f1, f2);
        }
    }

    public int filterData(int i) {
        return i;
    }
}
