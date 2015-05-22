package net.minecraft.server;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public enum EnumDirection implements INamable {

    DOWN(0, 1, -1, "down", EnumDirection.EnumAxisDirection.NEGATIVE, EnumDirection.EnumAxis.Y, new BaseBlockPosition(0, -1, 0)), UP(1, 0, -1, "up", EnumDirection.EnumAxisDirection.POSITIVE, EnumDirection.EnumAxis.Y, new BaseBlockPosition(0, 1, 0)), NORTH(2, 3, 2, "north", EnumDirection.EnumAxisDirection.NEGATIVE, EnumDirection.EnumAxis.Z, new BaseBlockPosition(0, 0, -1)), SOUTH(3, 2, 0, "south", EnumDirection.EnumAxisDirection.POSITIVE, EnumDirection.EnumAxis.Z, new BaseBlockPosition(0, 0, 1)), WEST(4, 5, 1, "west", EnumDirection.EnumAxisDirection.NEGATIVE, EnumDirection.EnumAxis.X, new BaseBlockPosition(-1, 0, 0)), EAST(5, 4, 3, "east", EnumDirection.EnumAxisDirection.POSITIVE, EnumDirection.EnumAxis.X, new BaseBlockPosition(1, 0, 0));

    private final int g;
    private final int h;
    private final int i;
    private final String j;
    private final EnumDirection.EnumAxis k;
    private final EnumDirection.EnumAxisDirection l;
    private final BaseBlockPosition m;
    private static final EnumDirection[] n = new EnumDirection[6];
    private static final EnumDirection[] o = new EnumDirection[4];
    private static final Map<String, EnumDirection> p = Maps.newHashMap();

    private EnumDirection(int i, int j, int k, String s, EnumDirection.EnumAxisDirection enumdirection_enumaxisdirection, EnumDirection.EnumAxis enumdirection_enumaxis, BaseBlockPosition baseblockposition) {
        this.g = i;
        this.i = k;
        this.h = j;
        this.j = s;
        this.k = enumdirection_enumaxis;
        this.l = enumdirection_enumaxisdirection;
        this.m = baseblockposition;
    }

    public int a() {
        return this.g;
    }

    public int b() {
        return this.i;
    }

    public EnumDirection.EnumAxisDirection c() {
        return this.l;
    }

    public EnumDirection opposite() {
        return fromType1(this.h);
    }

    public EnumDirection e() {
        switch (EnumDirection.SyntheticClass_1.b[this.ordinal()]) {
        case 1:
            return EnumDirection.EAST;

        case 2:
            return EnumDirection.SOUTH;

        case 3:
            return EnumDirection.WEST;

        case 4:
            return EnumDirection.NORTH;

        default:
            throw new IllegalStateException("Unable to get Y-rotated facing of " + this);
        }
    }

    public EnumDirection f() {
        switch (EnumDirection.SyntheticClass_1.b[this.ordinal()]) {
        case 1:
            return EnumDirection.WEST;

        case 2:
            return EnumDirection.NORTH;

        case 3:
            return EnumDirection.EAST;

        case 4:
            return EnumDirection.SOUTH;

        default:
            throw new IllegalStateException("Unable to get CCW facing of " + this);
        }
    }

    public int getAdjacentX() {
        return this.k == EnumDirection.EnumAxis.X ? this.l.a() : 0;
    }

    public int getAdjacentY() {
        return this.k == EnumDirection.EnumAxis.Y ? this.l.a() : 0;
    }

    public int getAdjacentZ() {
        return this.k == EnumDirection.EnumAxis.Z ? this.l.a() : 0;
    }

    public String j() {
        return this.j;
    }

    public EnumDirection.EnumAxis k() {
        return this.k;
    }

    public static EnumDirection fromType1(int i) {
        return EnumDirection.n[MathHelper.a(i % EnumDirection.n.length)];
    }

    public static EnumDirection fromType2(int i) {
        return EnumDirection.o[MathHelper.a(i % EnumDirection.o.length)];
    }

    public static EnumDirection fromAngle(double d0) {
        return fromType2(MathHelper.floor(d0 / 90.0D + 0.5D) & 3);
    }

    public static EnumDirection a(Random random) {
        return values()[random.nextInt(values().length)];
    }

    public String toString() {
        return this.j;
    }

    public String getName() {
        return this.j;
    }

    public static EnumDirection a(EnumDirection.EnumAxisDirection enumdirection_enumaxisdirection, EnumDirection.EnumAxis enumdirection_enumaxis) {
        EnumDirection[] aenumdirection = values();
        int i = aenumdirection.length;

        for (int j = 0; j < i; ++j) {
            EnumDirection enumdirection = aenumdirection[j];

            if (enumdirection.c() == enumdirection_enumaxisdirection && enumdirection.k() == enumdirection_enumaxis) {
                return enumdirection;
            }
        }

        throw new IllegalArgumentException("No such direction: " + enumdirection_enumaxisdirection + " " + enumdirection_enumaxis);
    }

    static {
        EnumDirection[] aenumdirection = values();
        int i = aenumdirection.length;

        for (int j = 0; j < i; ++j) {
            EnumDirection enumdirection = aenumdirection[j];

            EnumDirection.n[enumdirection.g] = enumdirection;
            if (enumdirection.k().c()) {
                EnumDirection.o[enumdirection.i] = enumdirection;
            }

            EnumDirection.p.put(enumdirection.j().toLowerCase(), enumdirection);
        }

    }

    static class SyntheticClass_1 {

        static final int[] a;
        static final int[] b;
        static final int[] c = new int[EnumDirection.EnumDirectionLimit.values().length];

        static {
            try {
                EnumDirection.SyntheticClass_1.c[EnumDirection.EnumDirectionLimit.HORIZONTAL.ordinal()] = 1;
            } catch (NoSuchFieldError nosuchfielderror) {
                ;
            }

            try {
                EnumDirection.SyntheticClass_1.c[EnumDirection.EnumDirectionLimit.VERTICAL.ordinal()] = 2;
            } catch (NoSuchFieldError nosuchfielderror1) {
                ;
            }

            b = new int[EnumDirection.values().length];

            try {
                EnumDirection.SyntheticClass_1.b[EnumDirection.NORTH.ordinal()] = 1;
            } catch (NoSuchFieldError nosuchfielderror2) {
                ;
            }

            try {
                EnumDirection.SyntheticClass_1.b[EnumDirection.EAST.ordinal()] = 2;
            } catch (NoSuchFieldError nosuchfielderror3) {
                ;
            }

            try {
                EnumDirection.SyntheticClass_1.b[EnumDirection.SOUTH.ordinal()] = 3;
            } catch (NoSuchFieldError nosuchfielderror4) {
                ;
            }

            try {
                EnumDirection.SyntheticClass_1.b[EnumDirection.WEST.ordinal()] = 4;
            } catch (NoSuchFieldError nosuchfielderror5) {
                ;
            }

            try {
                EnumDirection.SyntheticClass_1.b[EnumDirection.UP.ordinal()] = 5;
            } catch (NoSuchFieldError nosuchfielderror6) {
                ;
            }

            try {
                EnumDirection.SyntheticClass_1.b[EnumDirection.DOWN.ordinal()] = 6;
            } catch (NoSuchFieldError nosuchfielderror7) {
                ;
            }

            a = new int[EnumDirection.EnumAxis.values().length];

            try {
                EnumDirection.SyntheticClass_1.a[EnumDirection.EnumAxis.X.ordinal()] = 1;
            } catch (NoSuchFieldError nosuchfielderror8) {
                ;
            }

            try {
                EnumDirection.SyntheticClass_1.a[EnumDirection.EnumAxis.Y.ordinal()] = 2;
            } catch (NoSuchFieldError nosuchfielderror9) {
                ;
            }

            try {
                EnumDirection.SyntheticClass_1.a[EnumDirection.EnumAxis.Z.ordinal()] = 3;
            } catch (NoSuchFieldError nosuchfielderror10) {
                ;
            }

        }
    }

    public static enum EnumDirectionLimit implements Predicate<EnumDirection>, Iterable<EnumDirection> {

        HORIZONTAL, VERTICAL;

        private EnumDirectionLimit() {}

        public EnumDirection[] a() {
            switch (EnumDirection.SyntheticClass_1.c[this.ordinal()]) {
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

        public Iterator<EnumDirection> iterator() {
            return Iterators.forArray(this.a());
        }

        public boolean apply(Object object) {
            return this.a((EnumDirection) object);
        }
    }

    public static enum EnumAxisDirection {

        POSITIVE(1, "Towards positive"), NEGATIVE(-1, "Towards negative");

        private final int c;
        private final String d;

        private EnumAxisDirection(int i, String s) {
            this.c = i;
            this.d = s;
        }

        public int a() {
            return this.c;
        }

        public String toString() {
            return this.d;
        }
    }

    public static enum EnumAxis implements Predicate<EnumDirection>, INamable {

        X("x", EnumDirection.EnumDirectionLimit.HORIZONTAL), Y("y", EnumDirection.EnumDirectionLimit.VERTICAL), Z("z", EnumDirection.EnumDirectionLimit.HORIZONTAL);

        private static final Map<String, EnumDirection.EnumAxis> d = Maps.newHashMap();
        private final String e;
        private final EnumDirection.EnumDirectionLimit f;

        private EnumAxis(String s, EnumDirection.EnumDirectionLimit enumdirection_enumdirectionlimit) {
            this.e = s;
            this.f = enumdirection_enumdirectionlimit;
        }

        public String a() {
            return this.e;
        }

        public boolean b() {
            return this.f == EnumDirection.EnumDirectionLimit.VERTICAL;
        }

        public boolean c() {
            return this.f == EnumDirection.EnumDirectionLimit.HORIZONTAL;
        }

        public String toString() {
            return this.e;
        }

        public boolean a(EnumDirection enumdirection) {
            return enumdirection != null && enumdirection.k() == this;
        }

        public EnumDirection.EnumDirectionLimit d() {
            return this.f;
        }

        public String getName() {
            return this.e;
        }

        public boolean apply(Object object) {
            return this.a((EnumDirection) object);
        }

        static {
            EnumDirection.EnumAxis[] aenumdirection_enumaxis = values();
            int i = aenumdirection_enumaxis.length;

            for (int j = 0; j < i; ++j) {
                EnumDirection.EnumAxis enumdirection_enumaxis = aenumdirection_enumaxis[j];

                EnumDirection.EnumAxis.d.put(enumdirection_enumaxis.a().toLowerCase(), enumdirection_enumaxis);
            }

        }
    }
}
