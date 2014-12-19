package net.minecraft.server;

class SwitchHelperDirection14 {

    static final int[] a = new int[EnumDirection.values().length];

    static {
        try {
            SwitchHelperDirection14.a[EnumDirection.EAST.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror) {
            ;
        }

        try {
            SwitchHelperDirection14.a[EnumDirection.WEST.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror1) {
            ;
        }

        try {
            SwitchHelperDirection14.a[EnumDirection.SOUTH.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror2) {
            ;
        }

        try {
            SwitchHelperDirection14.a[EnumDirection.NORTH.ordinal()] = 4;
        } catch (NoSuchFieldError nosuchfielderror3) {
            ;
        }

        try {
            SwitchHelperDirection14.a[EnumDirection.DOWN.ordinal()] = 5;
        } catch (NoSuchFieldError nosuchfielderror4) {
            ;
        }

        try {
            SwitchHelperDirection14.a[EnumDirection.UP.ordinal()] = 6;
        } catch (NoSuchFieldError nosuchfielderror5) {
            ;
        }

    }
}
