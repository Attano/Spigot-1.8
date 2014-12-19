package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class PacketPlayOutUpdateAttributes implements Packet {

    private int a;
    private final List b = Lists.newArrayList();

    public PacketPlayOutUpdateAttributes() {}

    public PacketPlayOutUpdateAttributes(int i, Collection collection) {
        this.a = i;
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            AttributeInstance attributeinstance = (AttributeInstance) iterator.next();

            this.b.add(new AttributeSnapshot(this, attributeinstance.getAttribute().getName(), attributeinstance.b(), attributeinstance.c()));
        }

    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.e();
        int i = packetdataserializer.readInt();

        for (int j = 0; j < i; ++j) {
            String s = packetdataserializer.c(64);
            double d0 = packetdataserializer.readDouble();
            ArrayList arraylist = Lists.newArrayList();
            int k = packetdataserializer.e();

            for (int l = 0; l < k; ++l) {
                UUID uuid = packetdataserializer.g();

                arraylist.add(new AttributeModifier(uuid, "Unknown synced attribute modifier", packetdataserializer.readDouble(), packetdataserializer.readByte()));
            }

            this.b.add(new AttributeSnapshot(this, s, d0, arraylist));
        }

    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.b(this.a);
        packetdataserializer.writeInt(this.b.size());
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            AttributeSnapshot attributesnapshot = (AttributeSnapshot) iterator.next();

            packetdataserializer.a(attributesnapshot.a());
            packetdataserializer.writeDouble(attributesnapshot.b());
            packetdataserializer.b(attributesnapshot.c().size());
            Iterator iterator1 = attributesnapshot.c().iterator();

            while (iterator1.hasNext()) {
                AttributeModifier attributemodifier = (AttributeModifier) iterator1.next();

                packetdataserializer.a(attributemodifier.a());
                packetdataserializer.writeDouble(attributemodifier.d());
                packetdataserializer.writeByte(attributemodifier.c());
            }
        }

    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
