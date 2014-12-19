package net.minecraft.server;

class PathfinderGoalKillerRabbitMeleeAttack extends PathfinderGoalMeleeAttack {

    public PathfinderGoalKillerRabbitMeleeAttack(EntityRabbit entityrabbit) {
        super(entityrabbit, EntityLiving.class, 1.4D, true);
    }

    protected double a(EntityLiving entityliving) {
        return (double) (4.0F + entityliving.width);
    }
}
