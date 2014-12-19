package net.minecraft.server;

public enum EnumTrackPosition implements INamable {

    NORTH_SOUTH(0, "north_south"), EAST_WEST(1, "east_west"), ASCENDING_EAST(2, "ascending_east"), ASCENDING_WEST(3, "ascending_west"), ASCENDING_NORTH(4, "ascending_north"), ASCENDING_SOUTH(5, "ascending_south"), SOUTH_EAST(6, "south_east"), SOUTH_WEST(7, "south_west"), NORTH_WEST(8, "north_west"), NORTH_EAST(9, "north_east");

    private static final EnumTrackPosition[] k = new EnumTrackPosition[values().length];
    private final int l;
    private final String m;

    private EnumTrackPosition(int i, String s) {
        this.l = i;
        this.m = s;
    }

    public int a() {
        return this.l;
    }

    public String toString() {
        return this.m;
    }

    public boolean c() {
        return this == EnumTrackPosition.ASCENDING_NORTH || this == EnumTrackPosition.ASCENDING_EAST || this == EnumTrackPosition.ASCENDING_SOUTH || this == EnumTrackPosition.ASCENDING_WEST;
    }

    public static EnumTrackPosition a(int i) {
        if (i < 0 || i >= EnumTrackPosition.k.length) {
            i = 0;
        }

        return EnumTrackPosition.k[i];
    }

    public String getName() {
        return this.m;
    }

    static {
        EnumTrackPosition[] aenumtrackposition = values();
        int i = aenumtrackposition.length;

        for (int j = 0; j < i; ++j) {
            EnumTrackPosition enumtrackposition = aenumtrackposition[j];

            EnumTrackPosition.k[enumtrackposition.a()] = enumtrackposition;
        }

    }
}
