package net.minecraft.server;

import java.util.Random;

class PathfinderGoalSilverfishWakeOthers extends PathfinderGoal {

    private EntitySilverfish silverfish;
    private int b;

    public PathfinderGoalSilverfishWakeOthers(EntitySilverfish entitysilverfish) {
        this.silverfish = entitysilverfish;
    }

    public void f() {
        if (this.b == 0) {
            this.b = 20;
        }

    }

    public boolean a() {
        return this.b > 0;
    }

    public void e() {
        --this.b;
        if (this.b <= 0) {
            World world = this.silverfish.world;
            Random random = this.silverfish.bb();
            BlockPosition blockposition = new BlockPosition(this.silverfish);

            for (int i = 0; i <= 5 && i >= -5; i = i <= 0 ? 1 - i : 0 - i) {
                for (int j = 0; j <= 10 && j >= -10; j = j <= 0 ? 1 - j : 0 - j) {
                    for (int k = 0; k <= 10 && k >= -10; k = k <= 0 ? 1 - k : 0 - k) {
                        BlockPosition blockposition1 = blockposition.a(j, i, k);
                        IBlockData iblockdata = world.getType(blockposition1);

                        if (iblockdata.getBlock() == Blocks.MONSTER_EGG) {
                            // CraftBukkit start
                            if (org.bukkit.craftbukkit.event.CraftEventFactory.callEntityChangeBlockEvent(this.silverfish, blockposition1.getX(), blockposition1.getY(), blockposition1.getZ(), Blocks.AIR, 0).isCancelled()) {
                                continue;
                            }
                            // CraftBukkit end
                            if (world.getGameRules().getBoolean("mobGriefing")) {
                                world.setAir(blockposition1, true);
                            } else {
                                world.setTypeAndData(blockposition1, ((EnumMonsterEggVarient) iblockdata.get(BlockMonsterEggs.VARIANT)).d(), 3);
                            }

                            if (random.nextBoolean()) {
                                return;
                            }
                        }
                    }
                }
            }
        }

    }
}
