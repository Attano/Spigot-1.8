package net.minecraft.server;

class SwitchHelperDirection2 {

    static final int[] a;
    static final int[] b;
    static final int[] c = new int[EnumDirectionLimit.values().length];

    static {
        try {
            SwitchHelperDirection2.c[EnumDirectionLimit.HORIZONTAL.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror) {
            ;
        }

        try {
            SwitchHelperDirection2.c[EnumDirectionLimit.VERTICAL.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror1) {
            ;
        }

        b = new int[EnumDirection.values().length];

        try {
            SwitchHelperDirection2.b[EnumDirection.NORTH.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror2) {
            ;
        }

        try {
            SwitchHelperDirection2.b[EnumDirection.EAST.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror3) {
            ;
        }

        try {
            SwitchHelperDirection2.b[EnumDirection.SOUTH.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror4) {
            ;
        }

        try {
            SwitchHelperDirection2.b[EnumDirection.WEST.ordinal()] = 4;
        } catch (NoSuchFieldError nosuchfielderror5) {
            ;
        }

        try {
            SwitchHelperDirection2.b[EnumDirection.UP.ordinal()] = 5;
        } catch (NoSuchFieldError nosuchfielderror6) {
            ;
        }

        try {
            SwitchHelperDirection2.b[EnumDirection.DOWN.ordinal()] = 6;
        } catch (NoSuchFieldError nosuchfielderror7) {
            ;
        }

        a = new int[EnumAxis.values().length];

        try {
            SwitchHelperDirection2.a[EnumAxis.X.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror8) {
            ;
        }

        try {
            SwitchHelperDirection2.a[EnumAxis.Y.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror9) {
            ;
        }

        try {
            SwitchHelperDirection2.a[EnumAxis.Z.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror10) {
            ;
        }

    }
}
