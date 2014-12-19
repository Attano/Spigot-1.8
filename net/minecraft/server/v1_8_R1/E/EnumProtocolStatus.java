package net.minecraft.server;

enum EnumProtocolStatus {;
    EnumProtocolStatus(int i) {
        this.a(EnumProtocolDirection.SERVERBOUND, PacketStatusInStart.class);
        this.a(EnumProtocolDirection.CLIENTBOUND, PacketStatusOutServerInfo.class);
        this.a(EnumProtocolDirection.SERVERBOUND, PacketStatusInPing.class);
        this.a(EnumProtocolDirection.CLIENTBOUND, PacketStatusOutPong.class);
    }
}
