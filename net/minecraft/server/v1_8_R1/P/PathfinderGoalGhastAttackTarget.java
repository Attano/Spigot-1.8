package net.minecraft.server;

class PathfinderGoalGhastAttackTarget extends PathfinderGoal {

    private EntityGhast b;
    public int a;

    public PathfinderGoalGhastAttackTarget(EntityGhast entityghast) {
        this.b = entityghast;
    }

    public boolean a() {
        return this.b.getGoalTarget() != null;
    }

    public void c() {
        this.a = 0;
    }

    public void d() {
        this.b.a(false);
    }

    public void e() {
        EntityLiving entityliving = this.b.getGoalTarget();
        double d0 = 64.0D;

        if (entityliving.h(this.b) < d0 * d0 && this.b.hasLineOfSight(entityliving)) {
            World world = this.b.world;

            ++this.a;
            if (this.a == 10) {
                world.a((EntityHuman) null, 1007, new BlockPosition(this.b), 0);
            }

            if (this.a == 20) {
                double d1 = 4.0D;
                Vec3D vec3d = this.b.d(1.0F);
                double d2 = entityliving.locX - (this.b.locX + vec3d.a * d1);
                double d3 = entityliving.getBoundingBox().b + (double) (entityliving.length / 2.0F) - (0.5D + this.b.locY + (double) (this.b.length / 2.0F));
                double d4 = entityliving.locZ - (this.b.locZ + vec3d.c * d1);

                world.a((EntityHuman) null, 1008, new BlockPosition(this.b), 0);
                EntityLargeFireball entitylargefireball = new EntityLargeFireball(world, this.b, d2, d3, d4);

                // CraftBukkit - set bukkitYield when setting explosionpower
                entitylargefireball.bukkitYield = entitylargefireball.yield = this.b.cd();
                entitylargefireball.locX = this.b.locX + vec3d.a * d1;
                entitylargefireball.locY = this.b.locY + (double) (this.b.length / 2.0F) + 0.5D;
                entitylargefireball.locZ = this.b.locZ + vec3d.c * d1;
                world.addEntity(entitylargefireball);
                this.a = -40;
            }
        } else if (this.a > 0) {
            --this.a;
        }

        this.b.a(this.a > 10);
    }
}
