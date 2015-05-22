package net.minecraft.server;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.io.File;
import java.util.Iterator;

public class OpList extends JsonList<GameProfile, OpListEntry> {

    public OpList(File file) {
        super(file);
    }

    protected JsonListEntry<GameProfile> a(JsonObject jsonobject) {
        return new OpListEntry(jsonobject);
    }

    public String[] getEntries() {
        String[] astring = new String[this.e().size()];
        int i = 0;

        OpListEntry oplistentry;

        for (Iterator iterator = this.e().values().iterator(); iterator.hasNext(); astring[i++] = ((GameProfile) oplistentry.getKey()).getName()) {
            oplistentry = (OpListEntry) iterator.next();
        }

        return astring;
    }

    public boolean b(GameProfile gameprofile) {
        OpListEntry oplistentry = (OpListEntry) this.get(gameprofile);

        return oplistentry != null ? oplistentry.b() : false;
    }

    protected String c(GameProfile gameprofile) {
        return gameprofile.getId().toString();
    }

    public GameProfile a(String s) {
        Iterator iterator = this.e().values().iterator();

        OpListEntry oplistentry;

        do {
            if (!iterator.hasNext()) {
                return null;
            }

            oplistentry = (OpListEntry) iterator.next();
        } while (!s.equalsIgnoreCase(((GameProfile) oplistentry.getKey()).getName()));

        return (GameProfile) oplistentry.getKey();
    }

    protected String a(Object object) {
        return this.c((GameProfile) object);
    }
}
