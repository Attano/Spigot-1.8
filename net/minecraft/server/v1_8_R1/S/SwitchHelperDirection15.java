package net.minecraft.server;

class SwitchHelperDirection15 {

    public static final int[] a = new int[EnumDirection.values().length];

    static {
        try {
            SwitchHelperDirection15.a[EnumDirection.UP.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror) {
            ;
        }

        try {
            SwitchHelperDirection15.a[EnumDirection.NORTH.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror1) {
            ;
        }

        try {
            SwitchHelperDirection15.a[EnumDirection.SOUTH.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror2) {
            ;
        }

        try {
            SwitchHelperDirection15.a[EnumDirection.WEST.ordinal()] = 4;
        } catch (NoSuchFieldError nosuchfielderror3) {
            ;
        }

        try {
            SwitchHelperDirection15.a[EnumDirection.EAST.ordinal()] = 5;
        } catch (NoSuchFieldError nosuchfielderror4) {
            ;
        }

    }
}
