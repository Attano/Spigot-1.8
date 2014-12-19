package net.minecraft.server;

class SwitchHelperCommandActionType {

    public static final int[] a;
    public static final int[] b;
    public static final int[] c = new int[EnumClientCommand.values().length];

    static {
        try {
            SwitchHelperCommandActionType.c[EnumClientCommand.PERFORM_RESPAWN.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror) {
            ;
        }

        try {
            SwitchHelperCommandActionType.c[EnumClientCommand.REQUEST_STATS.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror1) {
            ;
        }

        try {
            SwitchHelperCommandActionType.c[EnumClientCommand.OPEN_INVENTORY_ACHIEVEMENT.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror2) {
            ;
        }

        b = new int[EnumPlayerAction.values().length];

        try {
            SwitchHelperCommandActionType.b[EnumPlayerAction.START_SNEAKING.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror3) {
            ;
        }

        try {
            SwitchHelperCommandActionType.b[EnumPlayerAction.STOP_SNEAKING.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror4) {
            ;
        }

        try {
            SwitchHelperCommandActionType.b[EnumPlayerAction.START_SPRINTING.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror5) {
            ;
        }

        try {
            SwitchHelperCommandActionType.b[EnumPlayerAction.STOP_SPRINTING.ordinal()] = 4;
        } catch (NoSuchFieldError nosuchfielderror6) {
            ;
        }

        try {
            SwitchHelperCommandActionType.b[EnumPlayerAction.STOP_SLEEPING.ordinal()] = 5;
        } catch (NoSuchFieldError nosuchfielderror7) {
            ;
        }

        try {
            SwitchHelperCommandActionType.b[EnumPlayerAction.RIDING_JUMP.ordinal()] = 6;
        } catch (NoSuchFieldError nosuchfielderror8) {
            ;
        }

        try {
            SwitchHelperCommandActionType.b[EnumPlayerAction.OPEN_INVENTORY.ordinal()] = 7;
        } catch (NoSuchFieldError nosuchfielderror9) {
            ;
        }

        a = new int[EnumPlayerDigType.values().length];

        try {
            SwitchHelperCommandActionType.a[EnumPlayerDigType.DROP_ITEM.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror10) {
            ;
        }

        try {
            SwitchHelperCommandActionType.a[EnumPlayerDigType.DROP_ALL_ITEMS.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror11) {
            ;
        }

        try {
            SwitchHelperCommandActionType.a[EnumPlayerDigType.RELEASE_USE_ITEM.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror12) {
            ;
        }

        try {
            SwitchHelperCommandActionType.a[EnumPlayerDigType.START_DESTROY_BLOCK.ordinal()] = 4;
        } catch (NoSuchFieldError nosuchfielderror13) {
            ;
        }

        try {
            SwitchHelperCommandActionType.a[EnumPlayerDigType.ABORT_DESTROY_BLOCK.ordinal()] = 5;
        } catch (NoSuchFieldError nosuchfielderror14) {
            ;
        }

        try {
            SwitchHelperCommandActionType.a[EnumPlayerDigType.STOP_DESTROY_BLOCK.ordinal()] = 6;
        } catch (NoSuchFieldError nosuchfielderror15) {
            ;
        }

    }
}
