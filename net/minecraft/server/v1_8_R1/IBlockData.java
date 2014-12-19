package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import java.util.Collection;

public interface IBlockData {

    Collection a();

    Comparable get(IBlockState iblockstate);

    IBlockData set(IBlockState iblockstate, Comparable comparable);

    IBlockData a(IBlockState iblockstate);

    ImmutableMap b();

    Block getBlock();
}
