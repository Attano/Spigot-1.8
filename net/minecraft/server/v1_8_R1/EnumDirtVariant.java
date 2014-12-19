package net.minecraft.server;

public enum EnumDirtVariant implements INamable {

    DIRT(0, "dirt", "default"), COARSE_DIRT(1, "coarse_dirt", "coarse"), PODZOL(2, "podzol");

    private static final EnumDirtVariant[] d = new EnumDirtVariant[values().length];
    private final int e;
    private final String f;
    private final String g;

    private EnumDirtVariant(int i, String s) {
        this(i, s, s);
    }

    private EnumDirtVariant(int i, String s, String s1) {
        this.e = i;
        this.f = s;
        this.g = s1;
    }

    public int a() {
        return this.e;
    }

    public String c() {
        return this.g;
    }

    public String toString() {
        return this.f;
    }

    public static EnumDirtVariant a(int i) {
        if (i < 0 || i >= EnumDirtVariant.d.length) {
            i = 0;
        }

        return EnumDirtVariant.d[i];
    }

    public String getName() {
        return this.f;
    }

    static {
        EnumDirtVariant[] aenumdirtvariant = values();
        int i = aenumdirtvariant.length;

        for (int j = 0; j < i; ++j) {
            EnumDirtVariant enumdirtvariant = aenumdirtvariant[j];

            EnumDirtVariant.d[enumdirtvariant.a()] = enumdirtvariant;
        }

    }
}
