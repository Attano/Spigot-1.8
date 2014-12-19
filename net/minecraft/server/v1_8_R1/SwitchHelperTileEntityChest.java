package net.minecraft.server;

class SwitchHelperTileEntityChest {

    public static final int[] a = new int[EnumDirection.values().length];

    static {
        try {
            SwitchHelperTileEntityChest.a[EnumDirection.NORTH.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror) {
            ;
        }

        try {
            SwitchHelperTileEntityChest.a[EnumDirection.SOUTH.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror1) {
            ;
        }

        try {
            SwitchHelperTileEntityChest.a[EnumDirection.EAST.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror2) {
            ;
        }

        try {
            SwitchHelperTileEntityChest.a[EnumDirection.WEST.ordinal()] = 4;
        } catch (NoSuchFieldError nosuchfielderror3) {
            ;
        }

    }
}
