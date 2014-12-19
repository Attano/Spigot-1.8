package net.minecraft.server;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class BlockData extends BlockDataAbstract {

    private final Block a;
    private final ImmutableMap b;
    private ImmutableTable c;

    private BlockData(Block block, ImmutableMap immutablemap) {
        this.a = block;
        this.b = immutablemap;
    }

    public Collection a() {
        return Collections.unmodifiableCollection(this.b.keySet());
    }

    public Comparable get(IBlockState iblockstate) {
        if (!this.b.containsKey(iblockstate)) {
            throw new IllegalArgumentException("Cannot get property " + iblockstate + " as it does not exist in " + this.a.O());
        } else {
            return (Comparable) iblockstate.b().cast(this.b.get(iblockstate));
        }
    }

    public IBlockData set(IBlockState iblockstate, Comparable comparable) {
        if (!this.b.containsKey(iblockstate)) {
            throw new IllegalArgumentException("Cannot set property " + iblockstate + " as it does not exist in " + this.a.O());
        } else if (!iblockstate.c().contains(comparable)) {
            throw new IllegalArgumentException("Cannot set property " + iblockstate + " to " + comparable + " on block " + Block.REGISTRY.c(this.a) + ", it is not an allowed value");
        } else {
            return (IBlockData) (this.b.get(iblockstate) == comparable ? this : (IBlockData) this.c.get(iblockstate, comparable));
        }
    }

    public ImmutableMap b() {
        return this.b;
    }

    public Block getBlock() {
        return this.a;
    }

    public boolean equals(Object object) {
        return this == object;
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    public void a(Map map) {
        if (this.c != null) {
            throw new IllegalStateException();
        } else {
            HashBasedTable hashbasedtable = HashBasedTable.create();
            Iterator iterator = this.b.keySet().iterator();

            while (iterator.hasNext()) {
                IBlockState iblockstate = (IBlockState) iterator.next();
                Iterator iterator1 = iblockstate.c().iterator();

                while (iterator1.hasNext()) {
                    Comparable comparable = (Comparable) iterator1.next();

                    if (comparable != this.b.get(iblockstate)) {
                        hashbasedtable.put(iblockstate, comparable, map.get(this.b(iblockstate, comparable)));
                    }
                }
            }

            this.c = ImmutableTable.copyOf(hashbasedtable);
        }
    }

    private Map b(IBlockState iblockstate, Comparable comparable) {
        HashMap hashmap = Maps.newHashMap(this.b);

        hashmap.put(iblockstate, comparable);
        return hashmap;
    }

    BlockData(Block block, ImmutableMap immutablemap, BlockStateListInnerClass1 blockstatelistinnerclass1) {
        this(block, immutablemap);
    }
}
