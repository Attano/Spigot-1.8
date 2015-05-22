package net.minecraft.server;

public class LongHashMap<V> {

    private transient LongHashMap.LongHashMapEntry<V>[] entries = new LongHashMap.LongHashMapEntry[4096];
    private transient int count;
    private int c;
    private int d = 3072;
    private final float e = 0.75F;
    private transient volatile int f;

    public LongHashMap() {
        this.c = this.entries.length - 1;
    }

    private static int g(long i) {
        return a((int) (i ^ i >>> 32));
    }

    private static int a(int i) {
        i ^= i >>> 20 ^ i >>> 12;
        return i ^ i >>> 7 ^ i >>> 4;
    }

    private static int a(int i, int j) {
        return i & j;
    }

    public int count() {
        return this.count;
    }

    public V getEntry(long i) {
        int j = g(i);

        for (LongHashMap.LongHashMapEntry longhashmap_longhashmapentry = this.entries[a(j, this.c)]; longhashmap_longhashmapentry != null; longhashmap_longhashmapentry = longhashmap_longhashmapentry.c) {
            if (longhashmap_longhashmapentry.a == i) {
                return longhashmap_longhashmapentry.b;
            }
        }

        return null;
    }

    public boolean contains(long i) {
        return this.c(i) != null;
    }

    final LongHashMap.LongHashMapEntry<V> c(long i) {
        int j = g(i);

        for (LongHashMap.LongHashMapEntry longhashmap_longhashmapentry = this.entries[a(j, this.c)]; longhashmap_longhashmapentry != null; longhashmap_longhashmapentry = longhashmap_longhashmapentry.c) {
            if (longhashmap_longhashmapentry.a == i) {
                return longhashmap_longhashmapentry;
            }
        }

        return null;
    }

    public void put(long i, V v0) {
        int j = g(i);
        int k = a(j, this.c);

        for (LongHashMap.LongHashMapEntry longhashmap_longhashmapentry = this.entries[k]; longhashmap_longhashmapentry != null; longhashmap_longhashmapentry = longhashmap_longhashmapentry.c) {
            if (longhashmap_longhashmapentry.a == i) {
                longhashmap_longhashmapentry.b = v0;
                return;
            }
        }

        ++this.f;
        this.a(j, i, v0, k);
    }

    private void b(int i) {
        LongHashMap.LongHashMapEntry[] alonghashmap_longhashmapentry = this.entries;
        int j = alonghashmap_longhashmapentry.length;

        if (j == 1073741824) {
            this.d = Integer.MAX_VALUE;
        } else {
            LongHashMap.LongHashMapEntry[] alonghashmap_longhashmapentry1 = new LongHashMap.LongHashMapEntry[i];

            this.a(alonghashmap_longhashmapentry1);
            this.entries = alonghashmap_longhashmapentry1;
            this.c = this.entries.length - 1;
            this.d = (int) ((float) i * this.e);
        }
    }

    private void a(LongHashMap.LongHashMapEntry<V>[] alonghashmap_longhashmapentry) {
        LongHashMap.LongHashMapEntry[] alonghashmap_longhashmapentry1 = this.entries;
        int i = alonghashmap_longhashmapentry.length;

        for (int j = 0; j < alonghashmap_longhashmapentry1.length; ++j) {
            LongHashMap.LongHashMapEntry longhashmap_longhashmapentry = alonghashmap_longhashmapentry1[j];

            if (longhashmap_longhashmapentry != null) {
                alonghashmap_longhashmapentry1[j] = null;

                LongHashMap.LongHashMapEntry longhashmap_longhashmapentry1;

                do {
                    longhashmap_longhashmapentry1 = longhashmap_longhashmapentry.c;
                    int k = a(longhashmap_longhashmapentry.d, i - 1);

                    longhashmap_longhashmapentry.c = alonghashmap_longhashmapentry[k];
                    alonghashmap_longhashmapentry[k] = longhashmap_longhashmapentry;
                    longhashmap_longhashmapentry = longhashmap_longhashmapentry1;
                } while (longhashmap_longhashmapentry1 != null);
            }
        }

    }

    public V remove(long i) {
        LongHashMap.LongHashMapEntry longhashmap_longhashmapentry = this.e(i);

        return longhashmap_longhashmapentry == null ? null : longhashmap_longhashmapentry.b;
    }

    final LongHashMap.LongHashMapEntry<V> e(long i) {
        int j = g(i);
        int k = a(j, this.c);
        LongHashMap.LongHashMapEntry longhashmap_longhashmapentry = this.entries[k];

        LongHashMap.LongHashMapEntry longhashmap_longhashmapentry1;
        LongHashMap.LongHashMapEntry longhashmap_longhashmapentry2;

        for (longhashmap_longhashmapentry1 = longhashmap_longhashmapentry; longhashmap_longhashmapentry1 != null; longhashmap_longhashmapentry1 = longhashmap_longhashmapentry2) {
            longhashmap_longhashmapentry2 = longhashmap_longhashmapentry1.c;
            if (longhashmap_longhashmapentry1.a == i) {
                ++this.f;
                --this.count;
                if (longhashmap_longhashmapentry == longhashmap_longhashmapentry1) {
                    this.entries[k] = longhashmap_longhashmapentry2;
                } else {
                    longhashmap_longhashmapentry.c = longhashmap_longhashmapentry2;
                }

                return longhashmap_longhashmapentry1;
            }

            longhashmap_longhashmapentry = longhashmap_longhashmapentry1;
        }

        return longhashmap_longhashmapentry1;
    }

    private void a(int i, long j, V v0, int k) {
        LongHashMap.LongHashMapEntry longhashmap_longhashmapentry = this.entries[k];

        this.entries[k] = new LongHashMap.LongHashMapEntry(i, j, v0, longhashmap_longhashmapentry);
        if (this.count++ >= this.d) {
            this.b(2 * this.entries.length);
        }

    }

    static class LongHashMapEntry<V> {

        final long a;
        V b;
        LongHashMap.LongHashMapEntry<V> c;
        final int d;

        LongHashMapEntry(int i, long j, V v0, LongHashMap.LongHashMapEntry<V> longhashmap_longhashmapentry) {
            this.b = v0;
            this.c = longhashmap_longhashmapentry;
            this.a = j;
            this.d = i;
        }

        public final long a() {
            return this.a;
        }

        public final V b() {
            return this.b;
        }

        public final boolean equals(Object object) {
            if (!(object instanceof LongHashMap.LongHashMapEntry)) {
                return false;
            } else {
                LongHashMap.LongHashMapEntry longhashmap_longhashmapentry = (LongHashMap.LongHashMapEntry) object;
                Long olong = Long.valueOf(this.a());
                Long olong1 = Long.valueOf(longhashmap_longhashmapentry.a());

                if (olong == olong1 || olong != null && olong.equals(olong1)) {
                    Object object1 = this.b();
                    Object object2 = longhashmap_longhashmapentry.b();

                    if (object1 == object2 || object1 != null && object1.equals(object2)) {
                        return true;
                    }
                }

                return false;
            }
        }

        public final int hashCode() {
            return LongHashMap.g(this.a);
        }

        public final String toString() {
            return this.a() + "=" + this.b();
        }
    }
}
