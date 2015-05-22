package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutEntityDestroy implements Packet<PacketListenerPlayOut> {

    private int[] a;

    public PacketPlayOutEntityDestroy() {}

    public PacketPlayOutEntityDestroy(int... aint) {
        this.a = aint;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = new int[packetdataserializer.e()];

        for (int i = 0; i < this.a.length; ++i) {
            this.a[i] = packetdataserializer.e();
        }

    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.b(this.a.length);

        for (int i = 0; i < this.a.length; ++i) {
            packetdataserializer.b(this.a[i]);
        }

    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
