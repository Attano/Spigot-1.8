package net.minecraft.server;

import java.util.Random;

class WorldGenMonumentPieceSelector3 implements IWorldGenMonumentPieceSelector {

    private WorldGenMonumentPieceSelector3() {}

    public boolean a(WorldGenMonumentStateTracker worldgenmonumentstatetracker) {
        return worldgenmonumentstatetracker.c[EnumDirection.NORTH.a()] && !worldgenmonumentstatetracker.b[EnumDirection.NORTH.a()].d;
    }

    public WorldGenMonumentPiece a(EnumDirection enumdirection, WorldGenMonumentStateTracker worldgenmonumentstatetracker, Random random) {
        WorldGenMonumentStateTracker worldgenmonumentstatetracker1 = worldgenmonumentstatetracker;

        if (!worldgenmonumentstatetracker.c[EnumDirection.NORTH.a()] || worldgenmonumentstatetracker.b[EnumDirection.NORTH.a()].d) {
            worldgenmonumentstatetracker1 = worldgenmonumentstatetracker.b[EnumDirection.SOUTH.a()];
        }

        worldgenmonumentstatetracker1.d = true;
        worldgenmonumentstatetracker1.b[EnumDirection.NORTH.a()].d = true;
        return new WorldGenMonumentPiece7(enumdirection, worldgenmonumentstatetracker1, random);
    }

    WorldGenMonumentPieceSelector3(SwitchHelperDirection5 switchhelperdirection5) {
        this();
    }
}
