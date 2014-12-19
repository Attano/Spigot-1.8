package net.minecraft.server;

public enum EnumCobbleVariant implements INamable {

    NORMAL(0, "cobblestone", "normal"), MOSSY(1, "mossy_cobblestone", "mossy");

    private static final EnumCobbleVariant[] c = new EnumCobbleVariant[values().length];
    private final int d;
    private final String e;
    private String f;

    private EnumCobbleVariant(int i, String s, String s1) {
        this.d = i;
        this.e = s;
        this.f = s1;
    }

    public int a() {
        return this.d;
    }

    public String toString() {
        return this.e;
    }

    public static EnumCobbleVariant a(int i) {
        if (i < 0 || i >= EnumCobbleVariant.c.length) {
            i = 0;
        }

        return EnumCobbleVariant.c[i];
    }

    public String getName() {
        return this.e;
    }

    public String c() {
        return this.f;
    }

    static {
        EnumCobbleVariant[] aenumcobblevariant = values();
        int i = aenumcobblevariant.length;

        for (int j = 0; j < i; ++j) {
            EnumCobbleVariant enumcobblevariant = aenumcobblevariant[j];

            EnumCobbleVariant.c[enumcobblevariant.a()] = enumcobblevariant;
        }

    }
}
