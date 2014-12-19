package net.minecraft.server;

class SwitchHelperDirection17 {

    static final int[] a = new int[EnumDirection.values().length];

    static {
        try {
            SwitchHelperDirection17.a[EnumDirection.NORTH.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror) {
            ;
        }

        try {
            SwitchHelperDirection17.a[EnumDirection.SOUTH.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror1) {
            ;
        }

        try {
            SwitchHelperDirection17.a[EnumDirection.WEST.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror2) {
            ;
        }

        try {
            SwitchHelperDirection17.a[EnumDirection.EAST.ordinal()] = 4;
        } catch (NoSuchFieldError nosuchfielderror3) {
            ;
        }

    }
}
