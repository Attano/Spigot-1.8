package net.minecraft.server;

import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.util.Iterator;
import java.util.List;

public class PacketPlayOutPlayerInfo implements Packet {

    private EnumPlayerInfoAction a;
    private final List b = Lists.newArrayList();

    public PacketPlayOutPlayerInfo() {}

    public PacketPlayOutPlayerInfo(EnumPlayerInfoAction enumplayerinfoaction, EntityPlayer... aentityplayer) {
        this.a = enumplayerinfoaction;
        EntityPlayer[] aentityplayer1 = aentityplayer;
        int i = aentityplayer.length;

        for (int j = 0; j < i; ++j) {
            EntityPlayer entityplayer = aentityplayer1[j];

            this.b.add(new PlayerInfoData(this, entityplayer.getProfile(), entityplayer.ping, entityplayer.playerInteractManager.getGameMode(), entityplayer.getPlayerListName()));
        }

    }

    public PacketPlayOutPlayerInfo(EnumPlayerInfoAction enumplayerinfoaction, Iterable iterable) {
        this.a = enumplayerinfoaction;
        Iterator iterator = iterable.iterator();

        while (iterator.hasNext()) {
            EntityPlayer entityplayer = (EntityPlayer) iterator.next();

            this.b.add(new PlayerInfoData(this, entityplayer.getProfile(), entityplayer.ping, entityplayer.playerInteractManager.getGameMode(), entityplayer.getPlayerListName()));
        }

    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = (EnumPlayerInfoAction) packetdataserializer.a(EnumPlayerInfoAction.class);
        int i = packetdataserializer.e();

        for (int j = 0; j < i; ++j) {
            GameProfile gameprofile = null;
            int k = 0;
            EnumGamemode enumgamemode = null;
            IChatBaseComponent ichatbasecomponent = null;

            switch (SwitchHelperPlayerInfo.a[this.a.ordinal()]) {
            case 1:
                gameprofile = new GameProfile(packetdataserializer.g(), packetdataserializer.c(16));
                int l = packetdataserializer.e();

                for (int i1 = 0; i1 < l; ++i1) {
                    String s = packetdataserializer.c(32767);
                    String s1 = packetdataserializer.c(32767);

                    if (packetdataserializer.readBoolean()) {
                        gameprofile.getProperties().put(s, new Property(s, s1, packetdataserializer.c(32767)));
                    } else {
                        gameprofile.getProperties().put(s, new Property(s, s1));
                    }
                }

                enumgamemode = EnumGamemode.getById(packetdataserializer.e());
                k = packetdataserializer.e();
                if (packetdataserializer.readBoolean()) {
                    ichatbasecomponent = packetdataserializer.d();
                }
                break;

            case 2:
                gameprofile = new GameProfile(packetdataserializer.g(), (String) null);
                enumgamemode = EnumGamemode.getById(packetdataserializer.e());
                break;

            case 3:
                gameprofile = new GameProfile(packetdataserializer.g(), (String) null);
                k = packetdataserializer.e();
                break;

            case 4:
                gameprofile = new GameProfile(packetdataserializer.g(), (String) null);
                if (packetdataserializer.readBoolean()) {
                    ichatbasecomponent = packetdataserializer.d();
                }
                break;

            case 5:
                gameprofile = new GameProfile(packetdataserializer.g(), (String) null);
            }

            this.b.add(new PlayerInfoData(this, gameprofile, k, enumgamemode, ichatbasecomponent));
        }

    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.a((Enum) this.a);
        packetdataserializer.b(this.b.size());
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            PlayerInfoData playerinfodata = (PlayerInfoData) iterator.next();

            switch (SwitchHelperPlayerInfo.a[this.a.ordinal()]) {
            case 1:
                packetdataserializer.a(playerinfodata.a().getId());
                packetdataserializer.a(playerinfodata.a().getName());
                packetdataserializer.b(playerinfodata.a().getProperties().size());
                Iterator iterator1 = playerinfodata.a().getProperties().values().iterator();

                while (iterator1.hasNext()) {
                    Property property = (Property) iterator1.next();

                    packetdataserializer.a(property.getName());
                    packetdataserializer.a(property.getValue());
                    if (property.hasSignature()) {
                        packetdataserializer.writeBoolean(true);
                        packetdataserializer.a(property.getSignature());
                    } else {
                        packetdataserializer.writeBoolean(false);
                    }
                }

                packetdataserializer.b(playerinfodata.c().getId());
                packetdataserializer.b(playerinfodata.b());
                if (playerinfodata.d() == null) {
                    packetdataserializer.writeBoolean(false);
                } else {
                    packetdataserializer.writeBoolean(true);
                    packetdataserializer.a(playerinfodata.d());
                }
                break;

            case 2:
                packetdataserializer.a(playerinfodata.a().getId());
                packetdataserializer.b(playerinfodata.c().getId());
                break;

            case 3:
                packetdataserializer.a(playerinfodata.a().getId());
                packetdataserializer.b(playerinfodata.b());
                break;

            case 4:
                packetdataserializer.a(playerinfodata.a().getId());
                if (playerinfodata.d() == null) {
                    packetdataserializer.writeBoolean(false);
                } else {
                    packetdataserializer.writeBoolean(true);
                    packetdataserializer.a(playerinfodata.d());
                }
                break;

            case 5:
                packetdataserializer.a(playerinfodata.a().getId());
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
