package net.minecraft.server;

import com.google.common.base.Predicate;

final class EntitySelectorChickenJockey implements Predicate {

    EntitySelectorChickenJockey() {}

    public boolean a(Entity entity) {
        return entity.isAlive() && entity.passenger == null && entity.vehicle == null;
    }

    public boolean apply(Object object) {
        return this.a((Entity) object);
    }
}
