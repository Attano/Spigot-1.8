package net.minecraft.server;

public class ItemBed extends Item {

    public ItemBed() {
        this.a(CreativeModeTab.c);
    }

    public boolean interactWith(ItemStack itemstack, EntityHuman entityhuman, World world, BlockPosition blockposition, EnumDirection enumdirection, float f, float f1, float f2) {
        if (world.isStatic) {
            return true;
        } else if (enumdirection != EnumDirection.UP) {
            return false;
        } else {
            IBlockData iblockdata = world.getType(blockposition);
            Block block = iblockdata.getBlock();
            boolean flag = block.f(world, blockposition);

            if (!flag) {
                blockposition = blockposition.up();
            }

            int i = MathHelper.floor((double) (entityhuman.yaw * 4.0F / 360.0F) + 0.5D) & 3;
            EnumDirection enumdirection1 = EnumDirection.fromType2(i);
            BlockPosition blockposition1 = blockposition.shift(enumdirection1);
            boolean flag1 = block.f(world, blockposition1);
            boolean flag2 = world.isEmpty(blockposition) || flag;
            boolean flag3 = world.isEmpty(blockposition1) || flag1;

            if (entityhuman.a(blockposition, enumdirection, itemstack) && entityhuman.a(blockposition1, enumdirection, itemstack)) {
                if (flag2 && flag3 && World.a((IBlockAccess) world, blockposition.down()) && World.a((IBlockAccess) world, blockposition1.down())) {
                    int j = enumdirection1.b();
                    IBlockData iblockdata1 = Blocks.BED.getBlockData().set(BlockBed.OCCUPIED, Boolean.valueOf(false)).set(BlockBed.FACING, enumdirection1).set(BlockBed.PART, EnumBedPart.FOOT);

                    if (world.setTypeAndData(blockposition, iblockdata1, 3)) {
                        IBlockData iblockdata2 = iblockdata1.set(BlockBed.PART, EnumBedPart.HEAD);

                        world.setTypeAndData(blockposition1, iblockdata2, 3);
                    }

                    --itemstack.count;
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}
