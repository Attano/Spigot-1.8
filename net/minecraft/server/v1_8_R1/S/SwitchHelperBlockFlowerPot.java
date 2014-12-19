package net.minecraft.server;

class SwitchHelperBlockFlowerPot {

    static final int[] a;
    static final int[] b = new int[EnumFlowerVarient.values().length];

    static {
        try {
            SwitchHelperBlockFlowerPot.b[EnumFlowerVarient.POPPY.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror) {
            ;
        }

        try {
            SwitchHelperBlockFlowerPot.b[EnumFlowerVarient.BLUE_ORCHID.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror1) {
            ;
        }

        try {
            SwitchHelperBlockFlowerPot.b[EnumFlowerVarient.ALLIUM.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror2) {
            ;
        }

        try {
            SwitchHelperBlockFlowerPot.b[EnumFlowerVarient.HOUSTONIA.ordinal()] = 4;
        } catch (NoSuchFieldError nosuchfielderror3) {
            ;
        }

        try {
            SwitchHelperBlockFlowerPot.b[EnumFlowerVarient.RED_TULIP.ordinal()] = 5;
        } catch (NoSuchFieldError nosuchfielderror4) {
            ;
        }

        try {
            SwitchHelperBlockFlowerPot.b[EnumFlowerVarient.ORANGE_TULIP.ordinal()] = 6;
        } catch (NoSuchFieldError nosuchfielderror5) {
            ;
        }

        try {
            SwitchHelperBlockFlowerPot.b[EnumFlowerVarient.WHITE_TULIP.ordinal()] = 7;
        } catch (NoSuchFieldError nosuchfielderror6) {
            ;
        }

        try {
            SwitchHelperBlockFlowerPot.b[EnumFlowerVarient.PINK_TULIP.ordinal()] = 8;
        } catch (NoSuchFieldError nosuchfielderror7) {
            ;
        }

        try {
            SwitchHelperBlockFlowerPot.b[EnumFlowerVarient.OXEYE_DAISY.ordinal()] = 9;
        } catch (NoSuchFieldError nosuchfielderror8) {
            ;
        }

        a = new int[EnumLogVariant.values().length];

        try {
            SwitchHelperBlockFlowerPot.a[EnumLogVariant.OAK.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror9) {
            ;
        }

        try {
            SwitchHelperBlockFlowerPot.a[EnumLogVariant.SPRUCE.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror10) {
            ;
        }

        try {
            SwitchHelperBlockFlowerPot.a[EnumLogVariant.BIRCH.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror11) {
            ;
        }

        try {
            SwitchHelperBlockFlowerPot.a[EnumLogVariant.JUNGLE.ordinal()] = 4;
        } catch (NoSuchFieldError nosuchfielderror12) {
            ;
        }

        try {
            SwitchHelperBlockFlowerPot.a[EnumLogVariant.ACACIA.ordinal()] = 5;
        } catch (NoSuchFieldError nosuchfielderror13) {
            ;
        }

        try {
            SwitchHelperBlockFlowerPot.a[EnumLogVariant.DARK_OAK.ordinal()] = 6;
        } catch (NoSuchFieldError nosuchfielderror14) {
            ;
        }

    }
}
