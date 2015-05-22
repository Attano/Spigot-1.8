package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutWorldBorder implements Packet<PacketListenerPlayOut> {

    private PacketPlayOutWorldBorder.EnumWorldBorderAction a;
    private int b;
    private double c;
    private double d;
    private double e;
    private double f;
    private long g;
    private int h;
    private int i;

    public PacketPlayOutWorldBorder() {}

    public PacketPlayOutWorldBorder(WorldBorder worldborder, PacketPlayOutWorldBorder.EnumWorldBorderAction packetplayoutworldborder_enumworldborderaction) {
        this.a = packetplayoutworldborder_enumworldborderaction;
        this.c = worldborder.getCenterX();
        this.d = worldborder.getCenterZ();
        this.f = worldborder.getSize();
        this.e = worldborder.j();
        this.g = worldborder.i();
        this.b = worldborder.l();
        this.i = worldborder.getWarningDistance();
        this.h = worldborder.getWarningTime();
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = (PacketPlayOutWorldBorder.EnumWorldBorderAction) packetdataserializer.a(PacketPlayOutWorldBorder.EnumWorldBorderAction.class);
        switch (PacketPlayOutWorldBorder.SyntheticClass_1.a[this.a.ordinal()]) {
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

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a((Enum) this.a);
        switch (PacketPlayOutWorldBorder.SyntheticClass_1.a[this.a.ordinal()]) {
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

    static class SyntheticClass_1 {

        static final int[] a = new int[PacketPlayOutWorldBorder.EnumWorldBorderAction.values().length];

        static {
            try {
                PacketPlayOutWorldBorder.SyntheticClass_1.a[PacketPlayOutWorldBorder.EnumWorldBorderAction.SET_SIZE.ordinal()] = 1;
            } catch (NoSuchFieldError nosuchfielderror) {
                ;
            }

            try {
                PacketPlayOutWorldBorder.SyntheticClass_1.a[PacketPlayOutWorldBorder.EnumWorldBorderAction.LERP_SIZE.ordinal()] = 2;
            } catch (NoSuchFieldError nosuchfielderror1) {
                ;
            }

            try {
                PacketPlayOutWorldBorder.SyntheticClass_1.a[PacketPlayOutWorldBorder.EnumWorldBorderAction.SET_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError nosuchfielderror2) {
                ;
            }

            try {
                PacketPlayOutWorldBorder.SyntheticClass_1.a[PacketPlayOutWorldBorder.EnumWorldBorderAction.SET_WARNING_BLOCKS.ordinal()] = 4;
            } catch (NoSuchFieldError nosuchfielderror3) {
                ;
            }

            try {
                PacketPlayOutWorldBorder.SyntheticClass_1.a[PacketPlayOutWorldBorder.EnumWorldBorderAction.SET_WARNING_TIME.ordinal()] = 5;
            } catch (NoSuchFieldError nosuchfielderror4) {
                ;
            }

            try {
                PacketPlayOutWorldBorder.SyntheticClass_1.a[PacketPlayOutWorldBorder.EnumWorldBorderAction.INITIALIZE.ordinal()] = 6;
            } catch (NoSuchFieldError nosuchfielderror5) {
                ;
            }

        }
    }

    public static enum EnumWorldBorderAction {

        SET_SIZE, LERP_SIZE, SET_CENTER, INITIALIZE, SET_WARNING_TIME, SET_WARNING_BLOCKS;

        private EnumWorldBorderAction() {}
    }
}
