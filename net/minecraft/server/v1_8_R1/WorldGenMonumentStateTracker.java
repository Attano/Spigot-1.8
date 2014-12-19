package net.minecraft.server;

class WorldGenMonumentStateTracker {

    int a;
    WorldGenMonumentStateTracker[] b = new WorldGenMonumentStateTracker[6];
    boolean[] c = new boolean[6];
    boolean d;
    boolean e;
    int f;

    public WorldGenMonumentStateTracker(int i) {
        this.a = i;
    }

    public void a(EnumDirection enumdirection, WorldGenMonumentStateTracker worldgenmonumentstatetracker) {
        this.b[enumdirection.a()] = worldgenmonumentstatetracker;
        worldgenmonumentstatetracker.b[enumdirection.opposite().a()] = this;
    }

    public void a() {
        for (int i = 0; i < 6; ++i) {
            this.c[i] = this.b[i] != null;
        }

    }

    public boolean a(int i) {
        if (this.e) {
            return true;
        } else {
            this.f = i;

            for (int j = 0; j < 6; ++j) {
                if (this.b[j] != null && this.c[j] && this.b[j].f != i && this.b[j].a(i)) {
                    return true;
                }
            }

            return false;
        }
    }

    public boolean b() {
        return this.a >= 75;
    }

    public int c() {
        int i = 0;

        for (int j = 0; j < 6; ++j) {
            if (this.c[j]) {
                ++i;
            }
        }

        return i;
    }
}
