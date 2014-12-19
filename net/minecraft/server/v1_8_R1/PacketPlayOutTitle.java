package net.minecraft.server;

public class PacketPlayOutTitle implements Packet {

    private EnumTitleAction a;
    private IChatBaseComponent b;
    private int c;
    private int d;
    private int e;

    public PacketPlayOutTitle() {}

    public PacketPlayOutTitle(EnumTitleAction enumtitleaction, IChatBaseComponent ichatbasecomponent) {
        this(enumtitleaction, ichatbasecomponent, -1, -1, -1);
    }

    public PacketPlayOutTitle(int i, int j, int k) {
        this(EnumTitleAction.TIMES, (IChatBaseComponent) null, i, j, k);
    }

    public PacketPlayOutTitle(EnumTitleAction enumtitleaction, IChatBaseComponent ichatbasecomponent, int i, int j, int k) {
        this.a = enumtitleaction;
        this.b = ichatbasecomponent;
        this.c = i;
        this.d = j;
        this.e = k;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = (EnumTitleAction) packetdataserializer.a(EnumTitleAction.class);
        if (this.a == EnumTitleAction.TITLE || this.a == EnumTitleAction.SUBTITLE) {
            this.b = packetdataserializer.d();
        }

        if (this.a == EnumTitleAction.TIMES) {
            this.c = packetdataserializer.readInt();
            this.d = packetdataserializer.readInt();
            this.e = packetdataserializer.readInt();
        }

    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.a((Enum) this.a);
        if (this.a == EnumTitleAction.TITLE || this.a == EnumTitleAction.SUBTITLE) {
            packetdataserializer.a(this.b);
        }

        if (this.a == EnumTitleAction.TIMES) {
            packetdataserializer.writeInt(this.c);
            packetdataserializer.writeInt(this.d);
            packetdataserializer.writeInt(this.e);
        }

    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
