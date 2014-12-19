package net.minecraft.server;

public abstract class WorldGenMonumentPiece extends StructurePiece {

    protected static final IBlockData a = Blocks.PRISMARINE.fromLegacyData(BlockPrismarine.b);
    protected static final IBlockData b = Blocks.PRISMARINE.fromLegacyData(BlockPrismarine.M);
    protected static final IBlockData c = Blocks.PRISMARINE.fromLegacyData(BlockPrismarine.N);
    protected static final IBlockData d = WorldGenMonumentPiece.b;
    protected static final IBlockData e = Blocks.SEA_LANTERN.getBlockData();
    protected static final IBlockData f = Blocks.WATER.getBlockData();
    protected static final int g = a(2, 0, 0);
    protected static final int h = a(2, 2, 0);
    protected static final int i = a(0, 1, 0);
    protected static final int j = a(4, 1, 0);
    protected WorldGenMonumentStateTracker k;

    protected static final int a(int i, int j, int k) {
        return j * 25 + k * 5 + i;
    }

    public WorldGenMonumentPiece() {
        super(0);
    }

    public WorldGenMonumentPiece(int i) {
        super(i);
    }

    public WorldGenMonumentPiece(EnumDirection enumdirection, StructureBoundingBox structureboundingbox) {
        super(1);
        this.m = enumdirection;
        this.l = structureboundingbox;
    }

    protected WorldGenMonumentPiece(int i, EnumDirection enumdirection, WorldGenMonumentStateTracker worldgenmonumentstatetracker, int j, int k, int l) {
        super(i);
        this.m = enumdirection;
        this.k = worldgenmonumentstatetracker;
        int i1 = worldgenmonumentstatetracker.a;
        int j1 = i1 % 5;
        int k1 = i1 / 5 % 5;
        int l1 = i1 / 25;

        if (enumdirection != EnumDirection.NORTH && enumdirection != EnumDirection.SOUTH) {
            this.l = new StructureBoundingBox(0, 0, 0, l * 8 - 1, k * 4 - 1, j * 8 - 1);
        } else {
            this.l = new StructureBoundingBox(0, 0, 0, j * 8 - 1, k * 4 - 1, l * 8 - 1);
        }

        switch (SwitchHelperDirection5.a[enumdirection.ordinal()]) {
        case 1:
            this.l.a(j1 * 8, l1 * 4, -(k1 + l) * 8 + 1);
            break;

        case 2:
            this.l.a(j1 * 8, l1 * 4, k1 * 8);
            break;

        case 3:
            this.l.a(-(k1 + l) * 8 + 1, l1 * 4, j1 * 8);
            break;

        default:
            this.l.a(k1 * 8, l1 * 4, j1 * 8);
        }

    }

    protected void a(NBTTagCompound nbttagcompound) {}

    protected void b(NBTTagCompound nbttagcompound) {}

    protected void a(World world, StructureBoundingBox structureboundingbox, int i, int j, boolean flag) {
        if (flag) {
            this.a(world, structureboundingbox, i + 0, 0, j + 0, i + 2, 0, j + 8 - 1, WorldGenMonumentPiece.a, WorldGenMonumentPiece.a, false);
            this.a(world, structureboundingbox, i + 5, 0, j + 0, i + 8 - 1, 0, j + 8 - 1, WorldGenMonumentPiece.a, WorldGenMonumentPiece.a, false);
            this.a(world, structureboundingbox, i + 3, 0, j + 0, i + 4, 0, j + 2, WorldGenMonumentPiece.a, WorldGenMonumentPiece.a, false);
            this.a(world, structureboundingbox, i + 3, 0, j + 5, i + 4, 0, j + 8 - 1, WorldGenMonumentPiece.a, WorldGenMonumentPiece.a, false);
            this.a(world, structureboundingbox, i + 3, 0, j + 2, i + 4, 0, j + 2, WorldGenMonumentPiece.b, WorldGenMonumentPiece.b, false);
            this.a(world, structureboundingbox, i + 3, 0, j + 5, i + 4, 0, j + 5, WorldGenMonumentPiece.b, WorldGenMonumentPiece.b, false);
            this.a(world, structureboundingbox, i + 2, 0, j + 3, i + 2, 0, j + 4, WorldGenMonumentPiece.b, WorldGenMonumentPiece.b, false);
            this.a(world, structureboundingbox, i + 5, 0, j + 3, i + 5, 0, j + 4, WorldGenMonumentPiece.b, WorldGenMonumentPiece.b, false);
        } else {
            this.a(world, structureboundingbox, i + 0, 0, j + 0, i + 8 - 1, 0, j + 8 - 1, WorldGenMonumentPiece.a, WorldGenMonumentPiece.a, false);
        }

    }

    protected void a(World world, StructureBoundingBox structureboundingbox, int i, int j, int k, int l, int i1, int j1, IBlockData iblockdata) {
        for (int k1 = j; k1 <= i1; ++k1) {
            for (int l1 = i; l1 <= l; ++l1) {
                for (int i2 = k; i2 <= j1; ++i2) {
                    if (this.a(world, l1, k1, i2, structureboundingbox) == WorldGenMonumentPiece.f) {
                        this.a(world, iblockdata, l1, k1, i2, structureboundingbox);
                    }
                }
            }
        }

    }

    protected boolean a(StructureBoundingBox structureboundingbox, int i, int j, int k, int l) {
        int i1 = this.a(i, j);
        int j1 = this.b(i, j);
        int k1 = this.a(k, l);
        int l1 = this.b(k, l);

        return structureboundingbox.a(Math.min(i1, k1), Math.min(j1, l1), Math.max(i1, k1), Math.max(j1, l1));
    }

    protected boolean a(World world, StructureBoundingBox structureboundingbox, int i, int j, int k) {
        int l = this.a(i, k);
        int i1 = this.d(j);
        int j1 = this.b(i, k);

        if (structureboundingbox.b((BaseBlockPosition) (new BlockPosition(l, i1, j1)))) {
            EntityGuardian entityguardian = new EntityGuardian(world);

            entityguardian.a(true);
            entityguardian.heal(entityguardian.getMaxHealth());
            entityguardian.setPositionRotation((double) l + 0.5D, (double) i1, (double) j1 + 0.5D, 0.0F, 0.0F);
            entityguardian.prepare(world.E(new BlockPosition(entityguardian)), (GroupDataEntity) null);
            world.addEntity(entityguardian);
            return true;
        } else {
            return false;
        }
    }
}
