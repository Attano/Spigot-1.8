package net.minecraft.server;

import com.google.common.base.Predicate;

class EntitySelectorExplodingCreeper implements Predicate {

    final EntityMonster a;

    EntitySelectorExplodingCreeper(EntityMonster entitymonster) {
        this.a = entitymonster;
    }

    public boolean a(Entity entity) {
        return entity instanceof EntityCreeper && ((EntityCreeper) entity).ck() > 0;
    }

    public boolean apply(Object object) {
        return this.a((Entity) object);
    }
}
