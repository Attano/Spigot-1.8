package net.minecraft.server;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlockStateList {

    private static final Joiner a = Joiner.on(", ");
    private static final Function b = new BlockStateListInnerClass1();
    private final Block c;
    private final ImmutableList d;
    private final ImmutableList e;

    public BlockStateList(Block block, IBlockState... aiblockstate) {
        this.c = block;
        Arrays.sort(aiblockstate, new BlockStateListInnerClass2(this));
        this.d = ImmutableList.copyOf(aiblockstate);
        LinkedHashMap linkedhashmap = Maps.newLinkedHashMap();
        ArrayList arraylist = Lists.newArrayList();
        Iterable iterable = IteratorUtils.a(this.e());
        Iterator iterator = iterable.iterator();

        while (iterator.hasNext()) {
            List list = (List) iterator.next();
            Map map = MapGeneratorUtils.b(this.d, list);
            BlockData blockdata = new BlockData(block, ImmutableMap.copyOf(map), (BlockStateListInnerClass1) null);

            linkedhashmap.put(map, blockdata);
            arraylist.add(blockdata);
        }

        iterator = arraylist.iterator();

        while (iterator.hasNext()) {
            BlockData blockdata1 = (BlockData) iterator.next();

            blockdata1.a((Map) linkedhashmap);
        }

        this.e = ImmutableList.copyOf(arraylist);
    }

    public ImmutableList a() {
        return this.e;
    }

    private List e() {
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

    public Collection d() {
        return this.d;
    }

    public String toString() {
        return Objects.toStringHelper(this).add("block", Block.REGISTRY.c(this.c)).add("properties", Iterables.transform(this.d, BlockStateList.b)).toString();
    }
}
