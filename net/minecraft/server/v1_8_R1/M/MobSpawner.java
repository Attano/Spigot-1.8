package net.minecraft.server;

class MobSpawner extends MobSpawnerAbstract {

    final TileEntityMobSpawner a;

    MobSpawner(TileEntityMobSpawner tileentitymobspawner) {
        this.a = tileentitymobspawner;
    }

    public void a(int i) {
        this.a.world.playBlockAction(this.a.position, Blocks.MOB_SPAWNER, i, 0);
    }

    public World a() {
        return this.a.world;
    }

    public BlockPosition b() {
        return this.a.position;
    }

    public void a(TileEntityMobSpawnerData tileentitymobspawnerdata) {
        super.a(tileentitymobspawnerdata);
        if (this.a() != null) {
            this.a().notify(this.a.position);
        }

    }
}
