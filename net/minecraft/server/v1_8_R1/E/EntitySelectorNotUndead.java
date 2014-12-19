package net.minecraft.server;

import com.google.common.base.Predicate;

final class EntitySelectorNotUndead implements Predicate {

    EntitySelectorNotUndead() {}

    public boolean a(Entity entity) {
        return entity instanceof EntityLiving && ((EntityLiving) entity).getMonsterType() != EnumMonsterType.UNDEAD;
    }

    public boolean apply(Object object) {
        return this.a((Entity) object);
    }
}
