package net.minecraft.server;

public enum EnumDoorHinge implements INamable {

    LEFT, RIGHT;

    private EnumDoorHinge() {}

    public String toString() {
        return this.getName();
    }

    public String getName() {
        return this == EnumDoorHinge.LEFT ? "left" : "right";
    }
}
