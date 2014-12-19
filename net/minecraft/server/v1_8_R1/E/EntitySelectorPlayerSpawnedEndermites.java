package net.minecraft.server;

import com.google.common.base.Predicate;

class EntitySelectorPlayerSpawnedEndermites implements Predicate {

    final EntityEnderman a;

    EntitySelectorPlayerSpawnedEndermites(EntityEnderman entityenderman) {
        this.a = entityenderman;
    }

    public boolean a(EntityEndermite entityendermite) {
        return entityendermite.n();
    }

    public boolean apply(Object object) {
        return this.a((EntityEndermite) object);
    }
}
