package net.minecraft.server;

import com.google.common.base.Predicate;

class PathfinderGoalRabbitAvoidTarget extends PathfinderGoalAvoidTarget {

    private EntityRabbit d;

    public PathfinderGoalRabbitAvoidTarget(EntityRabbit entityrabbit, Predicate predicate, float f, double d0, double d1) {
        super(entityrabbit, predicate, f, d0, d1);
        this.d = entityrabbit;
    }

    public void e() {
        super.e();
    }
}
