package net.minecraft.server;

public enum EnumQuartzVariant implements INamable {

    DEFAULT(0, "default", "default"), CHISELED(1, "chiseled", "chiseled"), LINES_Y(2, "lines_y", "lines"), LINES_X(3, "lines_x", "lines"), LINES_Z(4, "lines_z", "lines");

    private static final EnumQuartzVariant[] f = new EnumQuartzVariant[values().length];
    private final int g;
    private final String h;
    private final String i;

    private EnumQuartzVariant(int i, String s, String s1) {
        this.g = i;
        this.h = s;
        this.i = s1;
    }

    public int a() {
        return this.g;
    }

    public String toString() {
        return this.i;
    }

    public static EnumQuartzVariant a(int i) {
        if (i < 0 || i >= EnumQuartzVariant.f.length) {
            i = 0;
        }

        return EnumQuartzVariant.f[i];
    }

    public String getName() {
        return this.h;
    }

    static {
        EnumQuartzVariant[] aenumquartzvariant = values();
        int i = aenumquartzvariant.length;

        for (int j = 0; j < i; ++j) {
            EnumQuartzVariant enumquartzvariant = aenumquartzvariant[j];

            EnumQuartzVariant.f[enumquartzvariant.a()] = enumquartzvariant;
        }

    }
}
