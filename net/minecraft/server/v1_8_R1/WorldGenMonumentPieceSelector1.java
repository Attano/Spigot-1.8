package net.minecraft.server;

import java.util.Random;

class WorldGenMonumentPieceSelector1 implements IWorldGenMonumentPieceSelector {

    private WorldGenMonumentPieceSelector1() {}

    public boolean a(WorldGenMonumentStateTracker worldgenmonumentstatetracker) {
        return !worldgenmonumentstatetracker.c[EnumDirection.WEST.a()] && !worldgenmonumentstatetracker.c[EnumDirection.EAST.a()] && !worldgenmonumentstatetracker.c[EnumDirection.NORTH.a()] && !worldgenmonumentstatetracker.c[EnumDirection.SOUTH.a()] && !worldgenmonumentstatetracker.c[EnumDirection.UP.a()];
    }

    public WorldGenMonumentPiece a(EnumDirection enumdirection, WorldGenMonumentStateTracker worldgenmonumentstatetracker, Random random) {
        worldgenmonumentstatetracker.d = true;
        return new WorldGenMonumentPieceSimpleT(enumdirection, worldgenmonumentstatetracker, random);
    }

    WorldGenMonumentPieceSelector1(SwitchHelperDirection5 switchhelperdirection5) {
        this();
    }
}
