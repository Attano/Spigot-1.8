package net.minecraft.server;

class PathfinderGoalGhastMoveTowardsTarget extends PathfinderGoal {

    private EntityGhast a;

    public PathfinderGoalGhastMoveTowardsTarget(EntityGhast entityghast) {
        this.a = entityghast;
        this.a(2);
    }

    public boolean a() {
        return true;
    }

    public void e() {
        if (this.a.getGoalTarget() == null) {
            this.a.aG = this.a.yaw = -((float) Math.atan2(this.a.motX, this.a.motZ)) * 180.0F / 3.1415927F;
        } else {
            EntityLiving entityliving = this.a.getGoalTarget();
            double d0 = 64.0D;

            if (entityliving.h(this.a) < d0 * d0) {
                double d1 = entityliving.locX - this.a.locX;
                double d2 = entityliving.locZ - this.a.locZ;

                this.a.aG = this.a.yaw = -((float) Math.atan2(d1, d2)) * 180.0F / 3.1415927F;
            }
        }

    }
}
