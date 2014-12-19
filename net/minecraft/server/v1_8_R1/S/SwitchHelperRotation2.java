package net.minecraft.server;

class SwitchHelperRotation2 {

    static final int[] a = new int[EnumLogRotation.values().length];

    static {
        try {
            SwitchHelperRotation2.a[EnumLogRotation.X.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror) {
            ;
        }

        try {
            SwitchHelperRotation2.a[EnumLogRotation.Z.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror1) {
            ;
        }

        try {
            SwitchHelperRotation2.a[EnumLogRotation.NONE.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror2) {
            ;
        }

    }
}
