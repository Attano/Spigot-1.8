package net.minecraft.server;

class SwitchHelperLogRotation {

    static final int[] a = new int[EnumLogRotation.values().length];

    static {
        try {
            SwitchHelperLogRotation.a[EnumLogRotation.X.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror) {
            ;
        }

        try {
            SwitchHelperLogRotation.a[EnumLogRotation.Z.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror1) {
            ;
        }

        try {
            SwitchHelperLogRotation.a[EnumLogRotation.NONE.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror2) {
            ;
        }

    }
}
