package net.minecraft.server;

public enum EnumLogRotation implements INamable {

    X("x"), Y("y"), Z("z"), NONE("none");

    private final String e;

    private EnumLogRotation(String s) {
        this.e = s;
    }

    public String toString() {
        return this.e;
    }

    public static EnumLogRotation a(EnumAxis enumaxis) {
        switch (SwitchHelperAxis2.a[enumaxis.ordinal()]) {
        case 1:
            return EnumLogRotation.X;

        case 2:
            return EnumLogRotation.Y;

        case 3:
            return EnumLogRotation.Z;

        default:
            return EnumLogRotation.NONE;
        }
    }

    public String getName() {
        return this.e;
    }
}
