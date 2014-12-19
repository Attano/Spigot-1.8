package net.minecraft.server;

public enum EnumLogVariant implements INamable {

    OAK(0, "oak"), SPRUCE(1, "spruce"), BIRCH(2, "birch"), JUNGLE(3, "jungle"), ACACIA(4, "acacia"), DARK_OAK(5, "dark_oak", "big_oak");

    private static final EnumLogVariant[] g = new EnumLogVariant[values().length];
    private final int h;
    private final String i;
    private final String j;

    private EnumLogVariant(int i, String s) {
        this(i, s, s);
    }

    private EnumLogVariant(int i, String s, String s1) {
        this.h = i;
        this.i = s;
        this.j = s1;
    }

    public int a() {
        return this.h;
    }

    public String toString() {
        return this.i;
    }

    public static EnumLogVariant a(int i) {
        if (i < 0 || i >= EnumLogVariant.g.length) {
            i = 0;
        }

        return EnumLogVariant.g[i];
    }

    public String getName() {
        return this.i;
    }

    public String c() {
        return this.j;
    }

    static {
        EnumLogVariant[] aenumlogvariant = values();
        int i = aenumlogvariant.length;

        for (int j = 0; j < i; ++j) {
            EnumLogVariant enumlogvariant = aenumlogvariant[j];

            EnumLogVariant.g[enumlogvariant.a()] = enumlogvariant;
        }

    }
}
