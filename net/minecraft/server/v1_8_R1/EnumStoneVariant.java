package net.minecraft.server;

public enum EnumStoneVariant implements INamable {

    STONE(0, "stone"), GRANITE(1, "granite"), GRANITE_SMOOTH(2, "smooth_granite", "graniteSmooth"), DIORITE(3, "diorite"), DIORITE_SMOOTH(4, "smooth_diorite", "dioriteSmooth"), ANDESITE(5, "andesite"), ANDESITE_SMOOTH(6, "smooth_andesite", "andesiteSmooth");

    private static final EnumStoneVariant[] h = new EnumStoneVariant[values().length];
    private final int i;
    private final String j;
    private final String k;

    private EnumStoneVariant(int i, String s) {
        this(i, s, s);
    }

    private EnumStoneVariant(int i, String s, String s1) {
        this.i = i;
        this.j = s;
        this.k = s1;
    }

    public int a() {
        return this.i;
    }

    public String toString() {
        return this.j;
    }

    public static EnumStoneVariant a(int i) {
        if (i < 0 || i >= EnumStoneVariant.h.length) {
            i = 0;
        }

        return EnumStoneVariant.h[i];
    }

    public String getName() {
        return this.j;
    }

    public String c() {
        return this.k;
    }

    static {
        EnumStoneVariant[] aenumstonevariant = values();
        int i = aenumstonevariant.length;

        for (int j = 0; j < i; ++j) {
            EnumStoneVariant enumstonevariant = aenumstonevariant[j];

            EnumStoneVariant.h[enumstonevariant.a()] = enumstonevariant;
        }

    }
}
