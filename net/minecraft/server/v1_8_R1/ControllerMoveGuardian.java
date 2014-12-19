package net.minecraft.server;

class ControllerMoveGuardian extends ControllerMove {

    private EntityGuardian g;

    public ControllerMoveGuardian(EntityGuardian entityguardian) {
        super(entityguardian);
        this.g = entityguardian;
    }

    public void c() {
        if (this.f && !this.g.getNavigation().m()) {
            double d0 = this.b - this.g.locX;
            double d1 = this.c - this.g.locY;
            double d2 = this.d - this.g.locZ;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;

            d3 = (double) MathHelper.sqrt(d3);
            d1 /= d3;
            float f = (float) (Math.atan2(d2, d0) * 180.0D / 3.1415927410125732D) - 90.0F;

            this.g.yaw = this.a(this.g.yaw, f, 30.0F);
            this.g.aG = this.g.yaw;
            float f1 = (float) (this.e * this.g.getAttributeInstance(GenericAttributes.d).getValue());

            this.g.j(this.g.bH() + (f1 - this.g.bH()) * 0.125F);
            double d4 = Math.sin((double) (this.g.ticksLived + this.g.getId()) * 0.5D) * 0.05D;
            double d5 = Math.cos((double) (this.g.yaw * 3.1415927F / 180.0F));
            double d6 = Math.sin((double) (this.g.yaw * 3.1415927F / 180.0F));

            this.g.motX += d4 * d5;
            this.g.motZ += d4 * d6;
            d4 = Math.sin((double) (this.g.ticksLived + this.g.getId()) * 0.75D) * 0.05D;
            this.g.motY += d4 * (d6 + d5) * 0.25D;
            this.g.motY += (double) this.g.bH() * d1 * 0.1D;
            ControllerLook controllerlook = this.g.getControllerLook();
            double d7 = this.g.locX + d0 / d3 * 2.0D;
            double d8 = (double) this.g.getHeadHeight() + this.g.locY + d1 / d3 * 1.0D;
            double d9 = this.g.locZ + d2 / d3 * 2.0D;
            double d10 = controllerlook.e();
            double d11 = controllerlook.f();
            double d12 = controllerlook.g();

            if (!controllerlook.b()) {
                d10 = d7;
                d11 = d8;
                d12 = d9;
            }

            this.g.getControllerLook().a(d10 + (d7 - d10) * 0.125D, d11 + (d8 - d11) * 0.125D, d12 + (d9 - d12) * 0.125D, 10.0F, 40.0F);
            EntityGuardian.a(this.g, true);
        } else {
            this.g.j(0.0F);
            EntityGuardian.a(this.g, false);
        }
    }
}
