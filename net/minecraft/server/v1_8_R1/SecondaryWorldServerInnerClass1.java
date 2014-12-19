package net.minecraft.server;

class SecondaryWorldServerInnerClass1 implements IWorldBorderListener {

    final SecondaryWorldServer a;

    SecondaryWorldServerInnerClass1(SecondaryWorldServer secondaryworldserver) {
        this.a = secondaryworldserver;
    }

    public void a(WorldBorder worldborder, double d0) {
        this.a.af().a(d0);
    }

    public void a(WorldBorder worldborder, double d0, double d1, long i) {
        this.a.af().a(d0, d1, i);
    }

    public void a(WorldBorder worldborder, double d0, double d1) {
        this.a.af().c(d0, d1);
    }

    public void a(WorldBorder worldborder, int i) {
        this.a.af().b(i);
    }

    public void b(WorldBorder worldborder, int i) {
        this.a.af().c(i);
    }

    public void b(WorldBorder worldborder, double d0) {
        this.a.af().c(d0);
    }

    public void c(WorldBorder worldborder, double d0) {
        this.a.af().b(d0);
    }
}
