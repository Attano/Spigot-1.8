package net.minecraft.server;

public enum EnumLeverPosition implements INamable {

    DOWN_X(0, "down_x", EnumDirection.DOWN), EAST(1, "east", EnumDirection.EAST), WEST(2, "west", EnumDirection.WEST), SOUTH(3, "south", EnumDirection.SOUTH), NORTH(4, "north", EnumDirection.NORTH), UP_Z(5, "up_z", EnumDirection.UP), UP_X(6, "up_x", EnumDirection.UP), DOWN_Z(7, "down_z", EnumDirection.DOWN);

    private static final EnumLeverPosition[] i = new EnumLeverPosition[values().length];
    private final int j;
    private final String k;
    private final EnumDirection l;

    private EnumLeverPosition(int i, String s, EnumDirection enumdirection) {
        this.j = i;
        this.k = s;
        this.l = enumdirection;
    }

    public int a() {
        return this.j;
    }

    public EnumDirection c() {
        return this.l;
    }

    public String toString() {
        return this.k;
    }

    public static EnumLeverPosition a(int i) {
        if (i < 0 || i >= EnumLeverPosition.i.length) {
            i = 0;
        }

        return EnumLeverPosition.i[i];
    }

    public static EnumLeverPosition a(EnumDirection enumdirection, EnumDirection enumdirection1) {
        switch (SwitchHelperBlockLever.a[enumdirection.ordinal()]) {
        case 1:
            switch (SwitchHelperBlockLever.c[enumdirection1.k().ordinal()]) {
            case 1:
                return EnumLeverPosition.DOWN_X;

            case 2:
                return EnumLeverPosition.DOWN_Z;

            default:
                throw new IllegalArgumentException("Invalid entityFacing " + enumdirection1 + " for facing " + enumdirection);
            }

        case 2:
            switch (SwitchHelperBlockLever.c[enumdirection1.k().ordinal()]) {
            case 1:
                return EnumLeverPosition.UP_X;

            case 2:
                return EnumLeverPosition.UP_Z;

            default:
                throw new IllegalArgumentException("Invalid entityFacing " + enumdirection1 + " for facing " + enumdirection);
            }

        case 3:
            return EnumLeverPosition.NORTH;

        case 4:
            return EnumLeverPosition.SOUTH;

        case 5:
            return EnumLeverPosition.WEST;

        case 6:
            return EnumLeverPosition.EAST;

        default:
            throw new IllegalArgumentException("Invalid facing: " + enumdirection);
        }
    }

    public String getName() {
        return this.k;
    }

    static {
        EnumLeverPosition[] aenumleverposition = values();
        int i = aenumleverposition.length;

        for (int j = 0; j < i; ++j) {
            EnumLeverPosition enumleverposition = aenumleverposition[j];

            EnumLeverPosition.i[enumleverposition.a()] = enumleverposition;
        }

    }
}
