package net.minecraft.server;

import com.google.common.base.Predicate;

class PathfinderGoalNearestGolemTarget extends PathfinderGoalNearestAttackableTarget {

    public PathfinderGoalNearestGolemTarget(EntityCreature entitycreature, Class oclass, int i, boolean flag, boolean flag1, Predicate predicate) {
        super(entitycreature, oclass, i, flag, flag1, predicate);
        this.c = new EntityIronGolemTargetSelector(this, predicate, entitycreature);
    }

    static double a(PathfinderGoalNearestGolemTarget pathfindergoalnearestgolemtarget) {
        return pathfindergoalnearestgolemtarget.f();
    }

    static boolean a(PathfinderGoalNearestGolemTarget pathfindergoalnearestgolemtarget, EntityLiving entityliving, boolean flag) {
        return pathfindergoalnearestgolemtarget.a(entityliving, flag);
    }
}
