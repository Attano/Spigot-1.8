package net.minecraft.server;

class SwitchHelperBiomeBase {

    static final int[] switchMap = new int[EnumCreatureType.values().length];

    static {
        try {
            SwitchHelperBiomeBase.switchMap[EnumCreatureType.MONSTER.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror) {
            ;
        }

        try {
            SwitchHelperBiomeBase.switchMap[EnumCreatureType.CREATURE.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror1) {
            ;
        }

        try {
            SwitchHelperBiomeBase.switchMap[EnumCreatureType.WATER_CREATURE.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror2) {
            ;
        }

        try {
            SwitchHelperBiomeBase.switchMap[EnumCreatureType.AMBIENT.ordinal()] = 4;
        } catch (NoSuchFieldError nosuchfielderror3) {
            ;
        }

    }
}
