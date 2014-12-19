package net.minecraft.server;

class ControllerGhast extends ControllerMove {

    private EntityGhast g;
    private int h;

    public ControllerGhast(EntityGhast entityghast) {
        super(entityghast);
        this.g = entityghast;
    }

    public void c() {
        if (this.f) {
            double d0 = this.b - this.g.locX;
            double d1 = this.c - this.g.locY;
            double d2 = this.d - this.g.locZ;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;

            if (this.h-- <= 0) {
                this.h += this.g.bb().nextInt(5) + 2;
                d3 = (double) MathHelper.sqrt(d3);
                if (this.b(this.b, this.c, this.d, d3)) {
                    this.g.motX += d0 / d3 * 0.1D;
                    this.g.motY += d1 / d3 * 0.1D;
                    this.g.motZ += d2 / d3 * 0.1D;
                } else {
                    this.f = false;
                }
            }

        }
    }

    private boolean b(double d0, double d1, double d2, double d3) {
        double d4 = (d0 - this.g.locX) / d3;
        double d5 = (d1 - this.g.locY) / d3;
        double d6 = (d2 - this.g.locZ) / d3;
        AxisAlignedBB axisalignedbb = this.g.getBoundingBox();

        for (int i = 1; (double) i < d3; ++i) {
            axisalignedbb = axisalignedbb.c(d4, d5, d6);
            if (!this.g.world.getCubes(this.g, axisalignedbb).isEmpty()) {
                return false;
            }
        }

        return true;
    }
}
