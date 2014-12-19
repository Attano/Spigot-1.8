package net.minecraft.server;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.logging.log4j.LogManager;

public enum EnumProtocol {

    HANDSHAKING(-1), PLAY(0), STATUS(1), LOGIN(2);

    private static final TIntObjectMap e = new TIntObjectHashMap();
    private static final Map f = Maps.newHashMap();
    private final int g;
    private final Map h;

    private EnumProtocol(int i) {
        this.h = Maps.newEnumMap(EnumProtocolDirection.class);
        this.g = i;
    }

    protected EnumProtocol a(EnumProtocolDirection enumprotocoldirection, Class oclass) {
        Object object = (BiMap) this.h.get(enumprotocoldirection);

        if (object == null) {
            object = HashBiMap.create();
            this.h.put(enumprotocoldirection, object);
        }

        if (((BiMap) object).containsValue(oclass)) {
            String s = enumprotocoldirection + " packet " + oclass + " is already known to ID " + ((BiMap) object).inverse().get(oclass);

            LogManager.getLogger().fatal(s);
            throw new IllegalArgumentException(s);
        } else {
            ((BiMap) object).put(Integer.valueOf(((BiMap) object).size()), oclass);
            return this;
        }
    }

    public Integer a(EnumProtocolDirection enumprotocoldirection, Packet packet) {
        return (Integer) ((BiMap) this.h.get(enumprotocoldirection)).inverse().get(packet.getClass());
    }

    public Packet a(EnumProtocolDirection enumprotocoldirection, int i) {
        Class oclass = (Class) ((BiMap) this.h.get(enumprotocoldirection)).get(Integer.valueOf(i));

        return oclass == null ? null : (Packet) oclass.newInstance();
    }

    public int a() {
        return this.g;
    }

    public static EnumProtocol a(int i) {
        return (EnumProtocol) EnumProtocol.e.get(i);
    }

    public static EnumProtocol a(Packet packet) {
        return (EnumProtocol) EnumProtocol.f.get(packet.getClass());
    }

    EnumProtocol(int i, EnumProtocolHandshake enumprotocolhandshake) {
        this(i);
    }

    static {
        EnumProtocol[] aenumprotocol = values();
        int i = aenumprotocol.length;

        for (int j = 0; j < i; ++j) {
            EnumProtocol enumprotocol = aenumprotocol[j];

            EnumProtocol.e.put(enumprotocol.a(), enumprotocol);
            Iterator iterator = enumprotocol.h.keySet().iterator();

            while (iterator.hasNext()) {
                EnumProtocolDirection enumprotocoldirection = (EnumProtocolDirection) iterator.next();

                Class oclass;

                for (Iterator iterator1 = ((BiMap) enumprotocol.h.get(enumprotocoldirection)).values().iterator(); iterator1.hasNext(); EnumProtocol.f.put(oclass, enumprotocol)) {
                    oclass = (Class) iterator1.next();
                    if (EnumProtocol.f.containsKey(oclass) && EnumProtocol.f.get(oclass) != enumprotocol) {
                        throw new Error("Packet " + oclass + " is already assigned to protocol " + EnumProtocol.f.get(oclass) + " - can\'t reassign to " + enumprotocol);
                    }

                    try {
                        oclass.newInstance();
                    } catch (Throwable throwable) {
                        throw new Error("Packet " + oclass + " fails instantiation checks! " + oclass);
                    }
                }
            }
        }

    }
}
