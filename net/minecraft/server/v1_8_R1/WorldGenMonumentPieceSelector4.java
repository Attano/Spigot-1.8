package net.minecraft.server;

import java.util.Random;

class WorldGenMonumentPieceSelector4 implements IWorldGenMonumentPieceSelector {

    private WorldGenMonumentPieceSelector4() {}

    public boolean a(WorldGenMonumentStateTracker worldgenmonumentstatetracker) {
        if (worldgenmonumentstatetracker.c[EnumDirection.NORTH.a()] && !worldgenmonumentstatetracker.b[EnumDirection.NORTH.a()].d && worldgenmonumentstatetracker.c[EnumDirection.UP.a()] && !worldgenmonumentstatetracker.b[EnumDirection.UP.a()].d) {
            WorldGenMonumentStateTracker worldgenmonumentstatetracker1 = worldgenmonumentstatetracker.b[EnumDirection.NORTH.a()];

            return worldgenmonumentstatetracker1.c[EnumDirection.UP.a()] && !worldgenmonumentstatetracker1.b[EnumDirection.UP.a()].d;
        } else {
            return false;
        }
    }

    public WorldGenMonumentPiece a(EnumDirection enumdirection, WorldGenMonumentStateTracker worldgenmonumentstatetracker, Random random) {
        worldgenmonumentstatetracker.d = true;
        worldgenmonumentstatetracker.b[EnumDirection.NORTH.a()].d = true;
        worldgenmonumentstatetracker.b[EnumDirection.UP.a()].d = true;
        worldgenmonumentstatetracker.b[EnumDirection.NORTH.a()].b[EnumDirection.UP.a()].d = true;
        return new WorldGenMonumentPiece6(enumdirection, worldgenmonumentstatetracker, random);
    }

    WorldGenMonumentPieceSelector4(SwitchHelperDirection5 switchhelperdirection5) {
        this();
    }
}
