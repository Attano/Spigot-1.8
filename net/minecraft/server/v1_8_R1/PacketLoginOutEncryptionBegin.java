package net.minecraft.server;

import java.security.PublicKey;

public class PacketLoginOutEncryptionBegin implements Packet {

    private String a;
    private PublicKey b;
    private byte[] c;

    public PacketLoginOutEncryptionBegin() {}

    public PacketLoginOutEncryptionBegin(String s, PublicKey publickey, byte[] abyte) {
        this.a = s;
        this.b = publickey;
        this.c = abyte;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.c(20);
        this.b = MinecraftEncryption.a(packetdataserializer.a());
        this.c = packetdataserializer.a();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.a(this.a);
        packetdataserializer.a(this.b.getEncoded());
        packetdataserializer.a(this.c);
    }

    public void a(PacketLoginOutListener packetloginoutlistener) {
        packetloginoutlistener.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketLoginOutListener) packetlistener);
    }
}
