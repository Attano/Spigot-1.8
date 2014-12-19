package net.minecraft.server;

import java.util.Random;

class WorldGenMonumentPieceSelector6 implements IWorldGenMonumentPieceSelector {

    private WorldGenMonumentPieceSelector6() {}

    public boolean a(WorldGenMonumentStateTracker worldgenmonumentstatetracker) {
        if (worldgenmonumentstatetracker.c[EnumDirection.EAST.a()] && !worldgenmonumentstatetracker.b[EnumDirection.EAST.a()].d && worldgenmonumentstatetracker.c[EnumDirection.UP.a()] && !worldgenmonumentstatetracker.b[EnumDirection.UP.a()].d) {
            WorldGenMonumentStateTracker worldgenmonumentstatetracker1 = worldgenmonumentstatetracker.b[EnumDirection.EAST.a()];

            return worldgenmonumentstatetracker1.c[EnumDirection.UP.a()] && !worldgenmonumentstatetracker1.b[EnumDirection.UP.a()].d;
        } else {
            return false;
        }
    }

    public WorldGenMonumentPiece a(EnumDirection enumdirection, WorldGenMonumentStateTracker worldgenmonumentstatetracker, Random random) {
        worldgenmonumentstatetracker.d = true;
        worldgenmonumentstatetracker.b[EnumDirection.EAST.a()].d = true;
        worldgenmonumentstatetracker.b[EnumDirection.UP.a()].d = true;
        worldgenmonumentstatetracker.b[EnumDirection.EAST.a()].b[EnumDirection.UP.a()].d = true;
        return new WorldGenMonumentPiece4(enumdirection, worldgenmonumentstatetracker, random);
    }

    WorldGenMonumentPieceSelector6(SwitchHelperDirection5 switchhelperdirection5) {
        this();
    }
}
