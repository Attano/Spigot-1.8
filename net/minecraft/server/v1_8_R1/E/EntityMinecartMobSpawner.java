package net.minecraft.server;

public class EntityMinecartMobSpawner extends EntityMinecartAbstract {

    private final MobSpawnerAbstract a = new MobSpawnerMinecart(this);

    public EntityMinecartMobSpawner(World world) {
        super(world);
    }

    public EntityMinecartMobSpawner(World world, double d0, double d1, double d2) {
        super(world, d0, d1, d2);
    }

    public EnumMinecartType s() {
        return EnumMinecartType.SPAWNER;
    }

    public IBlockData u() {
        return Blocks.MOB_SPAWNER.getBlockData();
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.a.a(nbttagcompound);
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.a.b(nbttagcompound);
    }

    public void s_() {
        super.s_();
        this.a.c();
    }
}
