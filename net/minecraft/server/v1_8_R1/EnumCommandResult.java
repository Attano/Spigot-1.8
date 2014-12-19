package net.minecraft.server;

public enum EnumCommandResult {

    SUCCESS_COUNT(0, "SuccessCount"), AFFECTED_BLOCKS(1, "AffectedBlocks"), AFFECTED_ENTITIES(2, "AffectedEntities"), AFFECTED_ITEMS(3, "AffectedItems"), QUERY_RESULT(4, "QueryResult");

    final int f;
    final String g;

    private EnumCommandResult(int i, String s) {
        this.f = i;
        this.g = s;
    }

    public int a() {
        return this.f;
    }

    public String b() {
        return this.g;
    }

    public static String[] c() {
        String[] astring = new String[values().length];
        int i = 0;
        EnumCommandResult[] aenumcommandresult = values();
        int j = aenumcommandresult.length;

        for (int k = 0; k < j; ++k) {
            EnumCommandResult enumcommandresult = aenumcommandresult[k];

            astring[i++] = enumcommandresult.b();
        }

        return astring;
    }

    public static EnumCommandResult a(String s) {
        EnumCommandResult[] aenumcommandresult = values();
        int i = aenumcommandresult.length;

        for (int j = 0; j < i; ++j) {
            EnumCommandResult enumcommandresult = aenumcommandresult[j];

            if (enumcommandresult.b().equals(s)) {
                return enumcommandresult;
            }
        }

        return null;
    }
}
