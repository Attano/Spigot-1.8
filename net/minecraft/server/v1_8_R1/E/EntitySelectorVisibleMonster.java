package net.minecraft.server;

import com.google.common.base.Predicate;

final class EntitySelectorVisibleMonster implements Predicate {

    EntitySelectorVisibleMonster() {}

    public boolean a(Entity entity) {
        return entity instanceof IMonster && !entity.isInvisible();
    }

    public boolean apply(Object object) {
        return this.a((Entity) object);
    }
}
