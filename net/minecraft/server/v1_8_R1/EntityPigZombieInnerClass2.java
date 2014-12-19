package net.minecraft.server;

class EntityPigZombieInnerClass2 extends PathfinderGoalNearestAttackableTarget {

    public EntityPigZombieInnerClass2(EntityPigZombie entitypigzombie) {
        super(entitypigzombie, EntityHuman.class, true);
    }

    public boolean a() {
        return ((EntityPigZombie) this.e).ck() && super.a();
    }
}
