package net.minecraft.server;

public class CommandNumber {

    private final double a;
    private final double b;
    private final boolean c;

    protected CommandNumber(double d0, double d1, boolean flag) {
        this.a = d0;
        this.b = d1;
        this.c = flag;
    }

    public double a() {
        return this.a;
    }

    public double b() {
        return this.b;
    }

    public boolean c() {
        return this.c;
    }
}
