package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutLogin implements Packet<PacketListenerPlayOut> {

    private int a;
    private boolean b;
    private WorldSettings.EnumGamemode c;
    private int d;
    private EnumDifficulty e;
    private int f;
    private WorldType g;
    private boolean h;

    public PacketPlayOutLogin() {}

    public PacketPlayOutLogin(int i, WorldSettings.EnumGamemode worldsettings_enumgamemode, boolean flag, int j, EnumDifficulty enumdifficulty, int k, WorldType worldtype, boolean flag1) {
        this.a = i;
        this.d = j;
        this.e = enumdifficulty;
        this.c = worldsettings_enumgamemode;
        this.f = k;
        this.b = flag;
        this.g = worldtype;
        this.h = flag1;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.readInt();
        short short0 = packetdataserializer.readUnsignedByte();

        this.b = (short0 & 8) == 8;
        int i = short0 & -9;

        this.c = WorldSettings.EnumGamemode.getById(i);
        this.d = packetdataserializer.readByte();
        this.e = EnumDifficulty.getById(packetdataserializer.readUnsignedByte());
        this.f = packetdataserializer.readUnsignedByte();
        this.g = WorldType.getType(packetdataserializer.c(16));
        if (this.g == null) {
            this.g = WorldType.NORMAL;
        }

        this.h = packetdataserializer.readBoolean();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.writeInt(this.a);
        int i = this.c.getId();

        if (this.b) {
            i |= 8;
        }

        packetdataserializer.writeByte(i);
        packetdataserializer.writeByte(this.d);
        packetdataserializer.writeByte(this.e.a());
        packetdataserializer.writeByte(this.f);
        packetdataserializer.a(this.g.name());
        packetdataserializer.writeBoolean(this.h);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
