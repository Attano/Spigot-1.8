package net.minecraft.server;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.ProfileLookupCallback;
import java.util.List;

final class GameProfileLookupCallback implements ProfileLookupCallback {

    final MinecraftServer a;
    final List b;

    GameProfileLookupCallback(MinecraftServer minecraftserver, List list) {
        this.a = minecraftserver;
        this.b = list;
    }

    public void onProfileLookupSucceeded(GameProfile gameprofile) {
        this.a.getUserCache().a(gameprofile);
        this.b.add(gameprofile);
    }

    public void onProfileLookupFailed(GameProfile gameprofile, Exception exception) {
        NameReferencingFileConverter.a().warn("Could not lookup user whitelist entry for " + gameprofile.getName(), exception);
    }
}
