package net.minecraft.server;

public enum EnumHugeMushroomVariant implements INamable {

    NORTH_WEST(1, "north_west"), NORTH(2, "north"), NORTH_EAST(3, "north_east"), WEST(4, "west"), CENTER(5, "center"), EAST(6, "east"), SOUTH_WEST(7, "south_west"), SOUTH(8, "south"), SOUTH_EAST(9, "south_east"), STEM(10, "stem"), ALL_INSIDE(0, "all_inside"), ALL_OUTSIDE(14, "all_outside"), ALL_STEM(15, "all_stem");

    private static final EnumHugeMushroomVariant[] n = new EnumHugeMushroomVariant[16];
    private final int o;
    private final String p;

    private EnumHugeMushroomVariant(int i, String s) {
        this.o = i;
        this.p = s;
    }

    public int a() {
        return this.o;
    }

    public String toString() {
        return this.p;
    }

    public static EnumHugeMushroomVariant a(int i) {
        if (i < 0 || i >= EnumHugeMushroomVariant.n.length) {
            i = 0;
        }

        EnumHugeMushroomVariant enumhugemushroomvariant = EnumHugeMushroomVariant.n[i];

        return enumhugemushroomvariant == null ? EnumHugeMushroomVariant.n[0] : enumhugemushroomvariant;
    }

    public String getName() {
        return this.p;
    }

    static {
        EnumHugeMushroomVariant[] aenumhugemushroomvariant = values();
        int i = aenumhugemushroomvariant.length;

        for (int j = 0; j < i; ++j) {
            EnumHugeMushroomVariant enumhugemushroomvariant = aenumhugemushroomvariant[j];

            EnumHugeMushroomVariant.n[enumhugemushroomvariant.a()] = enumhugemushroomvariant;
        }

    }
}
