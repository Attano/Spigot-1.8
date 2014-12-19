package net.minecraft.server;

import java.util.Random;

class PathfinderGoalEndermanPickupBlock extends PathfinderGoal {

    private EntityEnderman enderman;

    public PathfinderGoalEndermanPickupBlock(EntityEnderman entityenderman) {
        this.enderman = entityenderman;
    }

    public boolean a() {
        return !this.enderman.world.getGameRules().getBoolean("mobGriefing") ? false : (this.enderman.getCarried().getBlock().getMaterial() != Material.AIR ? false : this.enderman.bb().nextInt(20) == 0);
    }

    public void e() {
        Random random = this.enderman.bb();
        World world = this.enderman.world;
        int i = MathHelper.floor(this.enderman.locX - 2.0D + random.nextDouble() * 4.0D);
        int j = MathHelper.floor(this.enderman.locY + random.nextDouble() * 3.0D);
        int k = MathHelper.floor(this.enderman.locZ - 2.0D + random.nextDouble() * 4.0D);
        BlockPosition blockposition = new BlockPosition(i, j, k);
        IBlockData iblockdata = world.getType(blockposition);
        Block block = iblockdata.getBlock();

        if (EntityEnderman.co().contains(block)) {
            // CraftBukkit start - Pickup event
            if (!org.bukkit.craftbukkit.event.CraftEventFactory.callEntityChangeBlockEvent(this.enderman, this.enderman.world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()), org.bukkit.Material.AIR).isCancelled()) {
                this.enderman.setCarried(iblockdata);
                world.setTypeUpdate(blockposition, Blocks.AIR.getBlockData());
            }
            // CraftBukkit end
        }

    }
}
