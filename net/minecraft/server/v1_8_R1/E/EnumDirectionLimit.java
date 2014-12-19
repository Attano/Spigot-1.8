package net.minecraft.server;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import java.util.Iterator;
import java.util.Random;

public enum EnumDirectionLimit implements Predicate, Iterable {

    HORIZONTAL, VERTICAL;

    private EnumDirectionLimit() {}

    public EnumDirection[] a() {
        switch (SwitchHelperDirection2.c[this.ordinal()]) {
        case 1:
            return new EnumDirection[] { EnumDirection.NORTH, EnumDirection.EAST, EnumDirection.SOUTH, EnumDirection.WEST};

        case 2:
            return new EnumDirection[] { EnumDirection.UP, EnumDirection.DOWN};

        default:
            throw new Error("Someone\'s been tampering with the universe!");
        }
    }

    public EnumDirection a(Random random) {
        EnumDirection[] aenumdirection = this.a();

        return aenumdirection[random.nextInt(aenumdirection.length)];
    }

    public boolean a(EnumDirection enumdirection) {
        return enumdirection != null && enumdirection.k().d() == this;
    }

    public Iterator iterator() {
        return Iterators.forArray(this.a());
    }

    public boolean apply(Object object) {
        return this.a((EnumDirection) object);
    }
}
