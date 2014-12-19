package net.minecraft.server;

public enum EnumSandVariant implements INamable {

    SAND(0, "sand", "default", MaterialMapColor.d), RED_SAND(1, "red_sand", "red", MaterialMapColor.l);

    private static final EnumSandVariant[] c = new EnumSandVariant[values().length];
    private final int d;
    private final String e;
    private final MaterialMapColor f;
    private final String g;

    private EnumSandVariant(int i, String s, String s1, MaterialMapColor materialmapcolor) {
        this.d = i;
        this.e = s;
        this.f = materialmapcolor;
        this.g = s1;
    }

    public int a() {
        return this.d;
    }

    public String toString() {
        return this.e;
    }

    public MaterialMapColor c() {
        return this.f;
    }

    public static EnumSandVariant a(int i) {
        if (i < 0 || i >= EnumSandVariant.c.length) {
            i = 0;
        }

        return EnumSandVariant.c[i];
    }

    public String getName() {
        return this.e;
    }

    public String d() {
        return this.g;
    }

    static {
        EnumSandVariant[] aenumsandvariant = values();
        int i = aenumsandvariant.length;

        for (int j = 0; j < i; ++j) {
            EnumSandVariant enumsandvariant = aenumsandvariant[j];

            EnumSandVariant.c[enumsandvariant.a()] = enumsandvariant;
        }

    }
}
