package net.minecraft.server;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.io.File;
import java.util.Iterator;

public class OpList extends JsonList {

    public OpList(File file) {
        super(file);
    }

    protected JsonListEntry a(JsonObject jsonobject) {
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

    protected String b(GameProfile gameprofile) {
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
        return this.b((GameProfile) object);
    }
}
