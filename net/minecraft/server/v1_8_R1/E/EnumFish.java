package net.minecraft.server;

import com.google.common.collect.Maps;
import java.util.Map;

public enum EnumFish {

    COD(0, "cod", 2, 0.1F, 5, 0.6F), SALMON(1, "salmon", 2, 0.1F, 6, 0.8F), CLOWNFISH(2, "clownfish", 1, 0.1F), PUFFERFISH(3, "pufferfish", 1, 0.1F);

    private static final Map e = Maps.newHashMap();
    private final int f;
    private final String g;
    private final int h;
    private final float i;
    private final int j;
    private final float k;
    private boolean l = false;

    private EnumFish(int i, String s, int j, float f, int k, float f1) {
        this.f = i;
        this.g = s;
        this.h = j;
        this.i = f;
        this.j = k;
        this.k = f1;
        this.l = true;
    }

    private EnumFish(int i, String s, int j, float f) {
        this.f = i;
        this.g = s;
        this.h = j;
        this.i = f;
        this.j = 0;
        this.k = 0.0F;
        this.l = false;
    }

    public int a() {
        return this.f;
    }

    public String b() {
        return this.g;
    }

    public int c() {
        return this.h;
    }

    public float d() {
        return this.i;
    }

    public int e() {
        return this.j;
    }

    public float f() {
        return this.k;
    }

    public boolean g() {
        return this.l;
    }

    public static EnumFish a(int i) {
        EnumFish enumfish = (EnumFish) EnumFish.e.get(Integer.valueOf(i));

        return enumfish == null ? EnumFish.COD : enumfish;
    }

    public static EnumFish a(ItemStack itemstack) {
        return itemstack.getItem() instanceof ItemFish ? a(itemstack.getData()) : EnumFish.COD;
    }

    static {
        EnumFish[] aenumfish = values();
        int i = aenumfish.length;

        for (int j = 0; j < i; ++j) {
            EnumFish enumfish = aenumfish[j];

            EnumFish.e.put(Integer.valueOf(enumfish.a()), enumfish);
        }

    }
}
