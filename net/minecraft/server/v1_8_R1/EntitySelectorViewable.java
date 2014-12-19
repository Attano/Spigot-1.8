package net.minecraft.server;

import com.google.common.base.Predicate;

class EntitySelectorViewable implements Predicate {

    final PathfinderGoalAvoidTarget a;

    EntitySelectorViewable(PathfinderGoalAvoidTarget pathfindergoalavoidtarget) {
        this.a = pathfindergoalavoidtarget;
    }

    public boolean a(Entity entity) {
        return entity.isAlive() && this.a.b.getEntitySenses().a(entity);
    }

    public boolean apply(Object object) {
        return this.a((Entity) object);
    }
}
