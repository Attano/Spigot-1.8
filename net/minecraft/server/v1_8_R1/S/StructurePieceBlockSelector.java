package net.minecraft.server;

import java.util.Random;

public abstract class StructurePieceBlockSelector {

    protected IBlockData a;

    protected StructurePieceBlockSelector() {
        this.a = Blocks.AIR.getBlockData();
    }

    public abstract void a(Random random, int i, int j, int k, boolean flag);

    public IBlockData a() {
        return this.a;
    }
}
