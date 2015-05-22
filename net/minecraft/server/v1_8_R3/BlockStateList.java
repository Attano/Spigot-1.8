package net.minecraft.server;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlockStateList {

    private static final Joiner a = Joiner.on(", ");
    private static final Function<IBlockState, String> b = new Function() {
        public String a(IBlockState iblockstate) {
            return iblockstate == null ? "<NULL>" : iblockstate.a();
        }

        public Object apply(Object object) {
            return this.a((IBlockState) object);
        }
    };
    private final Block c;
    private final ImmutableList<IBlockState> d;
    private final ImmutableList<IBlockData> e;

    public BlockStateList(Block block, IBlockState... aiblockstate) {
        this.c = block;
        Arrays.sort(aiblockstate, new Comparator() {
            public int a(IBlockState iblockstate, IBlockState iblockstate1) {
                return iblockstate.a().compareTo(iblockstate1.a());
            }

            public int compare(Object object, Object object1) {
                return this.a((IBlockState) object, (IBlockState) object1);
            }
        });
        this.d = ImmutableList.copyOf(aiblockstate);
        LinkedHashMap linkedhashmap = Maps.newLinkedHashMap();
        ArrayList arraylist = Lists.newArrayList();
        Iterable iterable = IteratorUtils.a(this.e());
        Iterator iterator = iterable.iterator();

        while (iterator.hasNext()) {
            List list = (List) iterator.next();
            Map map = MapGeneratorUtils.b(this.d, list);
            BlockStateList.BlockData blockstatelist_blockdata = new BlockStateList.BlockData(block, ImmutableMap.copyOf(map), null);

            linkedhashmap.put(map, blockstatelist_blockdata);
            arraylist.add(blockstatelist_blockdata);
        }

        iterator = arraylist.iterator();

        while (iterator.hasNext()) {
            BlockStateList.BlockData blockstatelist_blockdata1 = (BlockStateList.BlockData) iterator.next();

            blockstatelist_blockdata1.a((Map) linkedhashmap);
        }

        this.e = ImmutableList.copyOf(arraylist);
    }

    public ImmutableList<IBlockData> a() {
        return this.e;
    }

    private List<Iterable<Comparable>> e() {
        ArrayList arraylist = Lists.newArrayList();

        for (int i = 0; i < this.d.size(); ++i) {
            arraylist.add(((IBlockState) this.d.get(i)).c());
        }

        return arraylist;
    }

    public IBlockData getBlockData() {
        return (IBlockData) this.e.get(0);
    }

    public Block getBlock() {
        return this.c;
    }

    public Collection<IBlockState> d() {
        return this.d;
    }

    public String toString() {
        return Objects.toStringHelper(this).add("block", Block.REGISTRY.c(this.c)).add("properties", Iterables.transform(this.d, BlockStateList.b)).toString();
    }

    static class BlockData extends BlockDataAbstract {

        private final Block a;
        private final ImmutableMap<IBlockState, Comparable> b;
        private ImmutableTable<IBlockState, Comparable, IBlockData> c;

        private BlockData(Block block, ImmutableMap<IBlockState, Comparable> immutablemap) {
            this.a = block;
            this.b = immutablemap;
        }

        public Collection<IBlockState> a() {
            return Collections.unmodifiableCollection(this.b.keySet());
        }

        public <T extends Comparable<T>> T get(IBlockState<T> iblockstate) {
            if (!this.b.containsKey(iblockstate)) {
                throw new IllegalArgumentException("Cannot get property " + iblockstate + " as it does not exist in " + this.a.P());
            } else {
                return (Comparable) iblockstate.b().cast(this.b.get(iblockstate));
            }
        }

        public <T extends Comparable<T>, V extends T> IBlockData set(IBlockState<T> iblockstate, V v0) {
            if (!this.b.containsKey(iblockstate)) {
                throw new IllegalArgumentException("Cannot set property " + iblockstate + " as it does not exist in " + this.a.P());
            } else if (!iblockstate.c().contains(v0)) {
                throw new IllegalArgumentException("Cannot set property " + iblockstate + " to " + v0 + " on block " + Block.REGISTRY.c(this.a) + ", it is not an allowed value");
            } else {
                return (IBlockData) (this.b.get(iblockstate) == v0 ? this : (IBlockData) this.c.get(iblockstate, v0));
            }
        }

        public ImmutableMap<IBlockState, Comparable> b() {
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

        public void a(Map<Map<IBlockState, Comparable>, BlockStateList.BlockData> map) {
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

        private Map<IBlockState, Comparable> b(IBlockState iblockstate, Comparable comparable) {
            HashMap hashmap = Maps.newHashMap(this.b);

            hashmap.put(iblockstate, comparable);
            return hashmap;
        }

        BlockData(Block block, ImmutableMap immutablemap, Object object) {
            this(block, immutablemap);
        }
    }
}
