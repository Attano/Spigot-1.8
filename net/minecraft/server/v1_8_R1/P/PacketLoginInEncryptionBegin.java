package net.minecraft.server;

import java.security.PrivateKey;
import javax.crypto.SecretKey;

public class PacketLoginInEncryptionBegin implements Packet {

    private byte[] a = new byte[0];
    private byte[] b = new byte[0];

    public PacketLoginInEncryptionBegin() {}

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.a();
        this.b = packetdataserializer.a();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.a(this.a);
        packetdataserializer.a(this.b);
    }

    public void a(PacketLoginInListener packetlogininlistener) {
        packetlogininlistener.a(this);
    }

    public SecretKey a(PrivateKey privatekey) {
        return MinecraftEncryption.a(privatekey, this.a);
    }

    public byte[] b(PrivateKey privatekey) {
        return privatekey == null ? this.b : MinecraftEncryption.b(privatekey, this.b);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketLoginInListener) packetlistener);
    }
}
