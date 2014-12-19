package net.minecraft.server;

class PathfinderGoalBlazeFireball extends PathfinderGoal {

    private EntityBlaze a;
    private int b;
    private int c;

    public PathfinderGoalBlazeFireball(EntityBlaze entityblaze) {
        this.a = entityblaze;
        this.a(3);
    }

    public boolean a() {
        EntityLiving entityliving = this.a.getGoalTarget();

        return entityliving != null && entityliving.isAlive();
    }

    public void c() {
        this.b = 0;
    }

    public void d() {
        this.a.a(false);
    }

    public void e() {
        --this.c;
        EntityLiving entityliving = this.a.getGoalTarget();
        double d0 = this.a.h(entityliving);

        if (d0 < 4.0D) {
            if (this.c <= 0) {
                this.c = 20;
                this.a.r(entityliving);
            }

            this.a.getControllerMove().a(entityliving.locX, entityliving.locY, entityliving.locZ, 1.0D);
        } else if (d0 < 256.0D) {
            double d1 = entityliving.locX - this.a.locX;
            double d2 = entityliving.getBoundingBox().b + (double) (entityliving.length / 2.0F) - (this.a.locY + (double) (this.a.length / 2.0F));
            double d3 = entityliving.locZ - this.a.locZ;

            if (this.c <= 0) {
                ++this.b;
                if (this.b == 1) {
                    this.c = 60;
                    this.a.a(true);
                } else if (this.b <= 4) {
                    this.c = 6;
                } else {
                    this.c = 100;
                    this.b = 0;
                    this.a.a(false);
                }

                if (this.b > 1) {
                    float f = MathHelper.c(MathHelper.sqrt(d0)) * 0.5F;

                    this.a.world.a((EntityHuman) null, 1009, new BlockPosition((int) this.a.locX, (int) this.a.locY, (int) this.a.locZ), 0);

                    for (int i = 0; i < 1; ++i) {
                        EntitySmallFireball entitysmallfireball = new EntitySmallFireball(this.a.world, this.a, d1 + this.a.bb().nextGaussian() * (double) f, d2, d3 + this.a.bb().nextGaussian() * (double) f);

                        entitysmallfireball.locY = this.a.locY + (double) (this.a.length / 2.0F) + 0.5D;
                        this.a.world.addEntity(entitysmallfireball);
                    }
                }
            }

            this.a.getControllerLook().a(entityliving, 10.0F, 10.0F);
        } else {
            this.a.getNavigation().n();
            this.a.getControllerMove().a(entityliving.locX, entityliving.locY, entityliving.locZ, 1.0D);
        }

        super.e();
    }
}
