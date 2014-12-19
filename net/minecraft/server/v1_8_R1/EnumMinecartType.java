package net.minecraft.server;

import com.google.common.collect.Maps;
import java.util.Map;

public enum EnumMinecartType {

    RIDEABLE(0, "MinecartRideable"), CHEST(1, "MinecartChest"), FURNACE(2, "MinecartFurnace"), TNT(3, "MinecartTNT"), SPAWNER(4, "MinecartSpawner"), HOPPER(5, "MinecartHopper"), COMMAND_BLOCK(6, "MinecartCommandBlock");

    private static final Map h = Maps.newHashMap();
    private final int i;
    private final String j;

    private EnumMinecartType(int i, String s) {
        this.i = i;
        this.j = s;
    }

    public int a() {
        return this.i;
    }

    public String b() {
        return this.j;
    }

    public static EnumMinecartType a(int i) {
        EnumMinecartType enumminecarttype = (EnumMinecartType) EnumMinecartType.h.get(Integer.valueOf(i));

        return enumminecarttype == null ? EnumMinecartType.RIDEABLE : enumminecarttype;
    }

    static {
        EnumMinecartType[] aenumminecarttype = values();
        int i = aenumminecarttype.length;

        for (int j = 0; j < i; ++j) {
            EnumMinecartType enumminecarttype = aenumminecarttype[j];

            EnumMinecartType.h.put(Integer.valueOf(enumminecarttype.a()), enumminecarttype);
        }

    }
}
