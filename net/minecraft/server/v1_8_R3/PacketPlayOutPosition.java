package net.minecraft.server;

import java.io.IOException;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;

public class PacketPlayOutPosition implements Packet<PacketListenerPlayOut> {

    private double a;
    private double b;
    private double c;
    private float d;
    private float e;
    private Set<PacketPlayOutPosition.EnumPlayerTeleportFlags> f;

    public PacketPlayOutPosition() {}

    public PacketPlayOutPosition(double d0, double d1, double d2, float f, float f1, Set<PacketPlayOutPosition.EnumPlayerTeleportFlags> set) {
        this.a = d0;
        this.b = d1;
        this.c = d2;
        this.d = f;
        this.e = f1;
        this.f = set;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.readDouble();
        this.b = packetdataserializer.readDouble();
        this.c = packetdataserializer.readDouble();
        this.d = packetdataserializer.readFloat();
        this.e = packetdataserializer.readFloat();
        this.f = PacketPlayOutPosition.EnumPlayerTeleportFlags.a(packetdataserializer.readUnsignedByte());
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.writeDouble(this.a);
        packetdataserializer.writeDouble(this.b);
        packetdataserializer.writeDouble(this.c);
        packetdataserializer.writeFloat(this.d);
        packetdataserializer.writeFloat(this.e);
        packetdataserializer.writeByte(PacketPlayOutPosition.EnumPlayerTeleportFlags.a(this.f));
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }

    public static enum EnumPlayerTeleportFlags {

        X(0), Y(1), Z(2), Y_ROT(3), X_ROT(4);

        private int f;

        private EnumPlayerTeleportFlags(int i) {
            this.f = i;
        }

        private int a() {
            return 1 << this.f;
        }

        private boolean b(int i) {
            return (i & this.a()) == this.a();
        }

        public static Set<PacketPlayOutPosition.EnumPlayerTeleportFlags> a(int i) {
            EnumSet enumset = EnumSet.noneOf(PacketPlayOutPosition.EnumPlayerTeleportFlags.class);
            PacketPlayOutPosition.EnumPlayerTeleportFlags[] apacketplayoutposition_enumplayerteleportflags = values();
            int j = apacketplayoutposition_enumplayerteleportflags.length;

            for (int k = 0; k < j; ++k) {
                PacketPlayOutPosition.EnumPlayerTeleportFlags packetplayoutposition_enumplayerteleportflags = apacketplayoutposition_enumplayerteleportflags[k];

                if (packetplayoutposition_enumplayerteleportflags.b(i)) {
                    enumset.add(packetplayoutposition_enumplayerteleportflags);
                }
            }

            return enumset;
        }

        public static int a(Set<PacketPlayOutPosition.EnumPlayerTeleportFlags> set) {
            int i = 0;

            PacketPlayOutPosition.EnumPlayerTeleportFlags packetplayoutposition_enumplayerteleportflags;

            for (Iterator iterator = set.iterator(); iterator.hasNext(); i |= packetplayoutposition_enumplayerteleportflags.a()) {
                packetplayoutposition_enumplayerteleportflags = (PacketPlayOutPosition.EnumPlayerTeleportFlags) iterator.next();
            }

            return i;
        }
    }
}
