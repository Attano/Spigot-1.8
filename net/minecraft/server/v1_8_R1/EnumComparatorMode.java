package net.minecraft.server;

public enum EnumComparatorMode implements INamable {

    COMPARE("compare"), SUBTRACT("subtract");

    private final String c;

    private EnumComparatorMode(String s) {
        this.c = s;
    }

    public String toString() {
        return this.c;
    }

    public String getName() {
        return this.c;
    }
}
