package net.minecraft.server;

import com.google.common.base.Predicate;

public class EntitySelectorEquipable implements Predicate {

    private final ItemStack a;

    public EntitySelectorEquipable(ItemStack itemstack) {
        this.a = itemstack;
    }

    public boolean a(Entity entity) {
        if (!entity.isAlive()) {
            return false;
        } else if (!(entity instanceof EntityLiving)) {
            return false;
        } else {
            EntityLiving entityliving = (EntityLiving) entity;

            return entityliving.getEquipment(EntityInsentient.c(this.a)) != null ? false : (entityliving instanceof EntityInsentient ? ((EntityInsentient) entityliving).bX() : (entityliving instanceof EntityArmorStand ? true : entityliving instanceof EntityHuman));
        }
    }

    public boolean apply(Object object) {
        return this.a((Entity) object);
    }
}
