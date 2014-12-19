package net.minecraft.server;

public enum EnumStonebrickType implements INamable {

    DEFAULT(0, "stonebrick", "default"), MOSSY(1, "mossy_stonebrick", "mossy"), CRACKED(2, "cracked_stonebrick", "cracked"), CHISELED(3, "chiseled_stonebrick", "chiseled");

    private static final EnumStonebrickType[] e = new EnumStonebrickType[values().length];
    private final int f;
    private final String g;
    private final String h;

    private EnumStonebrickType(int i, String s, String s1) {
        this.f = i;
        this.g = s;
        this.h = s1;
    }

    public int a() {
        return this.f;
    }

    public String toString() {
        return this.g;
    }

    public static EnumStonebrickType a(int i) {
        if (i < 0 || i >= EnumStonebrickType.e.length) {
            i = 0;
        }

        return EnumStonebrickType.e[i];
    }

    public String getName() {
        return this.g;
    }

    public String c() {
        return this.h;
    }

    static {
        EnumStonebrickType[] aenumstonebricktype = values();
        int i = aenumstonebricktype.length;

        for (int j = 0; j < i; ++j) {
            EnumStonebrickType enumstonebricktype = aenumstonebricktype[j];

            EnumStonebrickType.e[enumstonebricktype.a()] = enumstonebricktype;
        }

    }
}
