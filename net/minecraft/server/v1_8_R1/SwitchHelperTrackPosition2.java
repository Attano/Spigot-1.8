package net.minecraft.server;

class SwitchHelperTrackPosition2 {

    static final int[] a = new int[EnumTrackPosition.values().length];

    static {
        try {
            SwitchHelperTrackPosition2.a[EnumTrackPosition.NORTH_SOUTH.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror) {
            ;
        }

        try {
            SwitchHelperTrackPosition2.a[EnumTrackPosition.EAST_WEST.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror1) {
            ;
        }

        try {
            SwitchHelperTrackPosition2.a[EnumTrackPosition.ASCENDING_EAST.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror2) {
            ;
        }

        try {
            SwitchHelperTrackPosition2.a[EnumTrackPosition.ASCENDING_WEST.ordinal()] = 4;
        } catch (NoSuchFieldError nosuchfielderror3) {
            ;
        }

        try {
            SwitchHelperTrackPosition2.a[EnumTrackPosition.ASCENDING_NORTH.ordinal()] = 5;
        } catch (NoSuchFieldError nosuchfielderror4) {
            ;
        }

        try {
            SwitchHelperTrackPosition2.a[EnumTrackPosition.ASCENDING_SOUTH.ordinal()] = 6;
        } catch (NoSuchFieldError nosuchfielderror5) {
            ;
        }

        try {
            SwitchHelperTrackPosition2.a[EnumTrackPosition.SOUTH_EAST.ordinal()] = 7;
        } catch (NoSuchFieldError nosuchfielderror6) {
            ;
        }

        try {
            SwitchHelperTrackPosition2.a[EnumTrackPosition.SOUTH_WEST.ordinal()] = 8;
        } catch (NoSuchFieldError nosuchfielderror7) {
            ;
        }

        try {
            SwitchHelperTrackPosition2.a[EnumTrackPosition.NORTH_WEST.ordinal()] = 9;
        } catch (NoSuchFieldError nosuchfielderror8) {
            ;
        }

        try {
            SwitchHelperTrackPosition2.a[EnumTrackPosition.NORTH_EAST.ordinal()] = 10;
        } catch (NoSuchFieldError nosuchfielderror9) {
            ;
        }

    }
}
