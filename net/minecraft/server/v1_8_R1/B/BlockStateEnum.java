package net.minecraft.server;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class BlockStateEnum extends BlockState {

    private final ImmutableSet a;
    private final Map b = Maps.newHashMap();

    protected BlockStateEnum(String s, Class oclass, Collection collection) {
        super(s, oclass);
        this.a = ImmutableSet.copyOf(collection);
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            Enum oenum = (Enum) iterator.next();
            String s1 = ((INamable) oenum).getName();

            if (this.b.containsKey(s1)) {
                throw new IllegalArgumentException("Multiple values have the same name \'" + s1 + "\'");
            }

            this.b.put(s1, oenum);
        }

    }

    public Collection c() {
        return this.a;
    }

    public String a(Enum oenum) {
        return ((INamable) oenum).getName();
    }

    public static BlockStateEnum of(String s, Class oclass) {
        return a(s, oclass, Predicates.alwaysTrue());
    }

    public static BlockStateEnum a(String s, Class oclass, Predicate predicate) {
        return a(s, oclass, Collections2.filter(Lists.newArrayList(oclass.getEnumConstants()), predicate));
    }

    public static BlockStateEnum of(String s, Class oclass, Enum... aenum) {
        return a(s, oclass, (Collection) Lists.newArrayList(aenum));
    }

    public static BlockStateEnum a(String s, Class oclass, Collection collection) {
        return new BlockStateEnum(s, oclass, collection);
    }

    public String a(Comparable comparable) {
        return this.a((Enum) comparable);
    }
}
