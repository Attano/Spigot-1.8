package net.minecraft.server;

import io.netty.buffer.ByteBuf;
import java.io.IOException;

public class PacketPlayInCustomPayload implements Packet<PacketListenerPlayIn> {

    private String a;
    private PacketDataSerializer b;

    public PacketPlayInCustomPayload() {}

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.c(20);
        int i = packetdataserializer.readableBytes();

        if (i >= 0 && i <= 32767) {
            this.b = new PacketDataSerializer(packetdataserializer.readBytes(i));
        } else {
            throw new IOException("Payload may not be larger than 32767 bytes");
        }
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a(this.a);
        packetdataserializer.writeBytes((ByteBuf) this.b);
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public String a() {
        return this.a;
    }

    public PacketDataSerializer b() {
        return this.b;
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayIn) packetlistener);
    }
}
