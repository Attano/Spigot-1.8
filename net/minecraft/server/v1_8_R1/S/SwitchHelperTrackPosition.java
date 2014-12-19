package net.minecraft.server;

class SwitchHelperTrackPosition {

    public static final int[] a = new int[EnumTrackPosition.values().length];

    static {
        try {
            SwitchHelperTrackPosition.a[EnumTrackPosition.NORTH_SOUTH.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror) {
            ;
        }

        try {
            SwitchHelperTrackPosition.a[EnumTrackPosition.EAST_WEST.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror1) {
            ;
        }

        try {
            SwitchHelperTrackPosition.a[EnumTrackPosition.ASCENDING_EAST.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror2) {
            ;
        }

        try {
            SwitchHelperTrackPosition.a[EnumTrackPosition.ASCENDING_WEST.ordinal()] = 4;
        } catch (NoSuchFieldError nosuchfielderror3) {
            ;
        }

        try {
            SwitchHelperTrackPosition.a[EnumTrackPosition.ASCENDING_NORTH.ordinal()] = 5;
        } catch (NoSuchFieldError nosuchfielderror4) {
            ;
        }

        try {
            SwitchHelperTrackPosition.a[EnumTrackPosition.ASCENDING_SOUTH.ordinal()] = 6;
        } catch (NoSuchFieldError nosuchfielderror5) {
            ;
        }

    }
}
