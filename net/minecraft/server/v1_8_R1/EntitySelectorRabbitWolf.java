package net.minecraft.server;

import com.google.common.base.Predicate;

class EntitySelectorRabbitWolf implements Predicate {

    final EntityRabbit a;

    EntitySelectorRabbitWolf(EntityRabbit entityrabbit) {
        this.a = entityrabbit;
    }

    public boolean a(Entity entity) {
        return entity instanceof EntityWolf;
    }

    public boolean apply(Object object) {
        return this.a((Entity) object);
    }
}
