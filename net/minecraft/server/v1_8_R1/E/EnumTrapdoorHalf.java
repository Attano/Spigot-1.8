package net.minecraft.server;

public enum EnumTrapdoorHalf implements INamable {

    TOP("top"), BOTTOM("bottom");

    private final String c;

    private EnumTrapdoorHalf(String s) {
        this.c = s;
    }

    public String toString() {
        return this.c;
    }

    public String getName() {
        return this.c;
    }
}
