package net.minecraft.server;

import java.util.Random;

class PathfinderGoalEndermanPlaceBlock extends PathfinderGoal {

    private EntityEnderman a;

    public PathfinderGoalEndermanPlaceBlock(EntityEnderman entityenderman) {
        this.a = entityenderman;
    }

    public boolean a() {
        return !this.a.world.getGameRules().getBoolean("mobGriefing") ? false : (this.a.getCarried().getBlock().getMaterial() == Material.AIR ? false : this.a.bb().nextInt(2000) == 0);
    }

    public void e() {
        Random random = this.a.bb();
        World world = this.a.world;
        int i = MathHelper.floor(this.a.locX - 1.0D + random.nextDouble() * 2.0D);
        int j = MathHelper.floor(this.a.locY + random.nextDouble() * 2.0D);
        int k = MathHelper.floor(this.a.locZ - 1.0D + random.nextDouble() * 2.0D);
        BlockPosition blockposition = new BlockPosition(i, j, k);
        Block block = world.getType(blockposition).getBlock();
        Block block1 = world.getType(blockposition.down()).getBlock();

        if (this.a(world, blockposition, this.a.getCarried().getBlock(), block, block1)) {
            // CraftBukkit start - Place event
            if (!org.bukkit.craftbukkit.event.CraftEventFactory.callEntityChangeBlockEvent(this.a, blockposition.getX(), blockposition.getY(), blockposition.getZ(), this.a.getCarried().getBlock(), this.a.getCarried().getBlock().toLegacyData(this.a.getCarried())).isCancelled()) {
            world.setTypeAndData(blockposition, this.a.getCarried(), 3);
            this.a.setCarried(Blocks.AIR.getBlockData());
            }
            // CraftBukkit end
        }

    }

    private boolean a(World world, BlockPosition blockposition, Block block, Block block1, Block block2) {
        return !block.canPlace(world, blockposition) ? false : (block1.getMaterial() != Material.AIR ? false : (block2.getMaterial() == Material.AIR ? false : block2.d()));
    }
}
