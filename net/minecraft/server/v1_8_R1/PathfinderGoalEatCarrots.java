package net.minecraft.server;

class PathfinderGoalEatCarrots extends PathfinderGoalGotoTarget {

    private final EntityRabbit c;
    private boolean d;
    private boolean e = false;

    public PathfinderGoalEatCarrots(EntityRabbit entityrabbit) {
        super(entityrabbit, 0.699999988079071D, 16);
        this.c = entityrabbit;
    }

    public boolean a() {
        if (this.a <= 0) {
            if (!this.c.world.getGameRules().getBoolean("mobGriefing")) {
                return false;
            }

            this.e = false;
            this.d = EntityRabbit.a(this.c);
        }

        return super.a();
    }

    public boolean b() {
        return this.e && super.b();
    }

    public void c() {
        super.c();
    }

    public void d() {
        super.d();
    }

    public void e() {
        super.e();
        this.c.getControllerLook().a((double) this.b.getX() + 0.5D, (double) (this.b.getY() + 1), (double) this.b.getZ() + 0.5D, 10.0F, (float) this.c.bP());
        if (this.f()) {
            World world = this.c.world;
            BlockPosition blockposition = this.b.up();
            IBlockData iblockdata = world.getType(blockposition);
            Block block = iblockdata.getBlock();

            if (this.e && block instanceof BlockCarrots && ((Integer) iblockdata.get(BlockCarrots.AGE)).intValue() == 7) {
                world.setTypeAndData(blockposition, Blocks.AIR.getBlockData(), 2);
                world.setAir(blockposition, true);
                this.c.cn();
            }

            this.e = false;
            this.a = 10;
        }

    }

    protected boolean a(World world, BlockPosition blockposition) {
        Block block = world.getType(blockposition).getBlock();

        if (block == Blocks.FARMLAND) {
            blockposition = blockposition.up();
            IBlockData iblockdata = world.getType(blockposition);

            block = iblockdata.getBlock();
            if (block instanceof BlockCarrots && ((Integer) iblockdata.get(BlockCarrots.AGE)).intValue() == 7 && this.d && !this.e) {
                this.e = true;
                return true;
            }
        }

        return false;
    }
}
