package net.minecraft.server;

class PathfinderGoalSquid extends PathfinderGoal {

    private EntitySquid a;

    public PathfinderGoalSquid(EntitySquid entitysquid) {
        this.a = entitysquid;
    }

    public boolean a() {
        return true;
    }

    public void e() {
        int i = this.a.bg();

        if (i > 100) {
            this.a.b(0.0F, 0.0F, 0.0F);
        } else if (this.a.bb().nextInt(50) == 0 || !EntitySquid.a(this.a) || !this.a.n()) {
            float f = this.a.bb().nextFloat() * 3.1415927F * 2.0F;
            float f1 = MathHelper.cos(f) * 0.2F;
            float f2 = -0.1F + this.a.bb().nextFloat() * 0.2F;
            float f3 = MathHelper.sin(f) * 0.2F;

            this.a.b(f1, f2, f3);
        }

    }
}
