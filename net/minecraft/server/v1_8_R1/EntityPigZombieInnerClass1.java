package net.minecraft.server;

class EntityPigZombieInnerClass1 extends PathfinderGoalHurtByTarget {

    public EntityPigZombieInnerClass1(EntityPigZombie entitypigzombie) {
        super(entitypigzombie, true, new Class[0]);
    }

    protected void a(EntityCreature entitycreature, EntityLiving entityliving) {
        super.a(entitycreature, entityliving);
        if (entitycreature instanceof EntityPigZombie) {
            EntityPigZombie.a((EntityPigZombie) entitycreature, (Entity) entityliving);
        }

    }
}
