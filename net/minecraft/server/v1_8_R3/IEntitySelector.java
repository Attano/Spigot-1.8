package net.minecraft.server;

import com.google.common.base.Predicate;

public final class IEntitySelector {

    public static final Predicate<Entity> a = new Predicate() {
        public boolean a(Entity entity) {
            return entity.isAlive();
        }

        public boolean apply(Object object) {
            return this.a((Entity) object);
        }
    };
    public static final Predicate<Entity> b = new Predicate() {
        public boolean a(Entity entity) {
            return entity.isAlive() && entity.passenger == null && entity.vehicle == null;
        }

        public boolean apply(Object object) {
            return this.a((Entity) object);
        }
    };
    public static final Predicate<Entity> c = new Predicate() {
        public boolean a(Entity entity) {
            return entity instanceof IInventory && entity.isAlive();
        }

        public boolean apply(Object object) {
            return this.a((Entity) object);
        }
    };
    public static final Predicate<Entity> d = new Predicate() {
        public boolean a(Entity entity) {
            return !(entity instanceof EntityHuman) || !((EntityHuman) entity).isSpectator();
        }

        public boolean apply(Object object) {
            return this.a((Entity) object);
        }
    };

    public static class EntitySelectorEquipable implements Predicate<Entity> {

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

                return entityliving.getEquipment(EntityInsentient.c(this.a)) != null ? false : (entityliving instanceof EntityInsentient ? ((EntityInsentient) entityliving).bY() : (entityliving instanceof EntityArmorStand ? true : entityliving instanceof EntityHuman));
            }
        }

        public boolean apply(Object object) {
            return this.a((Entity) object);
        }
    }
}
