package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class WorldGenStrongholdPieces {

    private static final WorldGenStrongholdPieceWeight[] b = new WorldGenStrongholdPieceWeight[] { new WorldGenStrongholdPieceWeight(WorldGenStrongholdStairs.class, 40, 0), new WorldGenStrongholdPieceWeight(WorldGenStrongholdPrison.class, 5, 5), new WorldGenStrongholdPieceWeight(WorldGenStrongholdLeftTurn.class, 20, 0), new WorldGenStrongholdPieceWeight(WorldGenStrongholdRightTurn.class, 20, 0), new WorldGenStrongholdPieceWeight(WorldGenStrongholdRoomCrossing.class, 10, 6), new WorldGenStrongholdPieceWeight(WorldGenStrongholdStairsStraight.class, 5, 5), new WorldGenStrongholdPieceWeight(WorldGenStrongholdStairs2.class, 5, 5), new WorldGenStrongholdPieceWeight(WorldGenStrongholdCrossing.class, 5, 4), new WorldGenStrongholdPieceWeight(WorldGenStrongholdChestCorridor.class, 5, 4), new WorldGenStrongholdUnknown(WorldGenStrongholdLibrary.class, 10, 2), new WorldGenStrongholdPiece2(WorldGenStrongholdPortalRoom.class, 20, 1)};
    private static List c;
    private static Class d;
    static int a;
    private static final WorldGenStrongholdStones e = new WorldGenStrongholdStones((WorldGenStrongholdUnknown) null);

    public static void a() {
        WorldGenFactory.a(WorldGenStrongholdChestCorridor.class, "SHCC");
        WorldGenFactory.a(WorldGenStrongholdCorridor.class, "SHFC");
        WorldGenFactory.a(WorldGenStrongholdCrossing.class, "SH5C");
        WorldGenFactory.a(WorldGenStrongholdLeftTurn.class, "SHLT");
        WorldGenFactory.a(WorldGenStrongholdLibrary.class, "SHLi");
        WorldGenFactory.a(WorldGenStrongholdPortalRoom.class, "SHPR");
        WorldGenFactory.a(WorldGenStrongholdPrison.class, "SHPH");
        WorldGenFactory.a(WorldGenStrongholdRightTurn.class, "SHRT");
        WorldGenFactory.a(WorldGenStrongholdRoomCrossing.class, "SHRC");
        WorldGenFactory.a(WorldGenStrongholdStairs2.class, "SHSD");
        WorldGenFactory.a(WorldGenStrongholdStart.class, "SHStart");
        WorldGenFactory.a(WorldGenStrongholdStairs.class, "SHS");
        WorldGenFactory.a(WorldGenStrongholdStairsStraight.class, "SHSSD");
    }

    public static void b() {
        WorldGenStrongholdPieces.c = Lists.newArrayList();
        WorldGenStrongholdPieceWeight[] aworldgenstrongholdpieceweight = WorldGenStrongholdPieces.b;
        int i = aworldgenstrongholdpieceweight.length;

        for (int j = 0; j < i; ++j) {
            WorldGenStrongholdPieceWeight worldgenstrongholdpieceweight = aworldgenstrongholdpieceweight[j];

            worldgenstrongholdpieceweight.c = 0;
            WorldGenStrongholdPieces.c.add(worldgenstrongholdpieceweight);
        }

        WorldGenStrongholdPieces.d = null;
    }

    private static boolean d() {
        boolean flag = false;

        WorldGenStrongholdPieces.a = 0;

        WorldGenStrongholdPieceWeight worldgenstrongholdpieceweight;

        for (Iterator iterator = WorldGenStrongholdPieces.c.iterator(); iterator.hasNext(); WorldGenStrongholdPieces.a += worldgenstrongholdpieceweight.b) {
            worldgenstrongholdpieceweight = (WorldGenStrongholdPieceWeight) iterator.next();
            if (worldgenstrongholdpieceweight.d > 0 && worldgenstrongholdpieceweight.c < worldgenstrongholdpieceweight.d) {
                flag = true;
            }
        }

        return flag;
    }

    private static WorldGenStrongholdPiece a(Class oclass, List list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
        Object object = null;

        if (oclass == WorldGenStrongholdStairs.class) {
            object = WorldGenStrongholdStairs.a(list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenStrongholdPrison.class) {
            object = WorldGenStrongholdPrison.a(list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenStrongholdLeftTurn.class) {
            object = WorldGenStrongholdLeftTurn.a(list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenStrongholdRightTurn.class) {
            object = WorldGenStrongholdRightTurn.a(list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenStrongholdRoomCrossing.class) {
            object = WorldGenStrongholdRoomCrossing.a(list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenStrongholdStairsStraight.class) {
            object = WorldGenStrongholdStairsStraight.a(list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenStrongholdStairs2.class) {
            object = WorldGenStrongholdStairs2.a(list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenStrongholdCrossing.class) {
            object = WorldGenStrongholdCrossing.a(list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenStrongholdChestCorridor.class) {
            object = WorldGenStrongholdChestCorridor.a(list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenStrongholdLibrary.class) {
            object = WorldGenStrongholdLibrary.a(list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenStrongholdPortalRoom.class) {
            object = WorldGenStrongholdPortalRoom.a(list, random, i, j, k, enumdirection, l);
        }

        return (WorldGenStrongholdPiece) object;
    }

    private static WorldGenStrongholdPiece b(WorldGenStrongholdStart worldgenstrongholdstart, List list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
        if (!d()) {
            return null;
        } else {
            if (WorldGenStrongholdPieces.d != null) {
                WorldGenStrongholdPiece worldgenstrongholdpiece = a(WorldGenStrongholdPieces.d, list, random, i, j, k, enumdirection, l);

                WorldGenStrongholdPieces.d = null;
                if (worldgenstrongholdpiece != null) {
                    return worldgenstrongholdpiece;
                }
            }

            int i1 = 0;

            while (i1 < 5) {
                ++i1;
                int j1 = random.nextInt(WorldGenStrongholdPieces.a);
                Iterator iterator = WorldGenStrongholdPieces.c.iterator();

                while (iterator.hasNext()) {
                    WorldGenStrongholdPieceWeight worldgenstrongholdpieceweight = (WorldGenStrongholdPieceWeight) iterator.next();

                    j1 -= worldgenstrongholdpieceweight.b;
                    if (j1 < 0) {
                        if (!worldgenstrongholdpieceweight.a(l) || worldgenstrongholdpieceweight == worldgenstrongholdstart.a) {
                            break;
                        }

                        WorldGenStrongholdPiece worldgenstrongholdpiece1 = a(worldgenstrongholdpieceweight.a, list, random, i, j, k, enumdirection, l);

                        if (worldgenstrongholdpiece1 != null) {
                            ++worldgenstrongholdpieceweight.c;
                            worldgenstrongholdstart.a = worldgenstrongholdpieceweight;
                            if (!worldgenstrongholdpieceweight.a()) {
                                WorldGenStrongholdPieces.c.remove(worldgenstrongholdpieceweight);
                            }

                            return worldgenstrongholdpiece1;
                        }
                    }
                }
            }

            StructureBoundingBox structureboundingbox = WorldGenStrongholdCorridor.a(list, random, i, j, k, enumdirection);

            if (structureboundingbox != null && structureboundingbox.b > 1) {
                return new WorldGenStrongholdCorridor(l, random, structureboundingbox, enumdirection);
            } else {
                return null;
            }
        }
    }

    private static StructurePiece c(WorldGenStrongholdStart worldgenstrongholdstart, List list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
        if (l > 50) {
            return null;
        } else if (Math.abs(i - worldgenstrongholdstart.c().a) <= 112 && Math.abs(k - worldgenstrongholdstart.c().c) <= 112) {
            WorldGenStrongholdPiece worldgenstrongholdpiece = b(worldgenstrongholdstart, list, random, i, j, k, enumdirection, l + 1);

            if (worldgenstrongholdpiece != null) {
                list.add(worldgenstrongholdpiece);
                worldgenstrongholdstart.c.add(worldgenstrongholdpiece);
            }

            return worldgenstrongholdpiece;
        } else {
            return null;
        }
    }

    static StructurePiece a(WorldGenStrongholdStart worldgenstrongholdstart, List list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
        return c(worldgenstrongholdstart, list, random, i, j, k, enumdirection, l);
    }

    static Class a(Class oclass) {
        WorldGenStrongholdPieces.d = oclass;
        return oclass;
    }

    static WorldGenStrongholdStones c() {
        return WorldGenStrongholdPieces.e;
    }
}
