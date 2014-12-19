package net.minecraft.server;

class PathfinderGoalSlimeIdle extends PathfinderGoal {

    private EntitySlime a;

    public PathfinderGoalSlimeIdle(EntitySlime entityslime) {
        this.a = entityslime;
        this.a(5);
    }

    public boolean a() {
        return true;
    }

    public void e() {
        ((ControllerMoveSlime) this.a.getControllerMove()).a(1.0D);
    }
}
