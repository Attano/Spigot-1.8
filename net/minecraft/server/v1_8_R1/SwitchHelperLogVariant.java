package net.minecraft.server;

// CraftBukkit - imported for visibility
class SwitchHelperLogVariant {

    static final int[] a = new int[EnumLogVariant.values().length];

    static {
        try {
            SwitchHelperLogVariant.a[EnumLogVariant.SPRUCE.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror) {
            ;
        }

        try {
            SwitchHelperLogVariant.a[EnumLogVariant.BIRCH.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror1) {
            ;
        }

        try {
            SwitchHelperLogVariant.a[EnumLogVariant.JUNGLE.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror2) {
            ;
        }

        try {
            SwitchHelperLogVariant.a[EnumLogVariant.ACACIA.ordinal()] = 4;
        } catch (NoSuchFieldError nosuchfielderror3) {
            ;
        }

        try {
            SwitchHelperLogVariant.a[EnumLogVariant.DARK_OAK.ordinal()] = 5;
        } catch (NoSuchFieldError nosuchfielderror4) {
            ;
        }

        try {
            SwitchHelperLogVariant.a[EnumLogVariant.OAK.ordinal()] = 6;
        } catch (NoSuchFieldError nosuchfielderror5) {
            ;
        }

    }
}
