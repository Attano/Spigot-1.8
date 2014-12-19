package net.minecraft.server;

import com.google.common.base.Predicate;

class EntityVillagerInnerClass1 implements Predicate {

    final EntityVillager a;

    EntityVillagerInnerClass1(EntityVillager entityvillager) {
        this.a = entityvillager;
    }

    public boolean a(Entity entity) {
        return entity instanceof EntityZombie;
    }

    public boolean apply(Object object) {
        return this.a((Entity) object);
    }
}
