package net.minecraft.server;

class SwitchHelperAxis2 {

    static final int[] a = new int[EnumAxis.values().length];

    static {
        try {
            SwitchHelperAxis2.a[EnumAxis.X.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror) {
            ;
        }

        try {
            SwitchHelperAxis2.a[EnumAxis.Y.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror1) {
            ;
        }

        try {
            SwitchHelperAxis2.a[EnumAxis.Z.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror2) {
            ;
        }

    }
}
