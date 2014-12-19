package net.minecraft.server;

import com.google.common.base.Predicate;

final class EntitySelectorMonster implements Predicate {

    EntitySelectorMonster() {}

    public boolean a(Entity entity) {
        return entity instanceof IMonster;
    }

    public boolean apply(Object object) {
        return this.a((Entity) object);
    }
}
