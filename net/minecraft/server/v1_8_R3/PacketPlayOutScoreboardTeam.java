package net.minecraft.server;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class PacketPlayOutScoreboardTeam implements Packet<PacketListenerPlayOut> {

    private String a = "";
    private String b = "";
    private String c = "";
    private String d = "";
    private String e;
    private int f;
    private Collection<String> g;
    private int h;
    private int i;

    public PacketPlayOutScoreboardTeam() {
        this.e = ScoreboardTeamBase.EnumNameTagVisibility.ALWAYS.e;
        this.f = -1;
        this.g = Lists.newArrayList();
    }

    public PacketPlayOutScoreboardTeam(ScoreboardTeam scoreboardteam, int i) {
        this.e = ScoreboardTeamBase.EnumNameTagVisibility.ALWAYS.e;
        this.f = -1;
        this.g = Lists.newArrayList();
        this.a = scoreboardteam.getName();
        this.h = i;
        if (i == 0 || i == 2) {
            this.b = scoreboardteam.getDisplayName();
            this.c = scoreboardteam.getPrefix();
            this.d = scoreboardteam.getSuffix();
            this.i = scoreboardteam.packOptionData();
            this.e = scoreboardteam.getNameTagVisibility().e;
            this.f = scoreboardteam.l().b();
        }

        if (i == 0) {
            this.g.addAll(scoreboardteam.getPlayerNameSet());
        }

    }

    public PacketPlayOutScoreboardTeam(ScoreboardTeam scoreboardteam, Collection<String> collection, int i) {
        this.e = ScoreboardTeamBase.EnumNameTagVisibility.ALWAYS.e;
        this.f = -1;
        this.g = Lists.newArrayList();
        if (i != 3 && i != 4) {
            throw new IllegalArgumentException("Method must be join or leave for player constructor");
        } else if (collection != null && !collection.isEmpty()) {
            this.h = i;
            this.a = scoreboardteam.getName();
            this.g.addAll(collection);
        } else {
            throw new IllegalArgumentException("Players cannot be null/empty");
        }
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.c(16);
        this.h = packetdataserializer.readByte();
        if (this.h == 0 || this.h == 2) {
            this.b = packetdataserializer.c(32);
            this.c = packetdataserializer.c(16);
            this.d = packetdataserializer.c(16);
            this.i = packetdataserializer.readByte();
            this.e = packetdataserializer.c(32);
            this.f = packetdataserializer.readByte();
        }

        if (this.h == 0 || this.h == 3 || this.h == 4) {
            int i = packetdataserializer.e();

            for (int j = 0; j < i; ++j) {
                this.g.add(packetdataserializer.c(40));
            }
        }

    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a(this.a);
        packetdataserializer.writeByte(this.h);
        if (this.h == 0 || this.h == 2) {
            packetdataserializer.a(this.b);
            packetdataserializer.a(this.c);
            packetdataserializer.a(this.d);
            packetdataserializer.writeByte(this.i);
            packetdataserializer.a(this.e);
            packetdataserializer.writeByte(this.f);
        }

        if (this.h == 0 || this.h == 3 || this.h == 4) {
            packetdataserializer.b(this.g.size());
            Iterator iterator = this.g.iterator();

            while (iterator.hasNext()) {
                String s = (String) iterator.next();

                packetdataserializer.a(s);
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
