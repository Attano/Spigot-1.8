package net.minecraft.server;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.io.File;
import java.util.Iterator;

public class WhiteList extends JsonList<GameProfile, WhiteListEntry> {

    public WhiteList(File file) {
        super(file);
    }

    protected JsonListEntry<GameProfile> a(JsonObject jsonobject) {
        return new WhiteListEntry(jsonobject);
    }

    public boolean isWhitelisted(GameProfile gameprofile) {
        return this.d(gameprofile);
    }

    public String[] getEntries() {
        String[] astring = new String[this.e().size()];
        int i = 0;

        WhiteListEntry whitelistentry;

        for (Iterator iterator = this.e().values().iterator(); iterator.hasNext(); astring[i++] = ((GameProfile) whitelistentry.getKey()).getName()) {
            whitelistentry = (WhiteListEntry) iterator.next();
        }

        return astring;
    }

    protected String b(GameProfile gameprofile) {
        return gameprofile.getId().toString();
    }

    public GameProfile a(String s) {
        Iterator iterator = this.e().values().iterator();

        WhiteListEntry whitelistentry;

        do {
            if (!iterator.hasNext()) {
                return null;
            }

            whitelistentry = (WhiteListEntry) iterator.next();
        } while (!s.equalsIgnoreCase(((GameProfile) whitelistentry.getKey()).getName()));

        return (GameProfile) whitelistentry.getKey();
    }

    protected String a(Object object) {
        return this.b((GameProfile) object);
    }
}
