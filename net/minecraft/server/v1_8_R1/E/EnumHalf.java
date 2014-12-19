package net.minecraft.server;

public enum EnumHalf implements INamable {

    TOP("top"), BOTTOM("bottom");

    private final String c;

    private EnumHalf(String s) {
        this.c = s;
    }

    public String toString() {
        return this.c;
    }

    public String getName() {
        return this.c;
    }
}
