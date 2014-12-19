package net.minecraft.server;

import com.mojang.authlib.GameProfile;

public class PlayerInfoData {

    private final int b;
    private final EnumGamemode c;
    private final GameProfile d;
    private final IChatBaseComponent e;
    final PacketPlayOutPlayerInfo a;

    public PlayerInfoData(PacketPlayOutPlayerInfo packetplayoutplayerinfo, GameProfile gameprofile, int i, EnumGamemode enumgamemode, IChatBaseComponent ichatbasecomponent) {
        this.a = packetplayoutplayerinfo;
        this.d = gameprofile;
        this.b = i;
        this.c = enumgamemode;
        this.e = ichatbasecomponent;
    }

    public GameProfile a() {
        return this.d;
    }

    public int b() {
        return this.b;
    }

    public EnumGamemode c() {
        return this.c;
    }

    public IChatBaseComponent d() {
        return this.e;
    }
}
