package net.minecraft.server;

class SwitchHelperEntityMinecartAbstract {

    public static final int[] a;
    public static final int[] b = new int[EnumTrackPosition.values().length];

    static {
        try {
            SwitchHelperEntityMinecartAbstract.b[EnumTrackPosition.ASCENDING_EAST.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror) {
            ;
        }

        try {
            SwitchHelperEntityMinecartAbstract.b[EnumTrackPosition.ASCENDING_WEST.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror1) {
            ;
        }

        try {
            SwitchHelperEntityMinecartAbstract.b[EnumTrackPosition.ASCENDING_NORTH.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror2) {
            ;
        }

        try {
            SwitchHelperEntityMinecartAbstract.b[EnumTrackPosition.ASCENDING_SOUTH.ordinal()] = 4;
        } catch (NoSuchFieldError nosuchfielderror3) {
            ;
        }

        a = new int[EnumMinecartType.values().length];

        try {
            SwitchHelperEntityMinecartAbstract.a[EnumMinecartType.CHEST.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror4) {
            ;
        }

        try {
            SwitchHelperEntityMinecartAbstract.a[EnumMinecartType.FURNACE.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror5) {
            ;
        }

        try {
            SwitchHelperEntityMinecartAbstract.a[EnumMinecartType.TNT.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror6) {
            ;
        }

        try {
            SwitchHelperEntityMinecartAbstract.a[EnumMinecartType.SPAWNER.ordinal()] = 4;
        } catch (NoSuchFieldError nosuchfielderror7) {
            ;
        }

        try {
            SwitchHelperEntityMinecartAbstract.a[EnumMinecartType.HOPPER.ordinal()] = 5;
        } catch (NoSuchFieldError nosuchfielderror8) {
            ;
        }

        try {
            SwitchHelperEntityMinecartAbstract.a[EnumMinecartType.COMMAND_BLOCK.ordinal()] = 6;
        } catch (NoSuchFieldError nosuchfielderror9) {
            ;
        }

    }
}
