package net.minecraft.server;

public class PacketPlayOutWorldBorder implements Packet {

    private EnumWorldBorderAction a;
    private int b;
    private double c;
    private double d;
    private double e;
    private double f;
    private long g;
    private int h;
    private int i;

    public PacketPlayOutWorldBorder() {}

    public PacketPlayOutWorldBorder(WorldBorder worldborder, EnumWorldBorderAction enumworldborderaction) {
        this.a = enumworldborderaction;
        this.c = worldborder.f();
        this.d = worldborder.g();
        this.f = worldborder.h();
        this.e = worldborder.j();
        this.g = worldborder.i();
        this.b = worldborder.l();
        this.i = worldborder.q();
        this.h = worldborder.p();
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = (EnumWorldBorderAction) packetdataserializer.a(EnumWorldBorderAction.class);
        switch (SwitchHelperWorldBorder.a[this.a.ordinal()]) {
        case 1:
            this.e = packetdataserializer.readDouble();
            break;

        case 2:
            this.f = packetdataserializer.readDouble();
            this.e = packetdataserializer.readDouble();
            this.g = packetdataserializer.f();
            break;

        case 3:
            this.c = packetdataserializer.readDouble();
            this.d = packetdataserializer.readDouble();
            break;

        case 4:
            this.i = packetdataserializer.e();
            break;

        case 5:
            this.h = packetdataserializer.e();
            break;

        case 6:
            this.c = packetdataserializer.readDouble();
            this.d = packetdataserializer.readDouble();
            this.f = packetdataserializer.readDouble();
            this.e = packetdataserializer.readDouble();
            this.g = packetdataserializer.f();
            this.b = packetdataserializer.e();
            this.i = packetdataserializer.e();
            this.h = packetdataserializer.e();
        }

    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.a((Enum) this.a);
        switch (SwitchHelperWorldBorder.a[this.a.ordinal()]) {
        case 1:
            packetdataserializer.writeDouble(this.e);
            break;

        case 2:
            packetdataserializer.writeDouble(this.f);
            packetdataserializer.writeDouble(this.e);
            packetdataserializer.b(this.g);
            break;

        case 3:
            packetdataserializer.writeDouble(this.c);
            packetdataserializer.writeDouble(this.d);
            break;

        case 4:
            packetdataserializer.b(this.i);
            break;

        case 5:
            packetdataserializer.b(this.h);
            break;

        case 6:
            packetdataserializer.writeDouble(this.c);
            packetdataserializer.writeDouble(this.d);
            packetdataserializer.writeDouble(this.f);
            packetdataserializer.writeDouble(this.e);
            packetdataserializer.b(this.g);
            packetdataserializer.b(this.b);
            packetdataserializer.b(this.i);
            packetdataserializer.b(this.h);
        }

    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
