package net.minecraft.server;

public enum EnumTallFlowerVariants implements INamable {

    SUNFLOWER(0, "sunflower"), SYRINGA(1, "syringa"), GRASS(2, "double_grass", "grass"), FERN(3, "double_fern", "fern"), ROSE(4, "double_rose", "rose"), PAEONIA(5, "paeonia");

    private static final EnumTallFlowerVariants[] g = new EnumTallFlowerVariants[values().length];
    private final int h;
    private final String i;
    private final String j;

    private EnumTallFlowerVariants(int i, String s) {
        this(i, s, s);
    }

    private EnumTallFlowerVariants(int i, String s, String s1) {
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

    public static EnumTallFlowerVariants a(int i) {
        if (i < 0 || i >= EnumTallFlowerVariants.g.length) {
            i = 0;
        }

        return EnumTallFlowerVariants.g[i];
    }

    public String getName() {
        return this.i;
    }

    public String c() {
        return this.j;
    }

    static {
        EnumTallFlowerVariants[] aenumtallflowervariants = values();
        int i = aenumtallflowervariants.length;

        for (int j = 0; j < i; ++j) {
            EnumTallFlowerVariants enumtallflowervariants = aenumtallflowervariants[j];

            EnumTallFlowerVariants.g[enumtallflowervariants.a()] = enumtallflowervariants;
        }

    }
}
