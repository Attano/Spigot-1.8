package net.minecraft.server;

class PathfinderGoalSpiderMeleeAttack extends PathfinderGoalMeleeAttack {

    public PathfinderGoalSpiderMeleeAttack(EntitySpider entityspider, Class oclass) {
        super(entityspider, oclass, 1.0D, true);
    }

    public boolean b() {
        float f = this.b.c(1.0F);

        if (f >= 0.5F && this.b.bb().nextInt(100) == 0) {
            this.b.setGoalTarget((EntityLiving) null);
            return false;
        } else {
            return super.b();
        }
    }

    protected double a(EntityLiving entityliving) {
        return (double) (4.0F + entityliving.width);
    }
}
