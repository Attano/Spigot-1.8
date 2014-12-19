package net.minecraft.server;

public enum EnumBedPart implements INamable {

    HEAD("head"), FOOT("foot");

    private final String c;

    private EnumBedPart(String s) {
        this.c = s;
    }

    public String toString() {
        return this.c;
    }

    public String getName() {
        return this.c;
    }
}
