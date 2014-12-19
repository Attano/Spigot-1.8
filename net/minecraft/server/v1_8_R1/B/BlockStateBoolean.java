package net.minecraft.server;

import com.google.common.collect.ImmutableSet;
import java.util.Collection;

public class BlockStateBoolean extends BlockState {

    private final ImmutableSet a = ImmutableSet.of(Boolean.valueOf(true), Boolean.valueOf(false));

    protected BlockStateBoolean(String s) {
        super(s, Boolean.class);
    }

    public Collection c() {
        return this.a;
    }

    public static BlockStateBoolean of(String s) {
        return new BlockStateBoolean(s);
    }

    public String a(Boolean obool) {
        return obool.toString();
    }

    public String a(Comparable comparable) {
        return this.a((Boolean) comparable);
    }
}
