package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutTabComplete implements Packet<PacketListenerPlayOut> {

    private String[] a;

    public PacketPlayOutTabComplete() {}

    public PacketPlayOutTabComplete(String[] astring) {
        this.a = astring;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = new String[packetdataserializer.e()];

        for (int i = 0; i < this.a.length; ++i) {
            this.a[i] = packetdataserializer.c(32767);
        }

    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.b(this.a.length);
        String[] astring = this.a;
        int i = astring.length;

        for (int j = 0; j < i; ++j) {
            String s = astring[j];

            packetdataserializer.a(s);
        }

    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
