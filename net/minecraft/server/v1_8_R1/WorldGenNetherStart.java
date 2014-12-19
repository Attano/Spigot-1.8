package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenNetherStart extends StructureStart {

    public WorldGenNetherStart() {}

    public WorldGenNetherStart(World world, Random random, int i, int j) {
        super(i, j);
        WorldGenNetherPiece15 worldgennetherpiece15 = new WorldGenNetherPiece15(random, (i << 4) + 2, (j << 4) + 2);

        this.a.add(worldgennetherpiece15);
        worldgennetherpiece15.a((StructurePiece) worldgennetherpiece15, (List) this.a, random);
        List list = worldgennetherpiece15.e;

        while (!list.isEmpty()) {
            int k = random.nextInt(list.size());
            StructurePiece structurepiece = (StructurePiece) list.remove(k);

            structurepiece.a((StructurePiece) worldgennetherpiece15, (List) this.a, random);
        }

        this.c();
        this.a(world, random, 48, 70);
    }
}
