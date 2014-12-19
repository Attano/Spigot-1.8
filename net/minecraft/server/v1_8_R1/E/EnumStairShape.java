package net.minecraft.server;

public enum EnumStairShape implements INamable {

    STRAIGHT("straight"), INNER_LEFT("inner_left"), INNER_RIGHT("inner_right"), OUTER_LEFT("outer_left"), OUTER_RIGHT("outer_right");

    private final String f;

    private EnumStairShape(String s) {
        this.f = s;
    }

    public String toString() {
        return this.f;
    }

    public String getName() {
        return this.f;
    }
}
