package net.minecraft.server;

import com.google.common.base.Predicate;

final class EntitySelectorContainer implements Predicate {

    EntitySelectorContainer() {}

    public boolean a(Entity entity) {
        return entity instanceof IInventory && entity.isAlive();
    }

    public boolean apply(Object object) {
        return this.a((Entity) object);
    }
}
