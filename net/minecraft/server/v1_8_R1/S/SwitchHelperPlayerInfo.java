package net.minecraft.server;

class SwitchHelperPlayerInfo {

    static final int[] a = new int[EnumPlayerInfoAction.values().length];

    static {
        try {
            SwitchHelperPlayerInfo.a[EnumPlayerInfoAction.ADD_PLAYER.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror) {
            ;
        }

        try {
            SwitchHelperPlayerInfo.a[EnumPlayerInfoAction.UPDATE_GAME_MODE.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror1) {
            ;
        }

        try {
            SwitchHelperPlayerInfo.a[EnumPlayerInfoAction.UPDATE_LATENCY.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror2) {
            ;
        }

        try {
            SwitchHelperPlayerInfo.a[EnumPlayerInfoAction.UPDATE_DISPLAY_NAME.ordinal()] = 4;
        } catch (NoSuchFieldError nosuchfielderror3) {
            ;
        }

        try {
            SwitchHelperPlayerInfo.a[EnumPlayerInfoAction.REMOVE_PLAYER.ordinal()] = 5;
        } catch (NoSuchFieldError nosuchfielderror4) {
            ;
        }

    }
}
