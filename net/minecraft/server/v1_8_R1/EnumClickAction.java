package net.minecraft.server;

import com.google.common.collect.Maps;
import java.util.Map;

public enum EnumClickAction {

    OPEN_URL("open_url", true), OPEN_FILE("open_file", false), RUN_COMMAND("run_command", true), TWITCH_USER_INFO("twitch_user_info", false), SUGGEST_COMMAND("suggest_command", true), CHANGE_PAGE("change_page", true);

    private static final Map g = Maps.newHashMap();
    private final boolean h;
    private final String i;

    private EnumClickAction(String s, boolean flag) {
        this.i = s;
        this.h = flag;
    }

    public boolean a() {
        return this.h;
    }

    public String b() {
        return this.i;
    }

    public static EnumClickAction a(String s) {
        return (EnumClickAction) EnumClickAction.g.get(s);
    }

    static {
        EnumClickAction[] aenumclickaction = values();
        int i = aenumclickaction.length;

        for (int j = 0; j < i; ++j) {
            EnumClickAction enumclickaction = aenumclickaction[j];

            EnumClickAction.g.put(enumclickaction.b(), enumclickaction);
        }

    }
}
