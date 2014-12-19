package net.minecraft.server;

class ProtocolOrdinalWrapper {

    public static final int[] a = new int[EnumProtocol.values().length];

    static {
        try {
            ProtocolOrdinalWrapper.a[EnumProtocol.LOGIN.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror) {
            ;
        }

        try {
            ProtocolOrdinalWrapper.a[EnumProtocol.STATUS.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror1) {
            ;
        }

    }
}
