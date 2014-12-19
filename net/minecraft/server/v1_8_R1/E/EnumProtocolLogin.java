package net.minecraft.server;

enum EnumProtocolLogin {;
    EnumProtocolLogin(int i) {
        this.a(EnumProtocolDirection.CLIENTBOUND, PacketLoginOutDisconnect.class);
        this.a(EnumProtocolDirection.CLIENTBOUND, PacketLoginOutEncryptionBegin.class);
        this.a(EnumProtocolDirection.CLIENTBOUND, PacketLoginOutSuccess.class);
        this.a(EnumProtocolDirection.CLIENTBOUND, PacketLoginOutSetCompression.class);
        this.a(EnumProtocolDirection.SERVERBOUND, PacketLoginInStart.class);
        this.a(EnumProtocolDirection.SERVERBOUND, PacketLoginInEncryptionBegin.class);
    }
}
