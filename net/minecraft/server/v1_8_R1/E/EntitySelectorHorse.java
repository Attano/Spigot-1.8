package net.minecraft.server;

import com.google.common.base.Predicate;

final class EntitySelectorHorse implements Predicate {

    EntitySelectorHorse() {}

    public boolean a(Entity entity) {
        return entity instanceof EntityHorse && ((EntityHorse) entity).cy();
    }

    public boolean apply(Object object) {
        return this.a((Entity) object);
    }
}
