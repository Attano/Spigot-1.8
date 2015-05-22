package net.minecraft.server;

import com.google.common.base.Predicate;

public class BlockPredicate implements Predicate<IBlockData> {

    private final Block a;

    private BlockPredicate(Block block) {
        this.a = block;
    }

    public static BlockPredicate a(Block block) {
        return new BlockPredicate(block);
    }

    public boolean a(IBlockData iblockdata) {
        return iblockdata != null && iblockdata.getBlock() == this.a;
    }

    public boolean apply(Object object) {
        return this.a((IBlockData) object);
    }
}
