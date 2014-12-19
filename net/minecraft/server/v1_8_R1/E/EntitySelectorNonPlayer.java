package net.minecraft.server;

import com.google.common.base.Predicate;

final class EntitySelectorNonPlayer implements Predicate {

    EntitySelectorNonPlayer() {}

    public boolean a(Entity entity) {
        return !(entity instanceof EntityHuman) || !((EntityHuman) entity).v();
    }

    public boolean apply(Object object) {
        return this.a((Entity) object);
    }
}
