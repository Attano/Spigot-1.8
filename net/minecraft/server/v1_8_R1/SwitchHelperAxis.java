package net.minecraft.server;

class SwitchHelperAxis {

    static final int[] a = new int[EnumAxis.values().length];

    static {
        try {
            SwitchHelperAxis.a[EnumAxis.Z.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror) {
            ;
        }

        try {
            SwitchHelperAxis.a[EnumAxis.X.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror1) {
            ;
        }

        try {
            SwitchHelperAxis.a[EnumAxis.Y.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror2) {
            ;
        }

    }
}
