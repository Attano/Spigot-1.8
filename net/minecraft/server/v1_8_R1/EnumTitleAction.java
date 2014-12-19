package net.minecraft.server;

public enum EnumTitleAction {

    TITLE, SUBTITLE, TIMES, CLEAR, RESET;

    private EnumTitleAction() {}

    public static EnumTitleAction a(String s) {
        EnumTitleAction[] aenumtitleaction = values();
        int i = aenumtitleaction.length;

        for (int j = 0; j < i; ++j) {
            EnumTitleAction enumtitleaction = aenumtitleaction[j];

            if (enumtitleaction.name().equalsIgnoreCase(s)) {
                return enumtitleaction;
            }
        }

        return EnumTitleAction.TITLE;
    }

    public static String[] a() {
        String[] astring = new String[values().length];
        int i = 0;
        EnumTitleAction[] aenumtitleaction = values();
        int j = aenumtitleaction.length;

        for (int k = 0; k < j; ++k) {
            EnumTitleAction enumtitleaction = aenumtitleaction[k];

            astring[i++] = enumtitleaction.name().toLowerCase();
        }

        return astring;
    }
}
