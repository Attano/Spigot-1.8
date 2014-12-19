package net.minecraft.server;

public class ControllerJumpRabbit extends ControllerJump {

    private EntityRabbit c;
    private boolean d;
    final EntityRabbit b;

    public ControllerJumpRabbit(EntityRabbit entityrabbit, EntityRabbit entityrabbit1) {
        super(entityrabbit1);
        this.b = entityrabbit;
        this.d = false;
        this.c = entityrabbit1;
    }

    public boolean c() {
        return this.a;
    }

    public boolean d() {
        return this.d;
    }

    public void a(boolean flag) {
        this.d = flag;
    }

    public void b() {
        if (this.a) {
            this.c.b(EnumRabbitState.STEP);
            this.a = false;
        }

    }
}
