package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutSpawnEntityWeather implements Packet<PacketListenerPlayOut> {

    private int a;
    private int b;
    private int c;
    private int d;
    private int e;

    public PacketPlayOutSpawnEntityWeather() {}

    public PacketPlayOutSpawnEntityWeather(Entity entity) {
        this.a = entity.getId();
        this.b = MathHelper.floor(entity.locX * 32.0D);
        this.c = MathHelper.floor(entity.locY * 32.0D);
        this.d = MathHelper.floor(entity.locZ * 32.0D);
        if (entity instanceof EntityLightning) {
            this.e = 1;
        }

    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.e();
        this.e = packetdataserializer.readByte();
        this.b = packetdataserializer.readInt();
        this.c = packetdataserializer.readInt();
        this.d = packetdataserializer.readInt();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.b(this.a);
        packetdataserializer.writeByte(this.e);
        packetdataserializer.writeInt(this.b);
        packetdataserializer.writeInt(this.c);
        packetdataserializer.writeInt(this.d);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
