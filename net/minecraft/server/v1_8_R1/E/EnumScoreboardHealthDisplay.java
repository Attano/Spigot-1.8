package net.minecraft.server;

import com.google.common.collect.Maps;
import java.util.Map;

public enum EnumScoreboardHealthDisplay {

    INTEGER("integer"), HEARTS("hearts");

    private static final Map c = Maps.newHashMap();
    private final String d;

    private EnumScoreboardHealthDisplay(String s) {
        this.d = s;
    }

    public String a() {
        return this.d;
    }

    public static EnumScoreboardHealthDisplay a(String s) {
        EnumScoreboardHealthDisplay enumscoreboardhealthdisplay = (EnumScoreboardHealthDisplay) EnumScoreboardHealthDisplay.c.get(s);

        return enumscoreboardhealthdisplay == null ? EnumScoreboardHealthDisplay.INTEGER : enumscoreboardhealthdisplay;
    }

    static {
        EnumScoreboardHealthDisplay[] aenumscoreboardhealthdisplay = values();
        int i = aenumscoreboardhealthdisplay.length;

        for (int j = 0; j < i; ++j) {
            EnumScoreboardHealthDisplay enumscoreboardhealthdisplay = aenumscoreboardhealthdisplay[j];

            EnumScoreboardHealthDisplay.c.put(enumscoreboardhealthdisplay.a(), enumscoreboardhealthdisplay);
        }

    }
}
