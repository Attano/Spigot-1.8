package net.minecraft.server;

public enum EnumMonsterEggVarient implements INamable {

    STONE(0, "stone"), COBBLESTONE(1, "cobblestone", "cobble"), STONEBRICK(2, "stone_brick", "brick"), MOSSY_STONEBRICK(3, "mossy_brick", "mossybrick"), CRACKED_STONEBRICK(4, "cracked_brick", "crackedbrick"), CHISELED_STONEBRICK(5, "chiseled_brick", "chiseledbrick");

    private static final EnumMonsterEggVarient[] g = new EnumMonsterEggVarient[values().length];
    private final int h;
    private final String i;
    private final String j;

    private EnumMonsterEggVarient(int i, String s) {
        this(i, s, s);
    }

    private EnumMonsterEggVarient(int i, String s, String s1) {
        this.h = i;
        this.i = s;
        this.j = s1;
    }

    public int a() {
        return this.h;
    }

    public String toString() {
        return this.i;
    }

    public static EnumMonsterEggVarient a(int i) {
        if (i < 0 || i >= EnumMonsterEggVarient.g.length) {
            i = 0;
        }

        return EnumMonsterEggVarient.g[i];
    }

    public String getName() {
        return this.i;
    }

    public String c() {
        return this.j;
    }

    public abstract IBlockData d();

    public static EnumMonsterEggVarient a(IBlockData iblockdata) {
        EnumMonsterEggVarient[] aenummonstereggvarient = values();
        int i = aenummonstereggvarient.length;

        for (int j = 0; j < i; ++j) {
            EnumMonsterEggVarient enummonstereggvarient = aenummonstereggvarient[j];

            if (iblockdata == enummonstereggvarient.d()) {
                return enummonstereggvarient;
            }
        }

        return EnumMonsterEggVarient.STONE;
    }

    EnumMonsterEggVarient(int i, String s, SwitchHelperMonsterEggVarient switchhelpermonstereggvarient) {
        this(i, s);
    }

    EnumMonsterEggVarient(int i, String s, String s1, SwitchHelperMonsterEggVarient switchhelpermonstereggvarient) {
        this(i, s, s1);
    }

    static {
        EnumMonsterEggVarient[] aenummonstereggvarient = values();
        int i = aenummonstereggvarient.length;

        for (int j = 0; j < i; ++j) {
            EnumMonsterEggVarient enummonstereggvarient = aenummonstereggvarient[j];

            EnumMonsterEggVarient.g[enummonstereggvarient.a()] = enummonstereggvarient;
        }

    }
}
