package net.minecraft.server;

import com.google.common.base.Predicate;

final class EntitySelectorLiving implements Predicate {

    EntitySelectorLiving() {}

    public boolean a(Entity entity) {
        return entity.isAlive();
    }

    public boolean apply(Object object) {
        return this.a((Entity) object);
    }
}
