package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;

public class WorldGenStrongholdLibrary extends WorldGenStrongholdPiece {

    private static final List a = Lists.newArrayList(new StructurePieceTreasure[] { new StructurePieceTreasure(Items.BOOK, 0, 1, 3, 20), new StructurePieceTreasure(Items.PAPER, 0, 2, 7, 20), new StructurePieceTreasure(Items.MAP, 0, 1, 1, 1), new StructurePieceTreasure(Items.COMPASS, 0, 1, 1, 1)});
    private boolean b;

    public WorldGenStrongholdLibrary() {}

    public WorldGenStrongholdLibrary(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
        super(i);
        this.m = enumdirection;
        this.d = this.a(random);
        this.l = structureboundingbox;
        this.b = structureboundingbox.d() > 6;
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.setBoolean("Tall", this.b);
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.b = nbttagcompound.getBoolean("Tall");
    }

    public static WorldGenStrongholdLibrary a(List list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -4, -1, 0, 14, 11, 15, enumdirection);

        if (!a(structureboundingbox) || StructurePiece.a(list, structureboundingbox) != null) {
            structureboundingbox = StructureBoundingBox.a(i, j, k, -4, -1, 0, 14, 6, 15, enumdirection);
            if (!a(structureboundingbox) || StructurePiece.a(list, structureboundingbox) != null) {
                return null;
            }
        }

        return new WorldGenStrongholdLibrary(l, random, structureboundingbox, enumdirection);
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a(world, structureboundingbox)) {
            return false;
        } else {
            byte b0 = 11;

            if (!this.b) {
                b0 = 6;
            }

            this.a(world, structureboundingbox, 0, 0, 0, 13, b0 - 1, 14, true, random, WorldGenStrongholdPieces.c());
            this.a(world, random, structureboundingbox, this.d, 4, 1, 0);
            this.a(world, structureboundingbox, random, 0.07F, 2, 1, 1, 11, 4, 13, Blocks.WEB.getBlockData(), Blocks.WEB.getBlockData(), false);
            boolean flag = true;
            boolean flag1 = true;

            int i;

            for (i = 1; i <= 13; ++i) {
                if ((i - 1) % 4 == 0) {
                    this.a(world, structureboundingbox, 1, 1, i, 1, 4, i, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
                    this.a(world, structureboundingbox, 12, 1, i, 12, 4, i, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
                    this.a(world, Blocks.TORCH.getBlockData(), 2, 3, i, structureboundingbox);
                    this.a(world, Blocks.TORCH.getBlockData(), 11, 3, i, structureboundingbox);
                    if (this.b) {
                        this.a(world, structureboundingbox, 1, 6, i, 1, 9, i, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
                        this.a(world, structureboundingbox, 12, 6, i, 12, 9, i, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
                    }
                } else {
                    this.a(world, structureboundingbox, 1, 1, i, 1, 4, i, Blocks.BOOKSHELF.getBlockData(), Blocks.BOOKSHELF.getBlockData(), false);
                    this.a(world, structureboundingbox, 12, 1, i, 12, 4, i, Blocks.BOOKSHELF.getBlockData(), Blocks.BOOKSHELF.getBlockData(), false);
                    if (this.b) {
                        this.a(world, structureboundingbox, 1, 6, i, 1, 9, i, Blocks.BOOKSHELF.getBlockData(), Blocks.BOOKSHELF.getBlockData(), false);
                        this.a(world, structureboundingbox, 12, 6, i, 12, 9, i, Blocks.BOOKSHELF.getBlockData(), Blocks.BOOKSHELF.getBlockData(), false);
                    }
                }
            }

            for (i = 3; i < 12; i += 2) {
                this.a(world, structureboundingbox, 3, 1, i, 4, 3, i, Blocks.BOOKSHELF.getBlockData(), Blocks.BOOKSHELF.getBlockData(), false);
                this.a(world, structureboundingbox, 6, 1, i, 7, 3, i, Blocks.BOOKSHELF.getBlockData(), Blocks.BOOKSHELF.getBlockData(), false);
                this.a(world, structureboundingbox, 9, 1, i, 10, 3, i, Blocks.BOOKSHELF.getBlockData(), Blocks.BOOKSHELF.getBlockData(), false);
            }

            if (this.b) {
                this.a(world, structureboundingbox, 1, 5, 1, 3, 5, 13, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
                this.a(world, structureboundingbox, 10, 5, 1, 12, 5, 13, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
                this.a(world, structureboundingbox, 4, 5, 1, 9, 5, 2, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
                this.a(world, structureboundingbox, 4, 5, 12, 9, 5, 13, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
                this.a(world, Blocks.PLANKS.getBlockData(), 9, 5, 11, structureboundingbox);
                this.a(world, Blocks.PLANKS.getBlockData(), 8, 5, 11, structureboundingbox);
                this.a(world, Blocks.PLANKS.getBlockData(), 9, 5, 10, structureboundingbox);
                this.a(world, structureboundingbox, 3, 6, 2, 3, 6, 12, Blocks.FENCE.getBlockData(), Blocks.FENCE.getBlockData(), false);
                this.a(world, structureboundingbox, 10, 6, 2, 10, 6, 10, Blocks.FENCE.getBlockData(), Blocks.FENCE.getBlockData(), false);
                this.a(world, structureboundingbox, 4, 6, 2, 9, 6, 2, Blocks.FENCE.getBlockData(), Blocks.FENCE.getBlockData(), false);
                this.a(world, structureboundingbox, 4, 6, 12, 8, 6, 12, Blocks.FENCE.getBlockData(), Blocks.FENCE.getBlockData(), false);
                this.a(world, Blocks.FENCE.getBlockData(), 9, 6, 11, structureboundingbox);
                this.a(world, Blocks.FENCE.getBlockData(), 8, 6, 11, structureboundingbox);
                this.a(world, Blocks.FENCE.getBlockData(), 9, 6, 10, structureboundingbox);
                i = this.a(Blocks.LADDER, 3);
                this.a(world, Blocks.LADDER.fromLegacyData(i), 10, 1, 13, structureboundingbox);
                this.a(world, Blocks.LADDER.fromLegacyData(i), 10, 2, 13, structureboundingbox);
                this.a(world, Blocks.LADDER.fromLegacyData(i), 10, 3, 13, structureboundingbox);
                this.a(world, Blocks.LADDER.fromLegacyData(i), 10, 4, 13, structureboundingbox);
                this.a(world, Blocks.LADDER.fromLegacyData(i), 10, 5, 13, structureboundingbox);
                this.a(world, Blocks.LADDER.fromLegacyData(i), 10, 6, 13, structureboundingbox);
                this.a(world, Blocks.LADDER.fromLegacyData(i), 10, 7, 13, structureboundingbox);
                byte b1 = 7;
                byte b2 = 7;

                this.a(world, Blocks.FENCE.getBlockData(), b1 - 1, 9, b2, structureboundingbox);
                this.a(world, Blocks.FENCE.getBlockData(), b1, 9, b2, structureboundingbox);
                this.a(world, Blocks.FENCE.getBlockData(), b1 - 1, 8, b2, structureboundingbox);
                this.a(world, Blocks.FENCE.getBlockData(), b1, 8, b2, structureboundingbox);
                this.a(world, Blocks.FENCE.getBlockData(), b1 - 1, 7, b2, structureboundingbox);
                this.a(world, Blocks.FENCE.getBlockData(), b1, 7, b2, structureboundingbox);
                this.a(world, Blocks.FENCE.getBlockData(), b1 - 2, 7, b2, structureboundingbox);
                this.a(world, Blocks.FENCE.getBlockData(), b1 + 1, 7, b2, structureboundingbox);
                this.a(world, Blocks.FENCE.getBlockData(), b1 - 1, 7, b2 - 1, structureboundingbox);
                this.a(world, Blocks.FENCE.getBlockData(), b1 - 1, 7, b2 + 1, structureboundingbox);
                this.a(world, Blocks.FENCE.getBlockData(), b1, 7, b2 - 1, structureboundingbox);
                this.a(world, Blocks.FENCE.getBlockData(), b1, 7, b2 + 1, structureboundingbox);
                this.a(world, Blocks.TORCH.getBlockData(), b1 - 2, 8, b2, structureboundingbox);
                this.a(world, Blocks.TORCH.getBlockData(), b1 + 1, 8, b2, structureboundingbox);
                this.a(world, Blocks.TORCH.getBlockData(), b1 - 1, 8, b2 - 1, structureboundingbox);
                this.a(world, Blocks.TORCH.getBlockData(), b1 - 1, 8, b2 + 1, structureboundingbox);
                this.a(world, Blocks.TORCH.getBlockData(), b1, 8, b2 - 1, structureboundingbox);
                this.a(world, Blocks.TORCH.getBlockData(), b1, 8, b2 + 1, structureboundingbox);
            }

            this.a(world, structureboundingbox, random, 3, 3, 5, StructurePieceTreasure.a(WorldGenStrongholdLibrary.a, new StructurePieceTreasure[] { Items.ENCHANTED_BOOK.a(random, 1, 5, 2)}), 1 + random.nextInt(4));
            if (this.b) {
                this.a(world, Blocks.AIR.getBlockData(), 12, 9, 1, structureboundingbox);
                this.a(world, structureboundingbox, random, 12, 8, 1, StructurePieceTreasure.a(WorldGenStrongholdLibrary.a, new StructurePieceTreasure[] { Items.ENCHANTED_BOOK.a(random, 1, 5, 2)}), 1 + random.nextInt(4));
            }

            return true;
        }
    }
}
