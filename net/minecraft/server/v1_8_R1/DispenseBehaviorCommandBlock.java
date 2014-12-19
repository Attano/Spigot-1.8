package net.minecraft.server;

final class DispenseBehaviorCommandBlock extends DispenseBehaviorItem {

    DispenseBehaviorCommandBlock() {}

    protected ItemStack b(ISourceBlock isourceblock, ItemStack itemstack) {
        World world = isourceblock.i();
        BlockPosition blockposition = isourceblock.getBlockPosition().shift(BlockDispenser.b(isourceblock.f()));

        if (world.isEmpty(blockposition)) {
            if (!world.isStatic) {
                IBlockData iblockdata = Blocks.COMMAND_BLOCK.getBlockData().set(BlockCommand.TRIGGERED, Boolean.valueOf(false));

                world.setTypeAndData(blockposition, iblockdata, 3);
                ItemBlock.a(world, blockposition, itemstack);
                world.applyPhysics(isourceblock.getBlockPosition(), isourceblock.e());
            }

            --itemstack.count;
        }

        return itemstack;
    }

    protected void a(ISourceBlock isourceblock) {}

    protected void a(ISourceBlock isourceblock, EnumDirection enumdirection) {}
}
