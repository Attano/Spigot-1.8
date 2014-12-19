package net.minecraft.server;

class SwitchHelperDirection10 {

    public static final int[] a = new int[EnumDirection.values().length];

    static {
        try {
            SwitchHelperDirection10.a[EnumDirection.DOWN.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror) {
            ;
        }

        try {
            SwitchHelperDirection10.a[EnumDirection.UP.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror1) {
            ;
        }

        try {
            SwitchHelperDirection10.a[EnumDirection.NORTH.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror2) {
            ;
        }

        try {
            SwitchHelperDirection10.a[EnumDirection.SOUTH.ordinal()] = 4;
        } catch (NoSuchFieldError nosuchfielderror3) {
            ;
        }

        try {
            SwitchHelperDirection10.a[EnumDirection.WEST.ordinal()] = 5;
        } catch (NoSuchFieldError nosuchfielderror4) {
            ;
        }

        try {
            SwitchHelperDirection10.a[EnumDirection.EAST.ordinal()] = 6;
        } catch (NoSuchFieldError nosuchfielderror5) {
            ;
        }

    }
}
