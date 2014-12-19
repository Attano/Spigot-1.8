package net.minecraft.server;

import com.google.common.base.Predicate;

class PathfinderGoalNearestAttackableTargetInsentientInnerClass1 implements Predicate {

    final PathfinderGoalNearestAttackableTargetInsentient a;

    PathfinderGoalNearestAttackableTargetInsentientInnerClass1(PathfinderGoalNearestAttackableTargetInsentient pathfindergoalnearestattackabletargetinsentient) {
        this.a = pathfindergoalnearestattackabletargetinsentient;
    }

    public boolean a(EntityLiving entityliving) {
        double d0 = this.a.f();

        if (entityliving.isSneaking()) {
            d0 *= 0.800000011920929D;
        }

        return entityliving.isInvisible() ? false : ((double) entityliving.g(PathfinderGoalNearestAttackableTargetInsentient.a(this.a)) > d0 ? false : PathfinderGoalTarget.a(PathfinderGoalNearestAttackableTargetInsentient.a(this.a), entityliving, false, true));
    }

    public boolean apply(Object object) {
        return this.a((EntityLiving) object);
    }
}
