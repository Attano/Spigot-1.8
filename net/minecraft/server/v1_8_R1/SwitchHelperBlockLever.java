package net.minecraft.server;

class SwitchHelperBlockLever {

    public static final int[] a;
    public static final int[] b;
    public static final int[] c = new int[EnumAxis.values().length];

    static {
        try {
            SwitchHelperBlockLever.c[EnumAxis.X.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror) {
            ;
        }

        try {
            SwitchHelperBlockLever.c[EnumAxis.Z.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror1) {
            ;
        }

        b = new int[EnumLeverPosition.values().length];

        try {
            SwitchHelperBlockLever.b[EnumLeverPosition.EAST.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror2) {
            ;
        }

        try {
            SwitchHelperBlockLever.b[EnumLeverPosition.WEST.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror3) {
            ;
        }

        try {
            SwitchHelperBlockLever.b[EnumLeverPosition.SOUTH.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror4) {
            ;
        }

        try {
            SwitchHelperBlockLever.b[EnumLeverPosition.NORTH.ordinal()] = 4;
        } catch (NoSuchFieldError nosuchfielderror5) {
            ;
        }

        try {
            SwitchHelperBlockLever.b[EnumLeverPosition.UP_Z.ordinal()] = 5;
        } catch (NoSuchFieldError nosuchfielderror6) {
            ;
        }

        try {
            SwitchHelperBlockLever.b[EnumLeverPosition.UP_X.ordinal()] = 6;
        } catch (NoSuchFieldError nosuchfielderror7) {
            ;
        }

        try {
            SwitchHelperBlockLever.b[EnumLeverPosition.DOWN_X.ordinal()] = 7;
        } catch (NoSuchFieldError nosuchfielderror8) {
            ;
        }

        try {
            SwitchHelperBlockLever.b[EnumLeverPosition.DOWN_Z.ordinal()] = 8;
        } catch (NoSuchFieldError nosuchfielderror9) {
            ;
        }

        a = new int[EnumDirection.values().length];

        try {
            SwitchHelperBlockLever.a[EnumDirection.DOWN.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror10) {
            ;
        }

        try {
            SwitchHelperBlockLever.a[EnumDirection.UP.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror11) {
            ;
        }

        try {
            SwitchHelperBlockLever.a[EnumDirection.NORTH.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror12) {
            ;
        }

        try {
            SwitchHelperBlockLever.a[EnumDirection.SOUTH.ordinal()] = 4;
        } catch (NoSuchFieldError nosuchfielderror13) {
            ;
        }

        try {
            SwitchHelperBlockLever.a[EnumDirection.WEST.ordinal()] = 5;
        } catch (NoSuchFieldError nosuchfielderror14) {
            ;
        }

        try {
            SwitchHelperBlockLever.a[EnumDirection.EAST.ordinal()] = 6;
        } catch (NoSuchFieldError nosuchfielderror15) {
            ;
        }

    }
}
