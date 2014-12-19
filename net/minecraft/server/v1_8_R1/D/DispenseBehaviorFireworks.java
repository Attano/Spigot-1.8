package net.minecraft.server;

final class DispenseBehaviorFireworks extends DispenseBehaviorItem {

    DispenseBehaviorFireworks() {}

    public ItemStack b(ISourceBlock isourceblock, ItemStack itemstack) {
        EnumDirection enumdirection = BlockDispenser.b(isourceblock.f());
        double d0 = isourceblock.getX() + (double) enumdirection.getAdjacentX();
        double d1 = (double) ((float) isourceblock.getBlockPosition().getY() + 0.2F);
        double d2 = isourceblock.getZ() + (double) enumdirection.getAdjacentZ();
        EntityFireworks entityfireworks = new EntityFireworks(isourceblock.i(), d0, d1, d2, itemstack);

        isourceblock.i().addEntity(entityfireworks);
        itemstack.a(1);
        return itemstack;
    }

    protected void a(ISourceBlock isourceblock) {
        isourceblock.i().triggerEffect(1002, isourceblock.getBlockPosition(), 0);
    }
}
