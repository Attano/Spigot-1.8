package net.minecraft.server;

import com.google.common.collect.Maps;
import java.util.Map;

public enum EnumHoverAction {

    SHOW_TEXT("show_text", true), SHOW_ACHIEVEMENT("show_achievement", true), SHOW_ITEM("show_item", true), SHOW_ENTITY("show_entity", true);

    private static final Map e = Maps.newHashMap();
    private final boolean f;
    private final String g;

    private EnumHoverAction(String s, boolean flag) {
        this.g = s;
        this.f = flag;
    }

    public boolean a() {
        return this.f;
    }

    public String b() {
        return this.g;
    }

    public static EnumHoverAction a(String s) {
        return (EnumHoverAction) EnumHoverAction.e.get(s);
    }

    static {
        EnumHoverAction[] aenumhoveraction = values();
        int i = aenumhoveraction.length;

        for (int j = 0; j < i; ++j) {
            EnumHoverAction enumhoveraction = aenumhoveraction[j];

            EnumHoverAction.e.put(enumhoveraction.b(), enumhoveraction);
        }

    }
}
