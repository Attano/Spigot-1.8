package net.minecraft.server;

class WorldGenJungleTempleUnknown {

    static final int[] a = new int[EnumDirection.values().length];

    static {
        try {
            WorldGenJungleTempleUnknown.a[EnumDirection.NORTH.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror) {
            ;
        }

        try {
            WorldGenJungleTempleUnknown.a[EnumDirection.SOUTH.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror1) {
            ;
        }

    }
}
