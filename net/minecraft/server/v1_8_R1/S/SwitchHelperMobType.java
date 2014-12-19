package net.minecraft.server;

class SwitchHelperMobType {

    public static final int[] a = new int[EnumMobType.values().length];

    static {
        try {
            SwitchHelperMobType.a[EnumMobType.EVERYTHING.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror) {
            ;
        }

        try {
            SwitchHelperMobType.a[EnumMobType.MOBS.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror1) {
            ;
        }

    }
}
