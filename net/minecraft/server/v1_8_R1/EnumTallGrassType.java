package net.minecraft.server;

public enum EnumTallGrassType implements INamable {

    DEAD_BUSH(0, "dead_bush"), GRASS(1, "tall_grass"), FERN(2, "fern");

    private static final EnumTallGrassType[] d = new EnumTallGrassType[values().length];
    private final int e;
    private final String f;

    private EnumTallGrassType(int i, String s) {
        this.e = i;
        this.f = s;
    }

    public int a() {
        return this.e;
    }

    public String toString() {
        return this.f;
    }

    public static EnumTallGrassType a(int i) {
        if (i < 0 || i >= EnumTallGrassType.d.length) {
            i = 0;
        }

        return EnumTallGrassType.d[i];
    }

    public String getName() {
        return this.f;
    }

    static {
        EnumTallGrassType[] aenumtallgrasstype = values();
        int i = aenumtallgrasstype.length;

        for (int j = 0; j < i; ++j) {
            EnumTallGrassType enumtallgrasstype = aenumtallgrasstype[j];

            EnumTallGrassType.d[enumtallgrasstype.a()] = enumtallgrasstype;
        }

    }
}
