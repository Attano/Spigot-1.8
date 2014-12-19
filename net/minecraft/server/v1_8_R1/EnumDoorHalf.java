package net.minecraft.server;

public enum EnumDoorHalf implements INamable {

    UPPER, LOWER;

    private EnumDoorHalf() {}

    public String toString() {
        return this.getName();
    }

    public String getName() {
        return this == EnumDoorHalf.UPPER ? "upper" : "lower";
    }
}
