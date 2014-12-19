package net.minecraft.server;

import com.google.common.base.Predicate;

class EntitySelectorSkeletonWolf implements Predicate {

    final EntitySkeleton a;

    EntitySelectorSkeletonWolf(EntitySkeleton entityskeleton) {
        this.a = entityskeleton;
    }

    public boolean a(Entity entity) {
        return entity instanceof EntityWolf;
    }

    public boolean apply(Object object) {
        return this.a((Entity) object);
    }
}
