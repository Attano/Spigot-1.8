package net.minecraft.server;

class SwitchHelperMonsterEggVarient {

    public static final int[] a = new int[EnumMonsterEggVarient.values().length];

    static {
        try {
            SwitchHelperMonsterEggVarient.a[EnumMonsterEggVarient.COBBLESTONE.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror) {
            ;
        }

        try {
            SwitchHelperMonsterEggVarient.a[EnumMonsterEggVarient.STONEBRICK.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror1) {
            ;
        }

        try {
            SwitchHelperMonsterEggVarient.a[EnumMonsterEggVarient.MOSSY_STONEBRICK.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror2) {
            ;
        }

        try {
            SwitchHelperMonsterEggVarient.a[EnumMonsterEggVarient.CRACKED_STONEBRICK.ordinal()] = 4;
        } catch (NoSuchFieldError nosuchfielderror3) {
            ;
        }

        try {
            SwitchHelperMonsterEggVarient.a[EnumMonsterEggVarient.CHISELED_STONEBRICK.ordinal()] = 5;
        } catch (NoSuchFieldError nosuchfielderror4) {
            ;
        }

    }
}
