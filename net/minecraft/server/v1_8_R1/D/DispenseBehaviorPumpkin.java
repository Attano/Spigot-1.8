package net.minecraft.server;

final class DispenseBehaviorPumpkin extends DispenseBehaviorItem {

    private boolean b = true;

    DispenseBehaviorPumpkin() {}

    protected ItemStack b(ISourceBlock isourceblock, ItemStack itemstack) {
        World world = isourceblock.i();
        BlockPosition blockposition = isourceblock.getBlockPosition().shift(BlockDispenser.b(isourceblock.f()));
        BlockPumpkin blockpumpkin = (BlockPumpkin) Blocks.PUMPKIN;

        if (world.isEmpty(blockposition) && blockpumpkin.d(world, blockposition)) {
            if (!world.isStatic) {
                world.setTypeAndData(blockposition, blockpumpkin.getBlockData(), 3);
            }

            --itemstack.count;
        } else {
            this.b = false;
        }

        return itemstack;
    }

    protected void a(ISourceBlock isourceblock) {
        if (this.b) {
            isourceblock.i().triggerEffect(1000, isourceblock.getBlockPosition(), 0);
        } else {
            isourceblock.i().triggerEffect(1001, isourceblock.getBlockPosition(), 0);
        }

    }
}
