package net.minecraft.server;

import com.google.common.base.Predicate;

class EntitySelectorNearestAttackableTarget implements Predicate {

    final Predicate a;
    final PathfinderGoalNearestAttackableTarget b;

    EntitySelectorNearestAttackableTarget(PathfinderGoalNearestAttackableTarget pathfindergoalnearestattackabletarget, Predicate predicate) {
        this.b = pathfindergoalnearestattackabletarget;
        this.a = predicate;
    }

    public boolean a(EntityLiving entityliving) {
        if (this.a != null && !this.a.apply(entityliving)) {
            return false;
        } else {
            if (entityliving instanceof EntityHuman) {
                double d0 = this.b.f();

                if (entityliving.isSneaking()) {
                    d0 *= 0.800000011920929D;
                }

                if (entityliving.isInvisible()) {
                    float f = ((EntityHuman) entityliving).bX();

                    if (f < 0.1F) {
                        f = 0.1F;
                    }

                    d0 *= (double) (0.7F * f);
                }

                if ((double) entityliving.g(this.b.e) > d0) {
                    return false;
                }
            }

            return this.b.a(entityliving, false);
        }
    }

    public boolean apply(Object object) {
        return this.a((EntityLiving) object);
    }
}
