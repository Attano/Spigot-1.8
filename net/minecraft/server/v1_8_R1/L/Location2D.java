package net.minecraft.server;

import java.util.Random;

class Location2D {

    double a;
    double b;

    Location2D() {}

    Location2D(double d0, double d1) {
        this.a = d0;
        this.b = d1;
    }

    double a(Location2D location2d) {
        double d0 = this.a - location2d.a;
        double d1 = this.b - location2d.b;

        return Math.sqrt(d0 * d0 + d1 * d1);
    }

    void a() {
        double d0 = (double) this.b();

        this.a /= d0;
        this.b /= d0;
    }

    float b() {
        return MathHelper.sqrt(this.a * this.a + this.b * this.b);
    }

    public void b(Location2D location2d) {
        this.a -= location2d.a;
        this.b -= location2d.b;
    }

    public boolean a(double d0, double d1, double d2, double d3) {
        boolean flag = false;

        if (this.a < d0) {
            this.a = d0;
            flag = true;
        } else if (this.a > d2) {
            this.a = d2;
            flag = true;
        }

        if (this.b < d1) {
            this.b = d1;
            flag = true;
        } else if (this.b > d3) {
            this.b = d3;
            flag = true;
        }

        return flag;
    }

    public int a(World world) {
        BlockPosition blockposition = new BlockPosition(this.a, 256.0D, this.b);

        do {
            if (blockposition.getY() <= 0) {
                return 257;
            }

            blockposition = blockposition.down();
        } while (world.getType(blockposition).getBlock().getMaterial() == Material.AIR);

        return blockposition.getY() + 1;
    }

    public boolean b(World world) {
        BlockPosition blockposition = new BlockPosition(this.a, 256.0D, this.b);

        Material material;

        do {
            if (blockposition.getY() <= 0) {
                return false;
            }

            blockposition = blockposition.down();
            material = world.getType(blockposition).getBlock().getMaterial();
        } while (material == Material.AIR);

        return !material.isLiquid() && material != Material.FIRE;
    }

    public void a(Random random, double d0, double d1, double d2, double d3) {
        this.a = MathHelper.a(random, d0, d2);
        this.b = MathHelper.a(random, d1, d3);
    }
}
