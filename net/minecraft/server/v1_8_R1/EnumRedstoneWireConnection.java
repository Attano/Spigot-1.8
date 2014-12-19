package net.minecraft.server;

enum EnumRedstoneWireConnection implements INamable {

    UP("up"), SIDE("side"), NONE("none");

    private final String d;

    private EnumRedstoneWireConnection(String s) {
        this.d = s;
    }

    public String toString() {
        return this.getName();
    }

    public String getName() {
        return this.d;
    }
}
