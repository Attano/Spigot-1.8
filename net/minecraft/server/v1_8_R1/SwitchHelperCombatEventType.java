package net.minecraft.server;

class SwitchHelperCombatEventType {

    static final int[] a = new int[EnumCombatEventType.values().length];

    static {
        try {
            SwitchHelperCombatEventType.a[EnumCombatEventType.END_COMBAT.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror) {
            ;
        }

        try {
            SwitchHelperCombatEventType.a[EnumCombatEventType.ENTITY_DIED.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror1) {
            ;
        }

    }
}
