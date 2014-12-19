package net.minecraft.server;

class SwitchHelperTileEntityPiston {

    public static final int[] a = new int[EnumAxis.values().length];

    static {
        try {
            SwitchHelperTileEntityPiston.a[EnumAxis.X.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror) {
            ;
        }

        try {
            SwitchHelperTileEntityPiston.a[EnumAxis.Y.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror1) {
            ;
        }

        try {
            SwitchHelperTileEntityPiston.a[EnumAxis.Z.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror2) {
            ;
        }

    }
}
