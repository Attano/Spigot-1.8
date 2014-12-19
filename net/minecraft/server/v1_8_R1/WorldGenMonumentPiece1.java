package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class WorldGenMonumentPiece1 extends WorldGenMonumentPiece {

    private WorldGenMonumentStateTracker o;
    private WorldGenMonumentStateTracker p;
    private List q = Lists.newArrayList();

    public WorldGenMonumentPiece1() {}

    public WorldGenMonumentPiece1(Random random, int i, int j, EnumDirection enumdirection) {
        super(0);
        this.m = enumdirection;
        switch (SwitchHelperDirection5.a[this.m.ordinal()]) {
        case 1:
        case 2:
            this.l = new StructureBoundingBox(i, 39, j, i + 58 - 1, 61, j + 58 - 1);
            break;

        default:
            this.l = new StructureBoundingBox(i, 39, j, i + 58 - 1, 61, j + 58 - 1);
        }

        List list = this.a(random);

        this.o.d = true;
        this.q.add(new WorldGenMonumentPieceEntry(this.m, this.o));
        this.q.add(new WorldGenMonumentPiece2(this.m, this.p, random));
        ArrayList arraylist = Lists.newArrayList();

        arraylist.add(new WorldGenMonumentPieceSelector6((SwitchHelperDirection5) null));
        arraylist.add(new WorldGenMonumentPieceSelector4((SwitchHelperDirection5) null));
        arraylist.add(new WorldGenMonumentPieceSelector3((SwitchHelperDirection5) null));
        arraylist.add(new WorldGenMonumentPieceSelector7((SwitchHelperDirection5) null));
        arraylist.add(new WorldGenMonumentPieceSelector5((SwitchHelperDirection5) null));
        arraylist.add(new WorldGenMonumentPieceSelector1((SwitchHelperDirection5) null));
        arraylist.add(new WorldGenMonumentPieceSelector2((SwitchHelperDirection5) null));
        Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            WorldGenMonumentStateTracker worldgenmonumentstatetracker = (WorldGenMonumentStateTracker) iterator.next();

            if (!worldgenmonumentstatetracker.d && !worldgenmonumentstatetracker.b()) {
                Iterator iterator1 = arraylist.iterator();

                while (iterator1.hasNext()) {
                    IWorldGenMonumentPieceSelector iworldgenmonumentpieceselector = (IWorldGenMonumentPieceSelector) iterator1.next();

                    if (iworldgenmonumentpieceselector.a(worldgenmonumentstatetracker)) {
                        this.q.add(iworldgenmonumentpieceselector.a(this.m, worldgenmonumentstatetracker, random));
                        break;
                    }
                }
            }
        }

        int k = this.l.b;
        int l = this.a(9, 22);
        int i1 = this.b(9, 22);
        Iterator iterator2 = this.q.iterator();

        while (iterator2.hasNext()) {
            WorldGenMonumentPiece worldgenmonumentpiece = (WorldGenMonumentPiece) iterator2.next();

            worldgenmonumentpiece.c().a(l, k, i1);
        }

        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(this.a(1, 1), this.d(1), this.b(1, 1), this.a(23, 21), this.d(8), this.b(23, 21));
        StructureBoundingBox structureboundingbox1 = StructureBoundingBox.a(this.a(34, 1), this.d(1), this.b(34, 1), this.a(56, 21), this.d(8), this.b(56, 21));
        StructureBoundingBox structureboundingbox2 = StructureBoundingBox.a(this.a(22, 22), this.d(13), this.b(22, 22), this.a(35, 35), this.d(17), this.b(35, 35));
        int j1 = random.nextInt();

        this.q.add(new WorldGenMonumentPiece8(this.m, structureboundingbox, j1++));
        this.q.add(new WorldGenMonumentPiece8(this.m, structureboundingbox1, j1++));
        this.q.add(new WorldGenMonumentPiecePenthouse(this.m, structureboundingbox2));
    }

    private List a(Random random) {
        WorldGenMonumentStateTracker[] aworldgenmonumentstatetracker = new WorldGenMonumentStateTracker[75];

        int i;
        int j;
        byte b0;
        int k;

        for (i = 0; i < 5; ++i) {
            for (j = 0; j < 4; ++j) {
                b0 = 0;
                k = a(i, b0, j);
                aworldgenmonumentstatetracker[k] = new WorldGenMonumentStateTracker(k);
            }
        }

        for (i = 0; i < 5; ++i) {
            for (j = 0; j < 4; ++j) {
                b0 = 1;
                k = a(i, b0, j);
                aworldgenmonumentstatetracker[k] = new WorldGenMonumentStateTracker(k);
            }
        }

        for (i = 1; i < 4; ++i) {
            for (j = 0; j < 2; ++j) {
                b0 = 2;
                k = a(i, b0, j);
                aworldgenmonumentstatetracker[k] = new WorldGenMonumentStateTracker(k);
            }
        }

        this.o = aworldgenmonumentstatetracker[WorldGenMonumentPiece1.g];

        int l;
        int i1;
        int j1;
        int k1;
        int l1;

        for (i = 0; i < 5; ++i) {
            for (j = 0; j < 5; ++j) {
                for (int i2 = 0; i2 < 3; ++i2) {
                    k = a(i, i2, j);
                    if (aworldgenmonumentstatetracker[k] != null) {
                        EnumDirection[] aenumdirection = EnumDirection.values();

                        l = aenumdirection.length;

                        for (i1 = 0; i1 < l; ++i1) {
                            EnumDirection enumdirection = aenumdirection[i1];

                            j1 = i + enumdirection.getAdjacentX();
                            k1 = i2 + enumdirection.getAdjacentY();
                            l1 = j + enumdirection.getAdjacentZ();
                            if (j1 >= 0 && j1 < 5 && l1 >= 0 && l1 < 5 && k1 >= 0 && k1 < 3) {
                                int j2 = a(j1, k1, l1);

                                if (aworldgenmonumentstatetracker[j2] != null) {
                                    if (l1 != j) {
                                        aworldgenmonumentstatetracker[k].a(enumdirection.opposite(), aworldgenmonumentstatetracker[j2]);
                                    } else {
                                        aworldgenmonumentstatetracker[k].a(enumdirection, aworldgenmonumentstatetracker[j2]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        WorldGenMonumentStateTracker worldgenmonumentstatetracker;
        WorldGenMonumentStateTracker worldgenmonumentstatetracker1;
        WorldGenMonumentStateTracker worldgenmonumentstatetracker2;

        aworldgenmonumentstatetracker[WorldGenMonumentPiece1.h].a(EnumDirection.UP, worldgenmonumentstatetracker = new WorldGenMonumentStateTracker(1003));
        aworldgenmonumentstatetracker[WorldGenMonumentPiece1.i].a(EnumDirection.SOUTH, worldgenmonumentstatetracker1 = new WorldGenMonumentStateTracker(1001));
        aworldgenmonumentstatetracker[WorldGenMonumentPiece1.j].a(EnumDirection.SOUTH, worldgenmonumentstatetracker2 = new WorldGenMonumentStateTracker(1002));
        worldgenmonumentstatetracker.d = true;
        worldgenmonumentstatetracker1.d = true;
        worldgenmonumentstatetracker2.d = true;
        this.o.e = true;
        this.p = aworldgenmonumentstatetracker[a(random.nextInt(4), 0, 2)];
        this.p.d = true;
        this.p.b[EnumDirection.EAST.a()].d = true;
        this.p.b[EnumDirection.NORTH.a()].d = true;
        this.p.b[EnumDirection.EAST.a()].b[EnumDirection.NORTH.a()].d = true;
        this.p.b[EnumDirection.UP.a()].d = true;
        this.p.b[EnumDirection.EAST.a()].b[EnumDirection.UP.a()].d = true;
        this.p.b[EnumDirection.NORTH.a()].b[EnumDirection.UP.a()].d = true;
        this.p.b[EnumDirection.EAST.a()].b[EnumDirection.NORTH.a()].b[EnumDirection.UP.a()].d = true;
        ArrayList arraylist = Lists.newArrayList();
        WorldGenMonumentStateTracker[] aworldgenmonumentstatetracker1 = aworldgenmonumentstatetracker;

        l = aworldgenmonumentstatetracker.length;

        for (i1 = 0; i1 < l; ++i1) {
            WorldGenMonumentStateTracker worldgenmonumentstatetracker3 = aworldgenmonumentstatetracker1[i1];

            if (worldgenmonumentstatetracker3 != null) {
                worldgenmonumentstatetracker3.a();
                arraylist.add(worldgenmonumentstatetracker3);
            }
        }

        worldgenmonumentstatetracker.a();
        Collections.shuffle(arraylist, random);
        int k2 = 1;
        Iterator iterator = arraylist.iterator();

        while (iterator.hasNext()) {
            WorldGenMonumentStateTracker worldgenmonumentstatetracker4 = (WorldGenMonumentStateTracker) iterator.next();
            int l2 = 0;

            j1 = 0;

            while (l2 < 2 && j1 < 5) {
                ++j1;
                k1 = random.nextInt(6);
                if (worldgenmonumentstatetracker4.c[k1]) {
                    l1 = EnumDirection.fromType1(k1).opposite().a();
                    worldgenmonumentstatetracker4.c[k1] = false;
                    worldgenmonumentstatetracker4.b[k1].c[l1] = false;
                    if (worldgenmonumentstatetracker4.a(k2++) && worldgenmonumentstatetracker4.b[k1].a(k2++)) {
                        ++l2;
                    } else {
                        worldgenmonumentstatetracker4.c[k1] = true;
                        worldgenmonumentstatetracker4.b[k1].c[l1] = true;
                    }
                }
            }
        }

        arraylist.add(worldgenmonumentstatetracker);
        arraylist.add(worldgenmonumentstatetracker1);
        arraylist.add(worldgenmonumentstatetracker2);
        return arraylist;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        this.a(false, 0, world, random, structureboundingbox);
        this.a(true, 33, world, random, structureboundingbox);
        this.b(world, random, structureboundingbox);
        this.c(world, random, structureboundingbox);
        this.d(world, random, structureboundingbox);
        this.e(world, random, structureboundingbox);
        this.f(world, random, structureboundingbox);
        this.g(world, random, structureboundingbox);

        int i;

        for (i = 0; i < 7; ++i) {
            int j = 0;

            while (j < 7) {
                if (j == 0 && i == 3) {
                    j = 6;
                }

                int k = i * 9;
                int l = j * 9;

                for (int i1 = 0; i1 < 4; ++i1) {
                    for (int j1 = 0; j1 < 4; ++j1) {
                        this.a(world, WorldGenMonumentPiece1.b, k + i1, 0, l + j1, structureboundingbox);
                        this.b(world, WorldGenMonumentPiece1.b, k + i1, -1, l + j1, structureboundingbox);
                    }
                }

                if (i != 0 && i != 6) {
                    j += 6;
                } else {
                    ++j;
                }
            }
        }

        for (i = 0; i < 5; ++i) {
            this.a(world, structureboundingbox, -1 - i, 0 + i * 2, -1 - i, -1 - i, 23, 58 + i, WorldGenMonumentPiece1.f, WorldGenMonumentPiece1.f, false);
            this.a(world, structureboundingbox, 58 + i, 0 + i * 2, -1 - i, 58 + i, 23, 58 + i, WorldGenMonumentPiece1.f, WorldGenMonumentPiece1.f, false);
            this.a(world, structureboundingbox, 0 - i, 0 + i * 2, -1 - i, 57 + i, 23, -1 - i, WorldGenMonumentPiece1.f, WorldGenMonumentPiece1.f, false);
            this.a(world, structureboundingbox, 0 - i, 0 + i * 2, 58 + i, 57 + i, 23, 58 + i, WorldGenMonumentPiece1.f, WorldGenMonumentPiece1.f, false);
        }

        Iterator iterator = this.q.iterator();

        while (iterator.hasNext()) {
            WorldGenMonumentPiece worldgenmonumentpiece = (WorldGenMonumentPiece) iterator.next();

            if (worldgenmonumentpiece.c().a(structureboundingbox)) {
                worldgenmonumentpiece.a(world, random, structureboundingbox);
            }
        }

        return true;
    }

    private void a(boolean flag, int i, World world, Random random, StructureBoundingBox structureboundingbox) {
        boolean flag1 = true;

        if (this.a(structureboundingbox, i, 0, i + 23, 20)) {
            this.a(world, structureboundingbox, i + 0, 0, 0, i + 24, 0, 20, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, i + 0, 1, 0, i + 24, 10, 20, WorldGenMonumentPiece1.f, WorldGenMonumentPiece1.f, false);

            int j;

            for (j = 0; j < 4; ++j) {
                this.a(world, structureboundingbox, i + j, j + 1, j, i + j, j + 1, 20, WorldGenMonumentPiece1.b, WorldGenMonumentPiece1.b, false);
                this.a(world, structureboundingbox, i + j + 7, j + 5, j + 7, i + j + 7, j + 5, 20, WorldGenMonumentPiece1.b, WorldGenMonumentPiece1.b, false);
                this.a(world, structureboundingbox, i + 17 - j, j + 5, j + 7, i + 17 - j, j + 5, 20, WorldGenMonumentPiece1.b, WorldGenMonumentPiece1.b, false);
                this.a(world, structureboundingbox, i + 24 - j, j + 1, j, i + 24 - j, j + 1, 20, WorldGenMonumentPiece1.b, WorldGenMonumentPiece1.b, false);
                this.a(world, structureboundingbox, i + j + 1, j + 1, j, i + 23 - j, j + 1, j, WorldGenMonumentPiece1.b, WorldGenMonumentPiece1.b, false);
                this.a(world, structureboundingbox, i + j + 8, j + 5, j + 7, i + 16 - j, j + 5, j + 7, WorldGenMonumentPiece1.b, WorldGenMonumentPiece1.b, false);
            }

            this.a(world, structureboundingbox, i + 4, 4, 4, i + 6, 4, 20, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, i + 7, 4, 4, i + 17, 4, 6, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, i + 18, 4, 4, i + 20, 4, 20, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, i + 11, 8, 11, i + 13, 8, 20, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, WorldGenMonumentPiece1.d, i + 12, 9, 12, structureboundingbox);
            this.a(world, WorldGenMonumentPiece1.d, i + 12, 9, 15, structureboundingbox);
            this.a(world, WorldGenMonumentPiece1.d, i + 12, 9, 18, structureboundingbox);
            j = flag ? i + 19 : i + 5;
            int k = flag ? i + 5 : i + 19;

            int l;

            for (l = 20; l >= 5; l -= 3) {
                this.a(world, WorldGenMonumentPiece1.d, j, 5, l, structureboundingbox);
            }

            for (l = 19; l >= 7; l -= 3) {
                this.a(world, WorldGenMonumentPiece1.d, k, 5, l, structureboundingbox);
            }

            for (l = 0; l < 4; ++l) {
                int i1 = flag ? i + (24 - (17 - l * 3)) : i + 17 - l * 3;

                this.a(world, WorldGenMonumentPiece1.d, i1, 5, 5, structureboundingbox);
            }

            this.a(world, WorldGenMonumentPiece1.d, k, 5, 5, structureboundingbox);
            this.a(world, structureboundingbox, i + 11, 1, 12, i + 13, 7, 12, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, i + 12, 1, 11, i + 12, 7, 13, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
        }

    }

    private void b(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a(structureboundingbox, 22, 5, 35, 17)) {
            this.a(world, structureboundingbox, 25, 0, 0, 32, 8, 20, WorldGenMonumentPiece1.f, WorldGenMonumentPiece1.f, false);

            for (int i = 0; i < 4; ++i) {
                this.a(world, structureboundingbox, 24, 2, 5 + i * 4, 24, 4, 5 + i * 4, WorldGenMonumentPiece1.b, WorldGenMonumentPiece1.b, false);
                this.a(world, structureboundingbox, 22, 4, 5 + i * 4, 23, 4, 5 + i * 4, WorldGenMonumentPiece1.b, WorldGenMonumentPiece1.b, false);
                this.a(world, WorldGenMonumentPiece1.b, 25, 5, 5 + i * 4, structureboundingbox);
                this.a(world, WorldGenMonumentPiece1.b, 26, 6, 5 + i * 4, structureboundingbox);
                this.a(world, WorldGenMonumentPiece1.e, 26, 5, 5 + i * 4, structureboundingbox);
                this.a(world, structureboundingbox, 33, 2, 5 + i * 4, 33, 4, 5 + i * 4, WorldGenMonumentPiece1.b, WorldGenMonumentPiece1.b, false);
                this.a(world, structureboundingbox, 34, 4, 5 + i * 4, 35, 4, 5 + i * 4, WorldGenMonumentPiece1.b, WorldGenMonumentPiece1.b, false);
                this.a(world, WorldGenMonumentPiece1.b, 32, 5, 5 + i * 4, structureboundingbox);
                this.a(world, WorldGenMonumentPiece1.b, 31, 6, 5 + i * 4, structureboundingbox);
                this.a(world, WorldGenMonumentPiece1.e, 31, 5, 5 + i * 4, structureboundingbox);
                this.a(world, structureboundingbox, 27, 6, 5 + i * 4, 30, 6, 5 + i * 4, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            }
        }

    }

    private void c(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a(structureboundingbox, 15, 20, 42, 21)) {
            this.a(world, structureboundingbox, 15, 0, 21, 42, 0, 21, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, 26, 1, 21, 31, 3, 21, WorldGenMonumentPiece1.f, WorldGenMonumentPiece1.f, false);
            this.a(world, structureboundingbox, 21, 12, 21, 36, 12, 21, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, 17, 11, 21, 40, 11, 21, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, 16, 10, 21, 41, 10, 21, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, 15, 7, 21, 42, 9, 21, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, 16, 6, 21, 41, 6, 21, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, 17, 5, 21, 40, 5, 21, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, 21, 4, 21, 36, 4, 21, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, 22, 3, 21, 26, 3, 21, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, 31, 3, 21, 35, 3, 21, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, 23, 2, 21, 25, 2, 21, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, 32, 2, 21, 34, 2, 21, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, 28, 4, 20, 29, 4, 21, WorldGenMonumentPiece1.b, WorldGenMonumentPiece1.b, false);
            this.a(world, WorldGenMonumentPiece1.b, 27, 3, 21, structureboundingbox);
            this.a(world, WorldGenMonumentPiece1.b, 30, 3, 21, structureboundingbox);
            this.a(world, WorldGenMonumentPiece1.b, 26, 2, 21, structureboundingbox);
            this.a(world, WorldGenMonumentPiece1.b, 31, 2, 21, structureboundingbox);
            this.a(world, WorldGenMonumentPiece1.b, 25, 1, 21, structureboundingbox);
            this.a(world, WorldGenMonumentPiece1.b, 32, 1, 21, structureboundingbox);

            int i;

            for (i = 0; i < 7; ++i) {
                this.a(world, WorldGenMonumentPiece1.c, 28 - i, 6 + i, 21, structureboundingbox);
                this.a(world, WorldGenMonumentPiece1.c, 29 + i, 6 + i, 21, structureboundingbox);
            }

            for (i = 0; i < 4; ++i) {
                this.a(world, WorldGenMonumentPiece1.c, 28 - i, 9 + i, 21, structureboundingbox);
                this.a(world, WorldGenMonumentPiece1.c, 29 + i, 9 + i, 21, structureboundingbox);
            }

            this.a(world, WorldGenMonumentPiece1.c, 28, 12, 21, structureboundingbox);
            this.a(world, WorldGenMonumentPiece1.c, 29, 12, 21, structureboundingbox);

            for (i = 0; i < 3; ++i) {
                this.a(world, WorldGenMonumentPiece1.c, 22 - i * 2, 8, 21, structureboundingbox);
                this.a(world, WorldGenMonumentPiece1.c, 22 - i * 2, 9, 21, structureboundingbox);
                this.a(world, WorldGenMonumentPiece1.c, 35 + i * 2, 8, 21, structureboundingbox);
                this.a(world, WorldGenMonumentPiece1.c, 35 + i * 2, 9, 21, structureboundingbox);
            }

            this.a(world, structureboundingbox, 15, 13, 21, 42, 15, 21, WorldGenMonumentPiece1.f, WorldGenMonumentPiece1.f, false);
            this.a(world, structureboundingbox, 15, 1, 21, 15, 6, 21, WorldGenMonumentPiece1.f, WorldGenMonumentPiece1.f, false);
            this.a(world, structureboundingbox, 16, 1, 21, 16, 5, 21, WorldGenMonumentPiece1.f, WorldGenMonumentPiece1.f, false);
            this.a(world, structureboundingbox, 17, 1, 21, 20, 4, 21, WorldGenMonumentPiece1.f, WorldGenMonumentPiece1.f, false);
            this.a(world, structureboundingbox, 21, 1, 21, 21, 3, 21, WorldGenMonumentPiece1.f, WorldGenMonumentPiece1.f, false);
            this.a(world, structureboundingbox, 22, 1, 21, 22, 2, 21, WorldGenMonumentPiece1.f, WorldGenMonumentPiece1.f, false);
            this.a(world, structureboundingbox, 23, 1, 21, 24, 1, 21, WorldGenMonumentPiece1.f, WorldGenMonumentPiece1.f, false);
            this.a(world, structureboundingbox, 42, 1, 21, 42, 6, 21, WorldGenMonumentPiece1.f, WorldGenMonumentPiece1.f, false);
            this.a(world, structureboundingbox, 41, 1, 21, 41, 5, 21, WorldGenMonumentPiece1.f, WorldGenMonumentPiece1.f, false);
            this.a(world, structureboundingbox, 37, 1, 21, 40, 4, 21, WorldGenMonumentPiece1.f, WorldGenMonumentPiece1.f, false);
            this.a(world, structureboundingbox, 36, 1, 21, 36, 3, 21, WorldGenMonumentPiece1.f, WorldGenMonumentPiece1.f, false);
            this.a(world, structureboundingbox, 35, 1, 21, 35, 2, 21, WorldGenMonumentPiece1.f, WorldGenMonumentPiece1.f, false);
            this.a(world, structureboundingbox, 33, 1, 21, 34, 1, 21, WorldGenMonumentPiece1.f, WorldGenMonumentPiece1.f, false);
        }

    }

    private void d(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a(structureboundingbox, 21, 21, 36, 36)) {
            this.a(world, structureboundingbox, 21, 0, 22, 36, 0, 36, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, 21, 1, 22, 36, 23, 36, WorldGenMonumentPiece1.f, WorldGenMonumentPiece1.f, false);

            for (int i = 0; i < 4; ++i) {
                this.a(world, structureboundingbox, 21 + i, 13 + i, 21 + i, 36 - i, 13 + i, 21 + i, WorldGenMonumentPiece1.b, WorldGenMonumentPiece1.b, false);
                this.a(world, structureboundingbox, 21 + i, 13 + i, 36 - i, 36 - i, 13 + i, 36 - i, WorldGenMonumentPiece1.b, WorldGenMonumentPiece1.b, false);
                this.a(world, structureboundingbox, 21 + i, 13 + i, 22 + i, 21 + i, 13 + i, 35 - i, WorldGenMonumentPiece1.b, WorldGenMonumentPiece1.b, false);
                this.a(world, structureboundingbox, 36 - i, 13 + i, 22 + i, 36 - i, 13 + i, 35 - i, WorldGenMonumentPiece1.b, WorldGenMonumentPiece1.b, false);
            }

            this.a(world, structureboundingbox, 25, 16, 25, 32, 16, 32, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, 25, 17, 25, 25, 19, 25, WorldGenMonumentPiece1.b, WorldGenMonumentPiece1.b, false);
            this.a(world, structureboundingbox, 32, 17, 25, 32, 19, 25, WorldGenMonumentPiece1.b, WorldGenMonumentPiece1.b, false);
            this.a(world, structureboundingbox, 25, 17, 32, 25, 19, 32, WorldGenMonumentPiece1.b, WorldGenMonumentPiece1.b, false);
            this.a(world, structureboundingbox, 32, 17, 32, 32, 19, 32, WorldGenMonumentPiece1.b, WorldGenMonumentPiece1.b, false);
            this.a(world, WorldGenMonumentPiece1.b, 26, 20, 26, structureboundingbox);
            this.a(world, WorldGenMonumentPiece1.b, 27, 21, 27, structureboundingbox);
            this.a(world, WorldGenMonumentPiece1.e, 27, 20, 27, structureboundingbox);
            this.a(world, WorldGenMonumentPiece1.b, 26, 20, 31, structureboundingbox);
            this.a(world, WorldGenMonumentPiece1.b, 27, 21, 30, structureboundingbox);
            this.a(world, WorldGenMonumentPiece1.e, 27, 20, 30, structureboundingbox);
            this.a(world, WorldGenMonumentPiece1.b, 31, 20, 31, structureboundingbox);
            this.a(world, WorldGenMonumentPiece1.b, 30, 21, 30, structureboundingbox);
            this.a(world, WorldGenMonumentPiece1.e, 30, 20, 30, structureboundingbox);
            this.a(world, WorldGenMonumentPiece1.b, 31, 20, 26, structureboundingbox);
            this.a(world, WorldGenMonumentPiece1.b, 30, 21, 27, structureboundingbox);
            this.a(world, WorldGenMonumentPiece1.e, 30, 20, 27, structureboundingbox);
            this.a(world, structureboundingbox, 28, 21, 27, 29, 21, 27, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, 27, 21, 28, 27, 21, 29, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, 28, 21, 30, 29, 21, 30, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, 30, 21, 28, 30, 21, 29, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
        }

    }

    private void e(World world, Random random, StructureBoundingBox structureboundingbox) {
        int i;

        if (this.a(structureboundingbox, 0, 21, 6, 58)) {
            this.a(world, structureboundingbox, 0, 0, 21, 6, 0, 57, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, 0, 1, 21, 6, 7, 57, WorldGenMonumentPiece1.f, WorldGenMonumentPiece1.f, false);
            this.a(world, structureboundingbox, 4, 4, 21, 6, 4, 53, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);

            for (i = 0; i < 4; ++i) {
                this.a(world, structureboundingbox, i, i + 1, 21, i, i + 1, 57 - i, WorldGenMonumentPiece1.b, WorldGenMonumentPiece1.b, false);
            }

            for (i = 23; i < 53; i += 3) {
                this.a(world, WorldGenMonumentPiece1.d, 5, 5, i, structureboundingbox);
            }

            this.a(world, WorldGenMonumentPiece1.d, 5, 5, 52, structureboundingbox);

            for (i = 0; i < 4; ++i) {
                this.a(world, structureboundingbox, i, i + 1, 21, i, i + 1, 57 - i, WorldGenMonumentPiece1.b, WorldGenMonumentPiece1.b, false);
            }

            this.a(world, structureboundingbox, 4, 1, 52, 6, 3, 52, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, 5, 1, 51, 5, 3, 53, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
        }

        if (this.a(structureboundingbox, 51, 21, 58, 58)) {
            this.a(world, structureboundingbox, 51, 0, 21, 57, 0, 57, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, 51, 1, 21, 57, 7, 57, WorldGenMonumentPiece1.f, WorldGenMonumentPiece1.f, false);
            this.a(world, structureboundingbox, 51, 4, 21, 53, 4, 53, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);

            for (i = 0; i < 4; ++i) {
                this.a(world, structureboundingbox, 57 - i, i + 1, 21, 57 - i, i + 1, 57 - i, WorldGenMonumentPiece1.b, WorldGenMonumentPiece1.b, false);
            }

            for (i = 23; i < 53; i += 3) {
                this.a(world, WorldGenMonumentPiece1.d, 52, 5, i, structureboundingbox);
            }

            this.a(world, WorldGenMonumentPiece1.d, 52, 5, 52, structureboundingbox);
            this.a(world, structureboundingbox, 51, 1, 52, 53, 3, 52, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, 52, 1, 51, 52, 3, 53, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
        }

        if (this.a(structureboundingbox, 0, 51, 57, 57)) {
            this.a(world, structureboundingbox, 7, 0, 51, 50, 0, 57, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, 7, 1, 51, 50, 10, 57, WorldGenMonumentPiece1.f, WorldGenMonumentPiece1.f, false);

            for (i = 0; i < 4; ++i) {
                this.a(world, structureboundingbox, i + 1, i + 1, 57 - i, 56 - i, i + 1, 57 - i, WorldGenMonumentPiece1.b, WorldGenMonumentPiece1.b, false);
            }
        }

    }

    private void f(World world, Random random, StructureBoundingBox structureboundingbox) {
        int i;

        if (this.a(structureboundingbox, 7, 21, 13, 50)) {
            this.a(world, structureboundingbox, 7, 0, 21, 13, 0, 50, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, 7, 1, 21, 13, 10, 50, WorldGenMonumentPiece1.f, WorldGenMonumentPiece1.f, false);
            this.a(world, structureboundingbox, 11, 8, 21, 13, 8, 53, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);

            for (i = 0; i < 4; ++i) {
                this.a(world, structureboundingbox, i + 7, i + 5, 21, i + 7, i + 5, 54, WorldGenMonumentPiece1.b, WorldGenMonumentPiece1.b, false);
            }

            for (i = 21; i <= 45; i += 3) {
                this.a(world, WorldGenMonumentPiece1.d, 12, 9, i, structureboundingbox);
            }
        }

        if (this.a(structureboundingbox, 44, 21, 50, 54)) {
            this.a(world, structureboundingbox, 44, 0, 21, 50, 0, 50, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, 44, 1, 21, 50, 10, 50, WorldGenMonumentPiece1.f, WorldGenMonumentPiece1.f, false);
            this.a(world, structureboundingbox, 44, 8, 21, 46, 8, 53, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);

            for (i = 0; i < 4; ++i) {
                this.a(world, structureboundingbox, 50 - i, i + 5, 21, 50 - i, i + 5, 54, WorldGenMonumentPiece1.b, WorldGenMonumentPiece1.b, false);
            }

            for (i = 21; i <= 45; i += 3) {
                this.a(world, WorldGenMonumentPiece1.d, 45, 9, i, structureboundingbox);
            }
        }

        if (this.a(structureboundingbox, 8, 44, 49, 54)) {
            this.a(world, structureboundingbox, 14, 0, 44, 43, 0, 50, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, 14, 1, 44, 43, 10, 50, WorldGenMonumentPiece1.f, WorldGenMonumentPiece1.f, false);

            for (i = 12; i <= 45; i += 3) {
                this.a(world, WorldGenMonumentPiece1.d, i, 9, 45, structureboundingbox);
                this.a(world, WorldGenMonumentPiece1.d, i, 9, 52, structureboundingbox);
                if (i == 12 || i == 18 || i == 24 || i == 33 || i == 39 || i == 45) {
                    this.a(world, WorldGenMonumentPiece1.d, i, 9, 47, structureboundingbox);
                    this.a(world, WorldGenMonumentPiece1.d, i, 9, 50, structureboundingbox);
                    this.a(world, WorldGenMonumentPiece1.d, i, 10, 45, structureboundingbox);
                    this.a(world, WorldGenMonumentPiece1.d, i, 10, 46, structureboundingbox);
                    this.a(world, WorldGenMonumentPiece1.d, i, 10, 51, structureboundingbox);
                    this.a(world, WorldGenMonumentPiece1.d, i, 10, 52, structureboundingbox);
                    this.a(world, WorldGenMonumentPiece1.d, i, 11, 47, structureboundingbox);
                    this.a(world, WorldGenMonumentPiece1.d, i, 11, 50, structureboundingbox);
                    this.a(world, WorldGenMonumentPiece1.d, i, 12, 48, structureboundingbox);
                    this.a(world, WorldGenMonumentPiece1.d, i, 12, 49, structureboundingbox);
                }
            }

            for (i = 0; i < 3; ++i) {
                this.a(world, structureboundingbox, 8 + i, 5 + i, 54, 49 - i, 5 + i, 54, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            }

            this.a(world, structureboundingbox, 11, 8, 54, 46, 8, 54, WorldGenMonumentPiece1.b, WorldGenMonumentPiece1.b, false);
            this.a(world, structureboundingbox, 14, 8, 44, 43, 8, 53, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
        }

    }

    private void g(World world, Random random, StructureBoundingBox structureboundingbox) {
        int i;

        if (this.a(structureboundingbox, 14, 21, 20, 43)) {
            this.a(world, structureboundingbox, 14, 0, 21, 20, 0, 43, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, 14, 1, 22, 20, 14, 43, WorldGenMonumentPiece1.f, WorldGenMonumentPiece1.f, false);
            this.a(world, structureboundingbox, 18, 12, 22, 20, 12, 39, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, 18, 12, 21, 20, 12, 21, WorldGenMonumentPiece1.b, WorldGenMonumentPiece1.b, false);

            for (i = 0; i < 4; ++i) {
                this.a(world, structureboundingbox, i + 14, i + 9, 21, i + 14, i + 9, 43 - i, WorldGenMonumentPiece1.b, WorldGenMonumentPiece1.b, false);
            }

            for (i = 23; i <= 39; i += 3) {
                this.a(world, WorldGenMonumentPiece1.d, 19, 13, i, structureboundingbox);
            }
        }

        if (this.a(structureboundingbox, 37, 21, 43, 43)) {
            this.a(world, structureboundingbox, 37, 0, 21, 43, 0, 43, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, 37, 1, 22, 43, 14, 43, WorldGenMonumentPiece1.f, WorldGenMonumentPiece1.f, false);
            this.a(world, structureboundingbox, 37, 12, 22, 39, 12, 39, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, 37, 12, 21, 39, 12, 21, WorldGenMonumentPiece1.b, WorldGenMonumentPiece1.b, false);

            for (i = 0; i < 4; ++i) {
                this.a(world, structureboundingbox, 43 - i, i + 9, 21, 43 - i, i + 9, 43 - i, WorldGenMonumentPiece1.b, WorldGenMonumentPiece1.b, false);
            }

            for (i = 23; i <= 39; i += 3) {
                this.a(world, WorldGenMonumentPiece1.d, 38, 13, i, structureboundingbox);
            }
        }

        if (this.a(structureboundingbox, 15, 37, 42, 43)) {
            this.a(world, structureboundingbox, 21, 0, 37, 36, 0, 43, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);
            this.a(world, structureboundingbox, 21, 1, 37, 36, 14, 43, WorldGenMonumentPiece1.f, WorldGenMonumentPiece1.f, false);
            this.a(world, structureboundingbox, 21, 12, 37, 36, 12, 39, WorldGenMonumentPiece1.a, WorldGenMonumentPiece1.a, false);

            for (i = 0; i < 4; ++i) {
                this.a(world, structureboundingbox, 15 + i, i + 9, 43 - i, 42 - i, i + 9, 43 - i, WorldGenMonumentPiece1.b, WorldGenMonumentPiece1.b, false);
            }

            for (i = 21; i <= 36; i += 3) {
                this.a(world, WorldGenMonumentPiece1.d, i, 13, 38, structureboundingbox);
            }
        }

    }
}
