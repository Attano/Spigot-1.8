package net.minecraft.server;

class PathfinderGoalSlimeNearestPlayer extends PathfinderGoal {

    private EntitySlime a;
    private int b;

    public PathfinderGoalSlimeNearestPlayer(EntitySlime entityslime) {
        this.a = entityslime;
        this.a(2);
    }

    public boolean a() {
        EntityLiving entityliving = this.a.getGoalTarget();

        return entityliving == null ? false : entityliving.isAlive();
    }

    public void c() {
        this.b = 300;
        super.c();
    }

    public boolean b() {
        EntityLiving entityliving = this.a.getGoalTarget();

        return entityliving == null ? false : (!entityliving.isAlive() ? false : --this.b > 0);
    }

    public void e() {
        this.a.a(this.a.getGoalTarget(), 10.0F, 10.0F);
        ((ControllerMoveSlime) this.a.getControllerMove()).a(this.a.yaw, this.a.cg());
    }
}
