package net.minecraft.server;

class GenLayerJumpTable {

    static final int[] a = new int[EnumGenLayerSpecial.values().length];

    static {
        try {
            GenLayerJumpTable.a[EnumGenLayerSpecial.COOL_WARM.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror) {
            ;
        }

        try {
            GenLayerJumpTable.a[EnumGenLayerSpecial.HEAT_ICE.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror1) {
            ;
        }

        try {
            GenLayerJumpTable.a[EnumGenLayerSpecial.SPECIAL.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror2) {
            ;
        }

    }
}
