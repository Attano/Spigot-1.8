package net.minecraft.server;

class SwitchHelperWorldBorder {

    static final int[] a = new int[EnumWorldBorderAction.values().length];

    static {
        try {
            SwitchHelperWorldBorder.a[EnumWorldBorderAction.SET_SIZE.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror) {
            ;
        }

        try {
            SwitchHelperWorldBorder.a[EnumWorldBorderAction.LERP_SIZE.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror1) {
            ;
        }

        try {
            SwitchHelperWorldBorder.a[EnumWorldBorderAction.SET_CENTER.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror2) {
            ;
        }

        try {
            SwitchHelperWorldBorder.a[EnumWorldBorderAction.SET_WARNING_BLOCKS.ordinal()] = 4;
        } catch (NoSuchFieldError nosuchfielderror3) {
            ;
        }

        try {
            SwitchHelperWorldBorder.a[EnumWorldBorderAction.SET_WARNING_TIME.ordinal()] = 5;
        } catch (NoSuchFieldError nosuchfielderror4) {
            ;
        }

        try {
            SwitchHelperWorldBorder.a[EnumWorldBorderAction.INITIALIZE.ordinal()] = 6;
        } catch (NoSuchFieldError nosuchfielderror5) {
            ;
        }

    }
}
