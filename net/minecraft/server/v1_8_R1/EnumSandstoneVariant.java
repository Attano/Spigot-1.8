package net.minecraft.server;

public enum EnumSandstoneVariant implements INamable {

    DEFAULT(0, "sandstone", "default"), CHISELED(1, "chiseled_sandstone", "chiseled"), SMOOTH(2, "smooth_sandstone", "smooth");

    private static final EnumSandstoneVariant[] d = new EnumSandstoneVariant[values().length];
    private final int e;
    private final String f;
    private final String g;

    private EnumSandstoneVariant(int i, String s, String s1) {
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

    public static EnumSandstoneVariant a(int i) {
        if (i < 0 || i >= EnumSandstoneVariant.d.length) {
            i = 0;
        }

        return EnumSandstoneVariant.d[i];
    }

    public String getName() {
        return this.f;
    }

    public String c() {
        return this.g;
    }

    static {
        EnumSandstoneVariant[] aenumsandstonevariant = values();
        int i = aenumsandstonevariant.length;

        for (int j = 0; j < i; ++j) {
            EnumSandstoneVariant enumsandstonevariant = aenumsandstonevariant[j];

            EnumSandstoneVariant.d[enumsandstonevariant.a()] = enumsandstonevariant;
        }

    }
}
