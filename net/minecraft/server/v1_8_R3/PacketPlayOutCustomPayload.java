package net.minecraft.server;

import io.netty.buffer.ByteBuf;
import java.io.IOException;

public class PacketPlayOutCustomPayload implements Packet<PacketListenerPlayOut> {

    private String a;
    private PacketDataSerializer b;

    public PacketPlayOutCustomPayload() {}

    public PacketPlayOutCustomPayload(String s, PacketDataSerializer packetdataserializer) {
        this.a = s;
        this.b = packetdataserializer;
        if (packetdataserializer.writerIndex() > 1048576) {
            throw new IllegalArgumentException("Payload may not be larger than 1048576 bytes");
        }
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.c(20);
        int i = packetdataserializer.readableBytes();

        if (i >= 0 && i <= 1048576) {
            this.b = new PacketDataSerializer(packetdataserializer.readBytes(i));
        } else {
            throw new IOException("Payload may not be larger than 1048576 bytes");
        }
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a(this.a);
        packetdataserializer.writeBytes((ByteBuf) this.b);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
