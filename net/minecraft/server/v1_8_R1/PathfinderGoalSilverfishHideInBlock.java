package net.minecraft.server;

import java.util.Random;

class PathfinderGoalSilverfishHideInBlock extends PathfinderGoalRandomStroll {

    private final EntitySilverfish silverfish;
    private EnumDirection b;
    private boolean c;

    public PathfinderGoalSilverfishHideInBlock(EntitySilverfish entitysilverfish) {
        super(entitysilverfish, 1.0D, 10);
        this.silverfish = entitysilverfish;
        this.a(1);
    }

    public boolean a() {
        if (this.silverfish.getGoalTarget() != null) {
            return false;
        } else if (!this.silverfish.getNavigation().m()) {
            return false;
        } else {
            Random random = this.silverfish.bb();

            if (random.nextInt(10) == 0) {
                this.b = EnumDirection.a(random);
                BlockPosition blockposition = (new BlockPosition(this.silverfish.locX, this.silverfish.locY + 0.5D, this.silverfish.locZ)).shift(this.b);
                IBlockData iblockdata = this.silverfish.world.getType(blockposition);

                if (BlockMonsterEggs.d(iblockdata)) {
                    this.c = true;
                    return true;
                }
            }

            this.c = false;
            return super.a();
        }
    }

    public boolean b() {
        return this.c ? false : super.b();
    }

    public void c() {
        if (!this.c) {
            super.c();
        } else {
            World world = this.silverfish.world;
            BlockPosition blockposition = (new BlockPosition(this.silverfish.locX, this.silverfish.locY + 0.5D, this.silverfish.locZ)).shift(this.b);
            IBlockData iblockdata = world.getType(blockposition);

            if (BlockMonsterEggs.d(iblockdata)) {
                // CraftBukkit start
                if (org.bukkit.craftbukkit.event.CraftEventFactory.callEntityChangeBlockEvent(this.silverfish, blockposition.getX(), blockposition.getY(), blockposition.getZ(), Blocks.MONSTER_EGG, Block.getId(BlockMonsterEggs.getById(iblockdata.getBlock().toLegacyData(iblockdata)))).isCancelled()) {
                    return;
                }
                // CraftBukkit end
                world.setTypeAndData(blockposition, Blocks.MONSTER_EGG.getBlockData().set(BlockMonsterEggs.VARIANT, EnumMonsterEggVarient.a(iblockdata)), 3);
                this.silverfish.y();
                this.silverfish.die();
            }

        }
    }
}
