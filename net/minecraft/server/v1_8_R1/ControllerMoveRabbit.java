package net.minecraft.server;

class ControllerMoveRabbit extends ControllerMove {

    private EntityRabbit g;

    public ControllerMoveRabbit(EntityRabbit entityrabbit) {
        super(entityrabbit);
        this.g = entityrabbit;
    }

    public void c() {
        if (this.g.onGround && !this.g.cj()) {
            this.g.b(0.0D);
        }

        super.c();
    }
}
