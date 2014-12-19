package net.minecraft.server;

import com.google.common.base.Predicate;

class PathfinderGoalTargetNearestPlayerPlayerDetector implements Predicate {

    final PathfinderGoalTargetNearestPlayer a;

    PathfinderGoalTargetNearestPlayerPlayerDetector(PathfinderGoalTargetNearestPlayer pathfindergoaltargetnearestplayer) {
        this.a = pathfindergoaltargetnearestplayer;
    }

    public boolean a(Entity entity) {
        if (!(entity instanceof EntityHuman)) {
            return false;
        } else {
            double d0 = this.a.f();

            if (entity.isSneaking()) {
                d0 *= 0.800000011920929D;
            }

            if (entity.isInvisible()) {
                float f = ((EntityHuman) entity).bX();

                if (f < 0.1F) {
                    f = 0.1F;
                }

                d0 *= (double) (0.7F * f);
            }

            return (double) entity.g(PathfinderGoalTargetNearestPlayer.a(this.a)) > d0 ? false : PathfinderGoalTarget.a(PathfinderGoalTargetNearestPlayer.a(this.a), (EntityLiving) entity, false, true);
        }
    }

    public boolean apply(Object object) {
        return this.a((Entity) object);
    }
}
