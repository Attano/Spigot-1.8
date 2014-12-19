package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class WorldGenVillagePieces {

    public static void a() {
        WorldGenFactory.a(WorldGenVillageLibrary.class, "ViBH");
        WorldGenFactory.a(WorldGenVillageFarm2.class, "ViDF");
        WorldGenFactory.a(WorldGenVillageFarm.class, "ViF");
        WorldGenFactory.a(WorldGenVillageLight.class, "ViL");
        WorldGenFactory.a(WorldGenVillageButcher.class, "ViPH");
        WorldGenFactory.a(WorldGenVillageHouse.class, "ViSH");
        WorldGenFactory.a(WorldGenVillageHut.class, "ViSmH");
        WorldGenFactory.a(WorldGenVillageTemple.class, "ViST");
        WorldGenFactory.a(WorldGenVillageBlacksmith.class, "ViS");
        WorldGenFactory.a(WorldGenVillageStartPiece.class, "ViStart");
        WorldGenFactory.a(WorldGenVillageRoad.class, "ViSR");
        WorldGenFactory.a(WorldGenVillageHouse2.class, "ViTRH");
        WorldGenFactory.a(WorldGenVillageWell.class, "ViW");
    }

    public static List a(Random random, int i) {
        ArrayList arraylist = Lists.newArrayList();

        arraylist.add(new WorldGenVillagePieceWeight(WorldGenVillageHouse.class, 4, MathHelper.nextInt(random, 2 + i, 4 + i * 2)));
        arraylist.add(new WorldGenVillagePieceWeight(WorldGenVillageTemple.class, 20, MathHelper.nextInt(random, 0 + i, 1 + i)));
        arraylist.add(new WorldGenVillagePieceWeight(WorldGenVillageLibrary.class, 20, MathHelper.nextInt(random, 0 + i, 2 + i)));
        arraylist.add(new WorldGenVillagePieceWeight(WorldGenVillageHut.class, 3, MathHelper.nextInt(random, 2 + i, 5 + i * 3)));
        arraylist.add(new WorldGenVillagePieceWeight(WorldGenVillageButcher.class, 15, MathHelper.nextInt(random, 0 + i, 2 + i)));
        arraylist.add(new WorldGenVillagePieceWeight(WorldGenVillageFarm2.class, 3, MathHelper.nextInt(random, 1 + i, 4 + i)));
        arraylist.add(new WorldGenVillagePieceWeight(WorldGenVillageFarm.class, 3, MathHelper.nextInt(random, 2 + i, 4 + i * 2)));
        arraylist.add(new WorldGenVillagePieceWeight(WorldGenVillageBlacksmith.class, 15, MathHelper.nextInt(random, 0, 1 + i)));
        arraylist.add(new WorldGenVillagePieceWeight(WorldGenVillageHouse2.class, 8, MathHelper.nextInt(random, 0 + i, 3 + i * 2)));
        Iterator iterator = arraylist.iterator();

        while (iterator.hasNext()) {
            if (((WorldGenVillagePieceWeight) iterator.next()).d == 0) {
                iterator.remove();
            }
        }

        return arraylist;
    }

    private static int a(List list) {
        boolean flag = false;
        int i = 0;

        WorldGenVillagePieceWeight worldgenvillagepieceweight;

        for (Iterator iterator = list.iterator(); iterator.hasNext(); i += worldgenvillagepieceweight.b) {
            worldgenvillagepieceweight = (WorldGenVillagePieceWeight) iterator.next();
            if (worldgenvillagepieceweight.d > 0 && worldgenvillagepieceweight.c < worldgenvillagepieceweight.d) {
                flag = true;
            }
        }

        return flag ? i : -1;
    }

    private static WorldGenVillagePiece a(WorldGenVillageStartPiece worldgenvillagestartpiece, WorldGenVillagePieceWeight worldgenvillagepieceweight, List list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
        Class oclass = worldgenvillagepieceweight.a;
        Object object = null;

        if (oclass == WorldGenVillageHouse.class) {
            object = WorldGenVillageHouse.a(worldgenvillagestartpiece, list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenVillageTemple.class) {
            object = WorldGenVillageTemple.a(worldgenvillagestartpiece, list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenVillageLibrary.class) {
            object = WorldGenVillageLibrary.a(worldgenvillagestartpiece, list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenVillageHut.class) {
            object = WorldGenVillageHut.a(worldgenvillagestartpiece, list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenVillageButcher.class) {
            object = WorldGenVillageButcher.a(worldgenvillagestartpiece, list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenVillageFarm2.class) {
            object = WorldGenVillageFarm2.a(worldgenvillagestartpiece, list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenVillageFarm.class) {
            object = WorldGenVillageFarm.a(worldgenvillagestartpiece, list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenVillageBlacksmith.class) {
            object = WorldGenVillageBlacksmith.a(worldgenvillagestartpiece, list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenVillageHouse2.class) {
            object = WorldGenVillageHouse2.a(worldgenvillagestartpiece, list, random, i, j, k, enumdirection, l);
        }

        return (WorldGenVillagePiece) object;
    }

    private static WorldGenVillagePiece c(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
        int i1 = a(worldgenvillagestartpiece.e);

        if (i1 <= 0) {
            return null;
        } else {
            int j1 = 0;

            while (j1 < 5) {
                ++j1;
                int k1 = random.nextInt(i1);
                Iterator iterator = worldgenvillagestartpiece.e.iterator();

                while (iterator.hasNext()) {
                    WorldGenVillagePieceWeight worldgenvillagepieceweight = (WorldGenVillagePieceWeight) iterator.next();

                    k1 -= worldgenvillagepieceweight.b;
                    if (k1 < 0) {
                        if (!worldgenvillagepieceweight.a(l) || worldgenvillagepieceweight == worldgenvillagestartpiece.d && worldgenvillagestartpiece.e.size() > 1) {
                            break;
                        }

                        WorldGenVillagePiece worldgenvillagepiece = a(worldgenvillagestartpiece, worldgenvillagepieceweight, list, random, i, j, k, enumdirection, l);

                        if (worldgenvillagepiece != null) {
                            ++worldgenvillagepieceweight.c;
                            worldgenvillagestartpiece.d = worldgenvillagepieceweight;
                            if (!worldgenvillagepieceweight.a()) {
                                worldgenvillagestartpiece.e.remove(worldgenvillagepieceweight);
                            }

                            return worldgenvillagepiece;
                        }
                    }
                }
            }

            StructureBoundingBox structureboundingbox = WorldGenVillageLight.a(worldgenvillagestartpiece, list, random, i, j, k, enumdirection);

            if (structureboundingbox != null) {
                return new WorldGenVillageLight(worldgenvillagestartpiece, l, random, structureboundingbox, enumdirection);
            } else {
                return null;
            }
        }
    }

    private static StructurePiece d(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
        if (l > 50) {
            return null;
        } else if (Math.abs(i - worldgenvillagestartpiece.c().a) <= 112 && Math.abs(k - worldgenvillagestartpiece.c().c) <= 112) {
            WorldGenVillagePiece worldgenvillagepiece = c(worldgenvillagestartpiece, list, random, i, j, k, enumdirection, l + 1);

            if (worldgenvillagepiece != null) {
                int i1 = (worldgenvillagepiece.l.a + worldgenvillagepiece.l.d) / 2;
                int j1 = (worldgenvillagepiece.l.c + worldgenvillagepiece.l.f) / 2;
                int k1 = worldgenvillagepiece.l.d - worldgenvillagepiece.l.a;
                int l1 = worldgenvillagepiece.l.f - worldgenvillagepiece.l.c;
                int i2 = k1 > l1 ? k1 : l1;

                if (worldgenvillagestartpiece.e().a(i1, j1, i2 / 2 + 4, WorldGenVillage.d)) {
                    list.add(worldgenvillagepiece);
                    worldgenvillagestartpiece.f.add(worldgenvillagepiece);
                    return worldgenvillagepiece;
                }
            }

            return null;
        } else {
            return null;
        }
    }

    private static StructurePiece e(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
        if (l > 3 + worldgenvillagestartpiece.c) {
            return null;
        } else if (Math.abs(i - worldgenvillagestartpiece.c().a) <= 112 && Math.abs(k - worldgenvillagestartpiece.c().c) <= 112) {
            StructureBoundingBox structureboundingbox = WorldGenVillageRoad.a(worldgenvillagestartpiece, list, random, i, j, k, enumdirection);

            if (structureboundingbox != null && structureboundingbox.b > 10) {
                WorldGenVillageRoad worldgenvillageroad = new WorldGenVillageRoad(worldgenvillagestartpiece, l, random, structureboundingbox, enumdirection);
                int i1 = (worldgenvillageroad.l.a + worldgenvillageroad.l.d) / 2;
                int j1 = (worldgenvillageroad.l.c + worldgenvillageroad.l.f) / 2;
                int k1 = worldgenvillageroad.l.d - worldgenvillageroad.l.a;
                int l1 = worldgenvillageroad.l.f - worldgenvillageroad.l.c;
                int i2 = k1 > l1 ? k1 : l1;

                if (worldgenvillagestartpiece.e().a(i1, j1, i2 / 2 + 4, WorldGenVillage.d)) {
                    list.add(worldgenvillageroad);
                    worldgenvillagestartpiece.g.add(worldgenvillageroad);
                    return worldgenvillageroad;
                }
            }

            return null;
        } else {
            return null;
        }
    }

    static StructurePiece a(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
        return d(worldgenvillagestartpiece, list, random, i, j, k, enumdirection, l);
    }

    static StructurePiece b(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
        return e(worldgenvillagestartpiece, list, random, i, j, k, enumdirection, l);
    }
}
