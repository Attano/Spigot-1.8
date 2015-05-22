package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutEntity implements Packet<PacketListenerPlayOut> {

    protected int a;
    protected byte b;
    protected byte c;
    protected byte d;
    protected byte e;
    protected byte f;
    protected boolean g;
    protected boolean h;

    public PacketPlayOutEntity() {}

    public PacketPlayOutEntity(int i) {
        this.a = i;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.e();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.b(this.a);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public String toString() {
        return "Entity_" + super.toString();
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }

    public static class PacketPlayOutEntityLook extends PacketPlayOutEntity {

        public PacketPlayOutEntityLook() {
            this.h = true;
        }

        public PacketPlayOutEntityLook(int i, byte b0, byte b1, boolean flag) {
            super(i);
            this.e = b0;
            this.f = b1;
            this.h = true;
            this.g = flag;
        }

        public void a(PacketDataSerializer packetdataserializer) throws IOException {
            super.a(packetdataserializer);
            this.e = packetdataserializer.readByte();
            this.f = packetdataserializer.readByte();
            this.g = packetdataserializer.readBoolean();
        }

        public void b(PacketDataSerializer packetdataserializer) throws IOException {
            super.b(packetdataserializer);
            packetdataserializer.writeByte(this.e);
            packetdataserializer.writeByte(this.f);
            packetdataserializer.writeBoolean(this.g);
        }

        public void a(PacketListener packetlistener) {
            super.a((PacketListenerPlayOut) packetlistener);
        }
    }

    public static class PacketPlayOutRelEntityMove extends PacketPlayOutEntity {

        public PacketPlayOutRelEntityMove() {}

        public PacketPlayOutRelEntityMove(int i, byte b0, byte b1, byte b2, boolean flag) {
            super(i);
            this.b = b0;
            this.c = b1;
            this.d = b2;
            this.g = flag;
        }

        public void a(PacketDataSerializer packetdataserializer) throws IOException {
            super.a(packetdataserializer);
            this.b = packetdataserializer.readByte();
            this.c = packetdataserializer.readByte();
            this.d = packetdataserializer.readByte();
            this.g = packetdataserializer.readBoolean();
        }

        public void b(PacketDataSerializer packetdataserializer) throws IOException {
            super.b(packetdataserializer);
            packetdataserializer.writeByte(this.b);
            packetdataserializer.writeByte(this.c);
            packetdataserializer.writeByte(this.d);
            packetdataserializer.writeBoolean(this.g);
        }

        public void a(PacketListener packetlistener) {
            super.a((PacketListenerPlayOut) packetlistener);
        }
    }

    public static class PacketPlayOutRelEntityMoveLook extends PacketPlayOutEntity {

        public PacketPlayOutRelEntityMoveLook() {
            this.h = true;
        }

        public PacketPlayOutRelEntityMoveLook(int i, byte b0, byte b1, byte b2, byte b3, byte b4, boolean flag) {
            super(i);
            this.b = b0;
            this.c = b1;
            this.d = b2;
            this.e = b3;
            this.f = b4;
            this.g = flag;
            this.h = true;
        }

        public void a(PacketDataSerializer packetdataserializer) throws IOException {
            super.a(packetdataserializer);
            this.b = packetdataserializer.readByte();
            this.c = packetdataserializer.readByte();
            this.d = packetdataserializer.readByte();
            this.e = packetdataserializer.readByte();
            this.f = packetdataserializer.readByte();
            this.g = packetdataserializer.readBoolean();
        }

        public void b(PacketDataSerializer packetdataserializer) throws IOException {
            super.b(packetdataserializer);
            packetdataserializer.writeByte(this.b);
            packetdataserializer.writeByte(this.c);
            packetdataserializer.writeByte(this.d);
            packetdataserializer.writeByte(this.e);
            packetdataserializer.writeByte(this.f);
            packetdataserializer.writeBoolean(this.g);
        }

        public void a(PacketListener packetlistener) {
            super.a((PacketListenerPlayOut) packetlistener);
        }
    }
}
