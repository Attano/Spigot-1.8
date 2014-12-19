package net.minecraft.server;

class PathfinderGoalSlimeRandomDirection extends PathfinderGoal {

    private EntitySlime a;
    private float b;
    private int c;

    public PathfinderGoalSlimeRandomDirection(EntitySlime entityslime) {
        this.a = entityslime;
        this.a(2);
    }

    public boolean a() {
        return this.a.getGoalTarget() == null && (this.a.onGround || this.a.V() || this.a.ab());
    }

    public void e() {
        if (--this.c <= 0) {
            this.c = 40 + this.a.bb().nextInt(60);
            this.b = (float) this.a.bb().nextInt(360);
        }

        ((ControllerMoveSlime) this.a.getControllerMove()).a(this.b, false);
    }
}
