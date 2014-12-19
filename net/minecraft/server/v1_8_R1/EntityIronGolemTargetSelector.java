package net.minecraft.server;

import com.google.common.base.Predicate;

class EntityIronGolemTargetSelector implements Predicate {

    final Predicate a;
    final EntityCreature b;
    final PathfinderGoalNearestGolemTarget c;

    EntityIronGolemTargetSelector(PathfinderGoalNearestGolemTarget pathfindergoalnearestgolemtarget, Predicate predicate, EntityCreature entitycreature) {
        this.c = pathfindergoalnearestgolemtarget;
        this.a = predicate;
        this.b = entitycreature;
    }

    public boolean a(EntityLiving entityliving) {
        if (this.a != null && !this.a.apply(entityliving)) {
            return false;
        } else if (entityliving instanceof EntityCreeper) {
            return false;
        } else {
            if (entityliving instanceof EntityHuman) {
                double d0 = PathfinderGoalNearestGolemTarget.a(this.c);

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

                if ((double) entityliving.g(this.b) > d0) {
                    return false;
                }
            }

            return PathfinderGoalNearestGolemTarget.a(this.c, entityliving, false);
        }
    }

    public boolean apply(Object object) {
        return this.a((EntityLiving) object);
    }
}
