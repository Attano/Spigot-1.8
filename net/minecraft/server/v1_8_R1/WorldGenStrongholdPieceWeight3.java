package net.minecraft.server;

class WorldGenStrongholdPieceWeight3 {

    static final int[] a;
    static final int[] b = new int[EnumDirection.values().length];

    static {
        try {
            WorldGenStrongholdPieceWeight3.b[EnumDirection.NORTH.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror) {
            ;
        }

        try {
            WorldGenStrongholdPieceWeight3.b[EnumDirection.SOUTH.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror1) {
            ;
        }

        try {
            WorldGenStrongholdPieceWeight3.b[EnumDirection.WEST.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror2) {
            ;
        }

        try {
            WorldGenStrongholdPieceWeight3.b[EnumDirection.EAST.ordinal()] = 4;
        } catch (NoSuchFieldError nosuchfielderror3) {
            ;
        }

        a = new int[WorldGenStrongholdDoorType.values().length];

        try {
            WorldGenStrongholdPieceWeight3.a[WorldGenStrongholdDoorType.OPENING.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror4) {
            ;
        }

        try {
            WorldGenStrongholdPieceWeight3.a[WorldGenStrongholdDoorType.WOOD_DOOR.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror5) {
            ;
        }

        try {
            WorldGenStrongholdPieceWeight3.a[WorldGenStrongholdDoorType.GRATES.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror6) {
            ;
        }

        try {
            WorldGenStrongholdPieceWeight3.a[WorldGenStrongholdDoorType.IRON_DOOR.ordinal()] = 4;
        } catch (NoSuchFieldError nosuchfielderror7) {
            ;
        }

    }
}
