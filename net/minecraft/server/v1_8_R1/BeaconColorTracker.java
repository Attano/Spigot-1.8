package net.minecraft.server;

public class BeaconColorTracker {

    private final float[] a;
    private int b;

    public BeaconColorTracker(float[] afloat) {
        this.a = afloat;
        this.b = 1;
    }

    protected void a() {
        ++this.b;
    }

    public float[] b() {
        return this.a;
    }
}
