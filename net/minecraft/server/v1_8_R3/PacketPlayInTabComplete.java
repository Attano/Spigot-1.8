package net.minecraft.server;

import java.io.IOException;
import org.apache.commons.lang3.StringUtils;

public class PacketPlayInTabComplete implements Packet<PacketListenerPlayIn> {

    private String a;
    private BlockPosition b;

    public PacketPlayInTabComplete() {}

    public PacketPlayInTabComplete(String s) {
        this(s, (BlockPosition) null);
    }

    public PacketPlayInTabComplete(String s, BlockPosition blockposition) {
        this.a = s;
        this.b = blockposition;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.c(32767);
        boolean flag = packetdataserializer.readBoolean();

        if (flag) {
            this.b = packetdataserializer.c();
        }

    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a(StringUtils.substring(this.a, 0, 32767));
        boolean flag = this.b != null;

        packetdataserializer.writeBoolean(flag);
        if (flag) {
            packetdataserializer.a(this.b);
        }

    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public String a() {
        return this.a;
    }

    public BlockPosition b() {
        return this.b;
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayIn) packetlistener);
    }
}
