package net.minecraft.server;

class SwitchHelperDirtVariant {

    static final int[] a = new int[EnumDirtVariant.values().length];

    static {
        try {
            SwitchHelperDirtVariant.a[EnumDirtVariant.DIRT.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror) {
            ;
        }

        try {
            SwitchHelperDirtVariant.a[EnumDirtVariant.COARSE_DIRT.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror1) {
            ;
        }

    }
}
