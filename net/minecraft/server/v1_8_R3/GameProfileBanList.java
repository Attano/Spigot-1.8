package net.minecraft.server;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.io.File;
import java.util.Iterator;

public class GameProfileBanList extends JsonList<GameProfile, GameProfileBanEntry> {

    public GameProfileBanList(File file) {
        super(file);
    }

    protected JsonListEntry<GameProfile> a(JsonObject jsonobject) {
        return new GameProfileBanEntry(jsonobject);
    }

    public boolean isBanned(GameProfile gameprofile) {
        return this.d(gameprofile);
    }

    public String[] getEntries() {
        String[] astring = new String[this.e().size()];
        int i = 0;

        GameProfileBanEntry gameprofilebanentry;

        for (Iterator iterator = this.e().values().iterator(); iterator.hasNext(); astring[i++] = ((GameProfile) gameprofilebanentry.getKey()).getName()) {
            gameprofilebanentry = (GameProfileBanEntry) iterator.next();
        }

        return astring;
    }

    protected String b(GameProfile gameprofile) {
        return gameprofile.getId().toString();
    }

    public GameProfile a(String s) {
        Iterator iterator = this.e().values().iterator();

        GameProfileBanEntry gameprofilebanentry;

        do {
            if (!iterator.hasNext()) {
                return null;
            }

            gameprofilebanentry = (GameProfileBanEntry) iterator.next();
        } while (!s.equalsIgnoreCase(((GameProfile) gameprofilebanentry.getKey()).getName()));

        return (GameProfile) gameprofilebanentry.getKey();
    }

    protected String a(Object object) {
        return this.b((GameProfile) object);
    }
}
