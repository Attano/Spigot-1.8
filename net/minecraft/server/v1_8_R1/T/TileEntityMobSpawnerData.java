package net.minecraft.server;

public class TileEntityMobSpawnerData extends WeightedRandomChoice {

    private final NBTTagCompound c;
    private final String d;
    final MobSpawnerAbstract b;

    public TileEntityMobSpawnerData(MobSpawnerAbstract mobspawnerabstract, NBTTagCompound nbttagcompound) {
        this(mobspawnerabstract, nbttagcompound.getCompound("Properties"), nbttagcompound.getString("Type"), nbttagcompound.getInt("Weight"));
    }

    public TileEntityMobSpawnerData(MobSpawnerAbstract mobspawnerabstract, NBTTagCompound nbttagcompound, String s) {
        this(mobspawnerabstract, nbttagcompound, s, 1);
    }

    private TileEntityMobSpawnerData(MobSpawnerAbstract mobspawnerabstract, NBTTagCompound nbttagcompound, String s, int i) {
        super(i);
        this.b = mobspawnerabstract;
        if (s.equals("Minecart")) {
            if (nbttagcompound != null) {
                s = EnumMinecartType.a(nbttagcompound.getInt("Type")).b();
            } else {
                s = "MinecartRideable";
            }
        }

        this.c = nbttagcompound;
        this.d = s;
    }

    public NBTTagCompound a() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        nbttagcompound.set("Properties", this.c);
        nbttagcompound.setString("Type", this.d);
        nbttagcompound.setInt("Weight", this.a);
        return nbttagcompound;
    }

    public static String a(TileEntityMobSpawnerData tileentitymobspawnerdata) {
        return tileentitymobspawnerdata.d;
    }

    public static NBTTagCompound b(TileEntityMobSpawnerData tileentitymobspawnerdata) {
        return tileentitymobspawnerdata.c;
    }
}
