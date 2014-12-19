package net.minecraft.server;

public class ItemMinecart extends Item {

    private static final IDispenseBehavior a = new DispenseBehaviorMinecart();
    private final EnumMinecartType b;

    public ItemMinecart(EnumMinecartType enumminecarttype) {
        this.maxStackSize = 1;
        this.b = enumminecarttype;
        this.a(CreativeModeTab.e);
        BlockDispenser.M.a(this, ItemMinecart.a);
    }

    public boolean interactWith(ItemStack itemstack, EntityHuman entityhuman, World world, BlockPosition blockposition, EnumDirection enumdirection, float f, float f1, float f2) {
        IBlockData iblockdata = world.getType(blockposition);

        if (BlockMinecartTrackAbstract.d(iblockdata)) {
            if (!world.isStatic) {
                EnumTrackPosition enumtrackposition = iblockdata.getBlock() instanceof BlockMinecartTrackAbstract ? (EnumTrackPosition) iblockdata.get(((BlockMinecartTrackAbstract) iblockdata.getBlock()).l()) : EnumTrackPosition.NORTH_SOUTH;
                double d0 = 0.0D;

                if (enumtrackposition.c()) {
                    d0 = 0.5D;
                }
                // CraftBukkit start - Minecarts
                org.bukkit.event.player.PlayerInteractEvent event = org.bukkit.craftbukkit.event.CraftEventFactory.callPlayerInteractEvent(entityhuman, org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK, blockposition, enumdirection, itemstack);

                if (event.isCancelled()) {
                    return false;
                }
                // CraftBukkit end

                EntityMinecartAbstract entityminecartabstract = EntityMinecartAbstract.a(world, (double) blockposition.getX() + 0.5D, (double) blockposition.getY() + 0.0625D + d0, (double) blockposition.getZ() + 0.5D, this.b);

                if (itemstack.hasName()) {
                    entityminecartabstract.setCustomName(itemstack.getName());
                }

                world.addEntity(entityminecartabstract);
            }

            --itemstack.count;
            return true;
        } else {
            return false;
        }
    }

    static EnumMinecartType a(ItemMinecart itemminecart) {
        return itemminecart.b;
    }
}
