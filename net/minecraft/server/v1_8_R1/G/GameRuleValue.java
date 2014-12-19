package net.minecraft.server;

class GameRuleValue {

    private String a;
    private boolean b;
    private int c;
    private double d;
    private final EnumGameRuleType e;

    public GameRuleValue(String s, EnumGameRuleType enumgameruletype) {
        this.e = enumgameruletype;
        this.a(s);
    }

    public void a(String s) {
        this.a = s;
        this.b = Boolean.parseBoolean(s);
        this.c = this.b ? 1 : 0;

        try {
            this.c = Integer.parseInt(s);
        } catch (NumberFormatException numberformatexception) {
            ;
        }

        try {
            this.d = Double.parseDouble(s);
        } catch (NumberFormatException numberformatexception1) {
            ;
        }

    }

    public String a() {
        return this.a;
    }

    public boolean b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public EnumGameRuleType e() {
        return this.e;
    }
}
