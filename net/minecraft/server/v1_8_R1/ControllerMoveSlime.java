package net.minecraft.server;

class ControllerMoveSlime extends ControllerMove {

    private float g;
    private int h;
    private EntitySlime i;
    private boolean j;

    public ControllerMoveSlime(EntitySlime entityslime) {
        super(entityslime);
        this.i = entityslime;
    }

    public void a(float f, boolean flag) {
        this.g = f;
        this.j = flag;
    }

    public void a(double d0) {
        this.e = d0;
        this.f = true;
    }

    public void c() {
        this.a.yaw = this.a(this.a.yaw, this.g, 30.0F);
        this.a.aI = this.a.yaw;
        this.a.aG = this.a.yaw;
        if (!this.f) {
            this.a.m(0.0F);
        } else {
            this.f = false;
            if (this.a.onGround) {
                this.a.j((float) (this.e * this.a.getAttributeInstance(GenericAttributes.d).getValue()));
                if (this.h-- <= 0) {
                    this.h = this.i.ce();
                    if (this.j) {
                        this.h /= 3;
                    }

                    this.i.getControllerJump().a();
                    if (this.i.cl()) {
                        this.i.makeSound(this.i.ci(), this.i.bA(), ((this.i.bb().nextFloat() - this.i.bb().nextFloat()) * 0.2F + 1.0F) * 0.8F);
                    }
                } else {
                    this.i.aX = this.i.aY = 0.0F;
                    this.a.j(0.0F);
                }
            } else {
                this.a.j((float) (this.e * this.a.getAttributeInstance(GenericAttributes.d).getValue()));
            }

        }
    }
}
