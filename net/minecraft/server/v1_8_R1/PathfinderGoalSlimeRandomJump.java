package net.minecraft.server;

class PathfinderGoalSlimeRandomJump extends PathfinderGoal {

    private EntitySlime a;

    public PathfinderGoalSlimeRandomJump(EntitySlime entityslime) {
        this.a = entityslime;
        this.a(5);
        ((Navigation) entityslime.getNavigation()).d(true);
    }

    public boolean a() {
        return this.a.V() || this.a.ab();
    }

    public void e() {
        if (this.a.bb().nextFloat() < 0.8F) {
            this.a.getControllerJump().a();
        }

        ((ControllerMoveSlime) this.a.getControllerMove()).a(1.2D);
    }
}
