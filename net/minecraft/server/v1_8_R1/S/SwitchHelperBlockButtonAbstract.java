package net.minecraft.server;

class SwitchHelperBlockButtonAbstract {

    public static final int[] a = new int[EnumDirection.values().length];

    static {
        try {
            SwitchHelperBlockButtonAbstract.a[EnumDirection.EAST.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror) {
            ;
        }

        try {
            SwitchHelperBlockButtonAbstract.a[EnumDirection.WEST.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror1) {
            ;
        }

        try {
            SwitchHelperBlockButtonAbstract.a[EnumDirection.SOUTH.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror2) {
            ;
        }

        try {
            SwitchHelperBlockButtonAbstract.a[EnumDirection.NORTH.ordinal()] = 4;
        } catch (NoSuchFieldError nosuchfielderror3) {
            ;
        }

        try {
            SwitchHelperBlockButtonAbstract.a[EnumDirection.UP.ordinal()] = 5;
        } catch (NoSuchFieldError nosuchfielderror4) {
            ;
        }

        try {
            SwitchHelperBlockButtonAbstract.a[EnumDirection.DOWN.ordinal()] = 6;
        } catch (NoSuchFieldError nosuchfielderror5) {
            ;
        }

    }
}
