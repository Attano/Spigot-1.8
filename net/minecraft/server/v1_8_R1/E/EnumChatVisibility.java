package net.minecraft.server;

public enum EnumChatVisibility {

    FULL(0, "options.chat.visibility.full"), SYSTEM(1, "options.chat.visibility.system"), HIDDEN(2, "options.chat.visibility.hidden");

    private static final EnumChatVisibility[] d = new EnumChatVisibility[values().length];
    private final int e;
    private final String f;

    private EnumChatVisibility(int i, String s) {
        this.e = i;
        this.f = s;
    }

    public int a() {
        return this.e;
    }

    public static EnumChatVisibility a(int i) {
        return EnumChatVisibility.d[i % EnumChatVisibility.d.length];
    }

    static {
        EnumChatVisibility[] aenumchatvisibility = values();
        int i = aenumchatvisibility.length;

        for (int j = 0; j < i; ++j) {
            EnumChatVisibility enumchatvisibility = aenumchatvisibility[j];

            EnumChatVisibility.d[enumchatvisibility.e] = enumchatvisibility;
        }

    }
}
