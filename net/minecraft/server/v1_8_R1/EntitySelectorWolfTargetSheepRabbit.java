package net.minecraft.server;

import com.google.common.base.Predicate;

class EntitySelectorWolfTargetSheepRabbit implements Predicate {

    final EntityWolf a;

    EntitySelectorWolfTargetSheepRabbit(EntityWolf entitywolf) {
        this.a = entitywolf;
    }

    public boolean a(Entity entity) {
        return entity instanceof EntitySheep || entity instanceof EntityRabbit;
    }

    public boolean apply(Object object) {
        return this.a((Entity) object);
    }
}
