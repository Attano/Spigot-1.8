package net.minecraft.server;

import java.util.Random;

class WorldGenMonumentPieceSelector5 implements IWorldGenMonumentPieceSelector {

    private WorldGenMonumentPieceSelector5() {}

    public boolean a(WorldGenMonumentStateTracker worldgenmonumentstatetracker) {
        return worldgenmonumentstatetracker.c[EnumDirection.UP.a()] && !worldgenmonumentstatetracker.b[EnumDirection.UP.a()].d;
    }

    public WorldGenMonumentPiece a(EnumDirection enumdirection, WorldGenMonumentStateTracker worldgenmonumentstatetracker, Random random) {
        worldgenmonumentstatetracker.d = true;
        worldgenmonumentstatetracker.b[EnumDirection.UP.a()].d = true;
        return new WorldGenMonumentPiece5(enumdirection, worldgenmonumentstatetracker, random);
    }

    WorldGenMonumentPieceSelector5(SwitchHelperDirection5 switchhelperdirection5) {
        this();
    }
}
