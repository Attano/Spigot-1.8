package net.minecraft.server;

import com.google.common.base.Predicate;

final class EntitySelectorHuman implements Predicate {

    EntitySelectorHuman() {}

    public boolean a(Entity entity) {
        return entity instanceof EntityHuman;
    }

    public boolean apply(Object object) {
        return this.a((Entity) object);
    }
}
