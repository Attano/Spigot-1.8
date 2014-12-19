package net.minecraft.server;

class PathfinderGoalGuardianAttack extends PathfinderGoal {

    private EntityGuardian a;
    private int b;

    public PathfinderGoalGuardianAttack(EntityGuardian entityguardian) {
        this.a = entityguardian;
        this.a(3);
    }

    public boolean a() {
        EntityLiving entityliving = this.a.getGoalTarget();

        return entityliving != null && entityliving.isAlive();
    }

    public boolean b() {
        return super.b() && (this.a.cl() || this.a.h(this.a.getGoalTarget()) > 9.0D);
    }

    public void c() {
        this.b = -10;
        this.a.getNavigation().n();
        this.a.getControllerLook().a(this.a.getGoalTarget(), 90.0F, 90.0F);
        this.a.ai = true;
    }

    public void d() {
        EntityGuardian.a(this.a, 0);
        this.a.setGoalTarget((EntityLiving) null);
        EntityGuardian.a(this.a).f();
    }

    public void e() {
        EntityLiving entityliving = this.a.getGoalTarget();

        this.a.getNavigation().n();
        this.a.getControllerLook().a(entityliving, 90.0F, 90.0F);
        if (!this.a.hasLineOfSight(entityliving)) {
            this.a.setGoalTarget((EntityLiving) null);
        } else {
            ++this.b;
            if (this.b == 0) {
                EntityGuardian.a(this.a, this.a.getGoalTarget().getId());
                this.a.world.broadcastEntityEffect(this.a, (byte) 21);
            } else if (this.b >= this.a.ck()) {
                float f = 1.0F;

                if (this.a.world.getDifficulty() == EnumDifficulty.HARD) {
                    f += 2.0F;
                }

                if (this.a.cl()) {
                    f += 2.0F;
                }

                entityliving.damageEntity(DamageSource.b(this.a, this.a), f);
                entityliving.damageEntity(DamageSource.mobAttack(this.a), (float) this.a.getAttributeInstance(GenericAttributes.e).getValue());
                this.a.setGoalTarget((EntityLiving) null);
            } else if (this.b >= 60 && this.b % 20 == 0) {
                ;
            }

            super.e();
        }
    }
}
