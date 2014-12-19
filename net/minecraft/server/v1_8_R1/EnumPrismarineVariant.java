package net.minecraft.server;

public enum EnumPrismarineVariant implements INamable {

    ROUGH(0, "prismarine", "rough"), BRICKS(1, "prismarine_bricks", "bricks"), DARK(2, "dark_prismarine", "dark");

    private static final EnumPrismarineVariant[] d = new EnumPrismarineVariant[values().length];
    private final int e;
    private final String f;
    private final String g;

    private EnumPrismarineVariant(int i, String s, String s1) {
        this.e = i;
        this.f = s;
        this.g = s1;
    }

    public int a() {
        return this.e;
    }

    public String toString() {
        return this.f;
    }

    public static EnumPrismarineVariant a(int i) {
        if (i < 0 || i >= EnumPrismarineVariant.d.length) {
            i = 0;
        }

        return EnumPrismarineVariant.d[i];
    }

    public String getName() {
        return this.f;
    }

    public String c() {
        return this.g;
    }

    static {
        EnumPrismarineVariant[] aenumprismarinevariant = values();
        int i = aenumprismarinevariant.length;

        for (int j = 0; j < i; ++j) {
            EnumPrismarineVariant enumprismarinevariant = aenumprismarinevariant[j];

            EnumPrismarineVariant.d[enumprismarinevariant.a()] = enumprismarinevariant;
        }

    }
}
