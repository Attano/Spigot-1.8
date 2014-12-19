package net.minecraft.server;

import java.util.Random;

class WorldGenMonumentPieceSelector7 implements IWorldGenMonumentPieceSelector {

    private WorldGenMonumentPieceSelector7() {}

    public boolean a(WorldGenMonumentStateTracker worldgenmonumentstatetracker) {
        return worldgenmonumentstatetracker.c[EnumDirection.EAST.a()] && !worldgenmonumentstatetracker.b[EnumDirection.EAST.a()].d;
    }

    public WorldGenMonumentPiece a(EnumDirection enumdirection, WorldGenMonumentStateTracker worldgenmonumentstatetracker, Random random) {
        worldgenmonumentstatetracker.d = true;
        worldgenmonumentstatetracker.b[EnumDirection.EAST.a()].d = true;
        return new WorldGenMonumentPiece3(enumdirection, worldgenmonumentstatetracker, random);
    }

    WorldGenMonumentPieceSelector7(SwitchHelperDirection5 switchhelperdirection5) {
        this();
    }
}
