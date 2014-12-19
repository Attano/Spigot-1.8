package net.minecraft.server;

public enum EnumRedSandstoneVariant implements INamable {

    DEFAULT(0, "red_sandstone", "default"), CHISELED(1, "chiseled_red_sandstone", "chiseled"), SMOOTH(2, "smooth_red_sandstone", "smooth");

    private static final EnumRedSandstoneVariant[] d = new EnumRedSandstoneVariant[values().length];
    private final int e;
    private final String f;
    private final String g;

    private EnumRedSandstoneVariant(int i, String s, String s1) {
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

    public static EnumRedSandstoneVariant a(int i) {
        if (i < 0 || i >= EnumRedSandstoneVariant.d.length) {
            i = 0;
        }

        return EnumRedSandstoneVariant.d[i];
    }

    public String getName() {
        return this.f;
    }

    public String c() {
        return this.g;
    }

    static {
        EnumRedSandstoneVariant[] aenumredsandstonevariant = values();
        int i = aenumredsandstonevariant.length;

        for (int j = 0; j < i; ++j) {
            EnumRedSandstoneVariant enumredsandstonevariant = aenumredsandstonevariant[j];

            EnumRedSandstoneVariant.d[enumredsandstonevariant.a()] = enumredsandstonevariant;
        }

    }
}
