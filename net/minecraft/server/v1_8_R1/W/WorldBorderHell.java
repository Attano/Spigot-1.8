package net.minecraft.server;

class WorldBorderHell extends WorldBorder {

    final WorldProviderHell a;

    WorldBorderHell(WorldProviderHell worldproviderhell) {
        this.a = worldproviderhell;
    }

    public double f() {
        return super.f() / 8.0D;
    }

    public double g() {
        return super.g() / 8.0D;
    }
}
