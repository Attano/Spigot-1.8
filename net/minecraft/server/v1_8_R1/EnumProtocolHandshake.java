package net.minecraft.server;

enum EnumProtocolHandshake {;
    EnumProtocolHandshake(int i) {
        this.a(EnumProtocolDirection.SERVERBOUND, PacketHandshakingInSetProtocol.class);
    }
}
