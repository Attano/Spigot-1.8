package net.minecraft.server;

class PathfinderGoalSpiderNearestAttackableTarget extends PathfinderGoalNearestAttackableTarget {

    public PathfinderGoalSpiderNearestAttackableTarget(EntitySpider entityspider, Class oclass) {
        super(entityspider, oclass, true);
    }

    public boolean a() {
        float f = this.e.c(1.0F);

        return f >= 0.5F ? false : super.a();
    }
}
