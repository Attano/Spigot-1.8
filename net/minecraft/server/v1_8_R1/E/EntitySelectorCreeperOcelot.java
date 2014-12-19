package net.minecraft.server;

import com.google.common.base.Predicate;

class EntitySelectorCreeperOcelot implements Predicate {

    final EntityCreeper a;

    EntitySelectorCreeperOcelot(EntityCreeper entitycreeper) {
        this.a = entitycreeper;
    }

    public boolean a(Entity entity) {
        return entity instanceof EntityOcelot;
    }

    public boolean apply(Object object) {
        return this.a((Entity) object);
    }
}
