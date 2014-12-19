package net.minecraft.server;

enum EnumProtocolState {

    HELLO, KEY, AUTHENTICATING, READY_TO_ACCEPT, ACCEPTED;

    private EnumProtocolState() {}
}
