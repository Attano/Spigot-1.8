package net.minecraft.server;

import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class ShapeDetectorBuilder {

    private static final Joiner a = Joiner.on(",");
    private final List<String[]> b = Lists.newArrayList();
    private final Map<Character, Predicate<ShapeDetectorBlock>> c = Maps.newHashMap();
    private int d;
    private int e;

    private ShapeDetectorBuilder() {
        this.c.put(Character.valueOf(' '), Predicates.alwaysTrue());
    }

    public ShapeDetectorBuilder a(String... astring) {
        if (!ArrayUtils.isEmpty(astring) && !StringUtils.isEmpty(astring[0])) {
            if (this.b.isEmpty()) {
                this.d = astring.length;
                this.e = astring[0].length();
            }

            if (astring.length != this.d) {
                throw new IllegalArgumentException("Expected aisle with height of " + this.d + ", but was given one with a height of " + astring.length + ")");
            } else {
                String[] astring1 = astring;
                int i = astring.length;

                for (int j = 0; j < i; ++j) {
                    String s = astring1[j];

                    if (s.length() != this.e) {
                        throw new IllegalArgumentException("Not all rows in the given aisle are the correct width (expected " + this.e + ", found one with " + s.length() + ")");
                    }

                    char[] achar = s.toCharArray();
                    int k = achar.length;

                    for (int l = 0; l < k; ++l) {
                        char c0 = achar[l];

                        if (!this.c.containsKey(Character.valueOf(c0))) {
                            this.c.put(Character.valueOf(c0), (Object) null);
                        }
                    }
                }

                this.b.add(astring);
                return this;
            }
        } else {
            throw new IllegalArgumentException("Empty pattern for aisle");
        }
    }

    public static ShapeDetectorBuilder a() {
        return new ShapeDetectorBuilder();
    }

    public ShapeDetectorBuilder a(char c0, Predicate<ShapeDetectorBlock> predicate) {
        this.c.put(Character.valueOf(c0), predicate);
        return this;
    }

    public ShapeDetector b() {
        return new ShapeDetector(this.c());
    }

    private Predicate<ShapeDetectorBlock>[][][] c() {
        this.d();
        Predicate[][][] apredicate = (Predicate[][][]) ((Predicate[][][]) Array.newInstance(Predicate.class, new int[] { this.b.size(), this.d, this.e}));

        for (int i = 0; i < this.b.size(); ++i) {
            for (int j = 0; j < this.d; ++j) {
                for (int k = 0; k < this.e; ++k) {
                    apredicate[i][j][k] = (Predicate) this.c.get(Character.valueOf(((String[]) this.b.get(i))[j].charAt(k)));
                }
            }
        }

        return apredicate;
    }

    private void d() {
        ArrayList arraylist = Lists.newArrayList();
        Iterator iterator = this.c.entrySet().iterator();

        while (iterator.hasNext()) {
            Entry entry = (Entry) iterator.next();

            if (entry.getValue() == null) {
                arraylist.add(entry.getKey());
            }
        }

        if (!arraylist.isEmpty()) {
            throw new IllegalStateException("Predicates for character(s) " + ShapeDetectorBuilder.a.join(arraylist) + " are missing");
        }
    }
}
