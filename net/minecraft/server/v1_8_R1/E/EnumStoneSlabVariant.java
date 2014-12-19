package net.minecraft.server;

public enum EnumStoneSlabVariant implements INamable {

    STONE(0, "stone"), SAND(1, "sandstone", "sand"), WOOD(2, "wood_old", "wood"), COBBLESTONE(3, "cobblestone", "cobble"), BRICK(4, "brick"), SMOOTHBRICK(5, "stone_brick", "smoothStoneBrick"), NETHERBRICK(6, "nether_brick", "netherBrick"), QUARTZ(7, "quartz");

    private static final EnumStoneSlabVariant[] i = new EnumStoneSlabVariant[values().length];
    private final int j;
    private final String k;
    private final String l;

    private EnumStoneSlabVariant(int i, String s) {
        this(i, s, s);
    }

    private EnumStoneSlabVariant(int i, String s, String s1) {
        this.j = i;
        this.k = s;
        this.l = s1;
    }

    public int a() {
        return this.j;
    }

    public String toString() {
        return this.k;
    }

    public static EnumStoneSlabVariant a(int i) {
        if (i < 0 || i >= EnumStoneSlabVariant.i.length) {
            i = 0;
        }

        return EnumStoneSlabVariant.i[i];
    }

    public String getName() {
        return this.k;
    }

    public String c() {
        return this.l;
    }

    static {
        EnumStoneSlabVariant[] aenumstoneslabvariant = values();
        int i = aenumstoneslabvariant.length;

        for (int j = 0; j < i; ++j) {
            EnumStoneSlabVariant enumstoneslabvariant = aenumstoneslabvariant[j];

            EnumStoneSlabVariant.i[enumstoneslabvariant.a()] = enumstoneslabvariant;
        }

    }
}
