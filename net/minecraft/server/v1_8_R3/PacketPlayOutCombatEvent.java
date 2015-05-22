package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutCombatEvent implements Packet<PacketListenerPlayOut> {

    public PacketPlayOutCombatEvent.EnumCombatEventType a;
    public int b;
    public int c;
    public int d;
    public String e;

    public PacketPlayOutCombatEvent() {}

    public PacketPlayOutCombatEvent(CombatTracker combattracker, PacketPlayOutCombatEvent.EnumCombatEventType packetplayoutcombatevent_enumcombateventtype) {
        this.a = packetplayoutcombatevent_enumcombateventtype;
        EntityLiving entityliving = combattracker.c();

        switch (PacketPlayOutCombatEvent.SyntheticClass_1.a[packetplayoutcombatevent_enumcombateventtype.ordinal()]) {
        case 1:
            this.d = combattracker.f();
            this.c = entityliving == null ? -1 : entityliving.getId();
            break;

        case 2:
            this.b = combattracker.h().getId();
            this.c = entityliving == null ? -1 : entityliving.getId();
            this.e = combattracker.b().c();
        }

    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = (PacketPlayOutCombatEvent.EnumCombatEventType) packetdataserializer.a(PacketPlayOutCombatEvent.EnumCombatEventType.class);
        if (this.a == PacketPlayOutCombatEvent.EnumCombatEventType.END_COMBAT) {
            this.d = packetdataserializer.e();
            this.c = packetdataserializer.readInt();
        } else if (this.a == PacketPlayOutCombatEvent.EnumCombatEventType.ENTITY_DIED) {
            this.b = packetdataserializer.e();
            this.c = packetdataserializer.readInt();
            this.e = packetdataserializer.c(32767);
        }

    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a((Enum) this.a);
        if (this.a == PacketPlayOutCombatEvent.EnumCombatEventType.END_COMBAT) {
            packetdataserializer.b(this.d);
            packetdataserializer.writeInt(this.c);
        } else if (this.a == PacketPlayOutCombatEvent.EnumCombatEventType.ENTITY_DIED) {
            packetdataserializer.b(this.b);
            packetdataserializer.writeInt(this.c);
            packetdataserializer.a(this.e);
        }

    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }

    static class SyntheticClass_1 {

        static final int[] a = new int[PacketPlayOutCombatEvent.EnumCombatEventType.values().length];

        static {
            try {
                PacketPlayOutCombatEvent.SyntheticClass_1.a[PacketPlayOutCombatEvent.EnumCombatEventType.END_COMBAT.ordinal()] = 1;
            } catch (NoSuchFieldError nosuchfielderror) {
                ;
            }

            try {
                PacketPlayOutCombatEvent.SyntheticClass_1.a[PacketPlayOutCombatEvent.EnumCombatEventType.ENTITY_DIED.ordinal()] = 2;
            } catch (NoSuchFieldError nosuchfielderror1) {
                ;
            }

        }
    }

    public static enum EnumCombatEventType {

        ENTER_COMBAT, END_COMBAT, ENTITY_DIED;

        private EnumCombatEventType() {}
    }
}
