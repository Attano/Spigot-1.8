package net.minecraft.server;

enum EnumRabbitState {

    NONE(0.0F, 0.0F, 30, 1), HOP(0.8F, 0.2F, 20, 10), STEP(1.0F, 0.45F, 14, 14), SPRINT(1.75F, 0.4F, 1, 8), ATTACK(2.0F, 0.7F, 7, 8);

    private final float f;
    private final float g;
    private final int h;
    private final int i;

    private EnumRabbitState(float f, float f1, int i, int j) {
        this.f = f;
        this.g = f1;
        this.h = i;
        this.i = j;
    }

    public float a() {
        return this.f;
    }

    public float b() {
        return this.g;
    }

    public int c() {
        return this.h;
    }

    public int d() {
        return this.i;
    }
}
