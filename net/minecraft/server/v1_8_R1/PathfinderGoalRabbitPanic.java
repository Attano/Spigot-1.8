package net.minecraft.server;

class PathfinderGoalRabbitPanic extends PathfinderGoalPanic {

    private EntityRabbit b;

    public PathfinderGoalRabbitPanic(EntityRabbit entityrabbit, double d0) {
        super(entityrabbit, d0);
        this.b = entityrabbit;
    }

    public void e() {
        super.e();
        this.b.b(this.a);
    }
}
