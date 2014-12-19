package net.minecraft.server;

import com.google.common.base.Predicate;

class EntitySelectorLiving2 implements Predicate {

    final WorldServer a;

    EntitySelectorLiving2(WorldServer worldserver) {
        this.a = worldserver;
    }

    public boolean a(EntityLiving entityliving) {
        return entityliving != null && entityliving.isAlive() && this.a.i(entityliving.getChunkCoordinates());
    }

    public boolean apply(Object object) {
        return this.a((EntityLiving) object);
    }
}
