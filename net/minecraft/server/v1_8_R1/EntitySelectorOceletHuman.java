package net.minecraft.server;

import com.google.common.base.Predicate;

class EntitySelectorOceletHuman implements Predicate {

    final EntityOcelot a;

    EntitySelectorOceletHuman(EntityOcelot entityocelot) {
        this.a = entityocelot;
    }

    public boolean a(Entity entity) {
        return entity instanceof EntityHuman;
    }

    public boolean apply(Object object) {
        return this.a((Entity) object);
    }
}
