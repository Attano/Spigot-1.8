package net.minecraft.server;

public enum EnumSlabHalf implements INamable {

    TOP("top"), BOTTOM("bottom");

    private final String c;

    private EnumSlabHalf(String s) {
        this.c = s;
    }

    public String toString() {
        return this.c;
    }

    public String getName() {
        return this.c;
    }
}
