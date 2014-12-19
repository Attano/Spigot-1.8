package net.minecraft.server;

import java.util.Random;

class WorldGenStrongholdStones extends StructurePieceBlockSelector {

    private WorldGenStrongholdStones() {}

    public void a(Random random, int i, int j, int k, boolean flag) {
        if (flag) {
            float f = random.nextFloat();

            if (f < 0.2F) {
                this.a = Blocks.STONEBRICK.fromLegacyData(BlockSmoothBrick.N);
            } else if (f < 0.5F) {
                this.a = Blocks.STONEBRICK.fromLegacyData(BlockSmoothBrick.M);
            } else if (f < 0.55F) {
                this.a = Blocks.MONSTER_EGG.fromLegacyData(EnumMonsterEggVarient.STONEBRICK.a());
            } else {
                this.a = Blocks.STONEBRICK.getBlockData();
            }
        } else {
            this.a = Blocks.AIR.getBlockData();
        }

    }

    WorldGenStrongholdStones(WorldGenStrongholdUnknown worldgenstrongholdunknown) {
        this();
    }
}
