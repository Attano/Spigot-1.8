package net.minecraft.server;

import com.google.gson.JsonObject;
import java.io.File;
import java.net.SocketAddress;

public class IpBanList extends JsonList<String, IpBanEntry> {

    public IpBanList(File file) {
        super(file);
    }

    protected JsonListEntry<String> a(JsonObject jsonobject) {
        return new IpBanEntry(jsonobject);
    }

    public boolean isBanned(SocketAddress socketaddress) {
        String s = this.c(socketaddress);

        return this.d(s);
    }

    public IpBanEntry get(SocketAddress socketaddress) {
        String s = this.c(socketaddress);

        return (IpBanEntry) this.get((Object) s);
    }

    private String c(SocketAddress socketaddress) {
        String s = socketaddress.toString();

        if (s.contains("/")) {
            s = s.substring(s.indexOf(47) + 1);
        }

        if (s.contains(":")) {
            s = s.substring(0, s.indexOf(58));
        }

        return s;
    }
}
