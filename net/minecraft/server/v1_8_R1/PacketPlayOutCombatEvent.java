package net.minecraft.server;

public class PacketPlayOutCombatEvent implements Packet {

    public EnumCombatEventType a;
    public int b;
    public int c;
    public int d;
    public String e;

    public PacketPlayOutCombatEvent() {}

    public PacketPlayOutCombatEvent(CombatTracker combattracker, EnumCombatEventType enumcombateventtype) {
        this.a = enumcombateventtype;
        EntityLiving entityliving = combattracker.c();

        switch (SwitchHelperCombatEventType.a[enumcombateventtype.ordinal()]) {
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

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = (EnumCombatEventType) packetdataserializer.a(EnumCombatEventType.class);
        if (this.a == EnumCombatEventType.END_COMBAT) {
            this.d = packetdataserializer.e();
            this.c = packetdataserializer.readInt();
        } else if (this.a == EnumCombatEventType.ENTITY_DIED) {
            this.b = packetdataserializer.e();
            this.c = packetdataserializer.readInt();
            this.e = packetdataserializer.c(32767);
        }

    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.a((Enum) this.a);
        if (this.a == EnumCombatEventType.END_COMBAT) {
            packetdataserializer.b(this.d);
            packetdataserializer.writeInt(this.c);
        } else if (this.a == EnumCombatEventType.ENTITY_DIED) {
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
}
