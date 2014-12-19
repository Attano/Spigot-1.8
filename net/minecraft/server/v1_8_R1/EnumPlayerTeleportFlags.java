package net.minecraft.server;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;

public enum EnumPlayerTeleportFlags {

    X(0), Y(1), Z(2), Y_ROT(3), X_ROT(4);

    private int f;

    private EnumPlayerTeleportFlags(int i) {
        this.f = i;
    }

    private int a() {
        return 1 << this.f;
    }

    private boolean b(int i) {
        return (i & this.a()) == this.a();
    }

    public static Set a(int i) {
        EnumSet enumset = EnumSet.noneOf(EnumPlayerTeleportFlags.class);
        EnumPlayerTeleportFlags[] aenumplayerteleportflags = values();
        int j = aenumplayerteleportflags.length;

        for (int k = 0; k < j; ++k) {
            EnumPlayerTeleportFlags enumplayerteleportflags = aenumplayerteleportflags[k];

            if (enumplayerteleportflags.b(i)) {
                enumset.add(enumplayerteleportflags);
            }
        }

        return enumset;
    }

    public static int a(Set set) {
        int i = 0;

        EnumPlayerTeleportFlags enumplayerteleportflags;

        for (Iterator iterator = set.iterator(); iterator.hasNext(); i |= enumplayerteleportflags.a()) {
            enumplayerteleportflags = (EnumPlayerTeleportFlags) iterator.next();
        }

        return i;
    }
}
