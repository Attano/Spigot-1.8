package net.minecraft.server;

public enum EnumStoneSlab2Variant implements INamable {

    RED_SANDSTONE(0, "red_sandstone");

    private static final EnumStoneSlab2Variant[] b = new EnumStoneSlab2Variant[values().length];
    private final int c;
    private final String d;

    private EnumStoneSlab2Variant(int i, String s) {
        this.c = i;
        this.d = s;
    }

    public int a() {
        return this.c;
    }

    public String toString() {
        return this.d;
    }

    public static EnumStoneSlab2Variant a(int i) {
        if (i < 0 || i >= EnumStoneSlab2Variant.b.length) {
            i = 0;
        }

        return EnumStoneSlab2Variant.b[i];
    }

    public String getName() {
        return this.d;
    }

    public String c() {
        return this.d;
    }

    static {
        EnumStoneSlab2Variant[] aenumstoneslab2variant = values();
        int i = aenumstoneslab2variant.length;

        for (int j = 0; j < i; ++j) {
            EnumStoneSlab2Variant enumstoneslab2variant = aenumstoneslab2variant[j];

            EnumStoneSlab2Variant.b[enumstoneslab2variant.a()] = enumstoneslab2variant;
        }

    }
}
