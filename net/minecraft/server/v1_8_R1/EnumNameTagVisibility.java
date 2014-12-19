package net.minecraft.server;

import com.google.common.collect.Maps;
import java.util.Map;

public enum EnumNameTagVisibility {

    ALWAYS("always", 0), NEVER("never", 1), HIDE_FOR_OTHER_TEAMS("hideForOtherTeams", 2), HIDE_FOR_OWN_TEAM("hideForOwnTeam", 3);

    private static Map g = Maps.newHashMap();
    public final String e;
    public final int f;

    public static String[] a() {
        return (String[]) EnumNameTagVisibility.g.keySet().toArray(new String[EnumNameTagVisibility.g.size()]);
    }

    public static EnumNameTagVisibility a(String s) {
        return (EnumNameTagVisibility) EnumNameTagVisibility.g.get(s);
    }

    private EnumNameTagVisibility(String s, int i) {
        this.e = s;
        this.f = i;
    }

    static {
        EnumNameTagVisibility[] aenumnametagvisibility = values();
        int i = aenumnametagvisibility.length;

        for (int j = 0; j < i; ++j) {
            EnumNameTagVisibility enumnametagvisibility = aenumnametagvisibility[j];

            EnumNameTagVisibility.g.put(enumnametagvisibility.e, enumnametagvisibility);
        }

    }
}
