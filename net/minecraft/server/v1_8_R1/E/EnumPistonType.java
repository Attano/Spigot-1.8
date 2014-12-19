package net.minecraft.server;

public enum EnumPistonType implements INamable {

    DEFAULT("normal"), STICKY("sticky");

    private final String c;

    private EnumPistonType(String s) {
        this.c = s;
    }

    public String toString() {
        return this.c;
    }

    public String getName() {
        return this.c;
    }
}
