package net.minecraft.server;

import java.util.Random;

class WorldGenMonumentPieceSelector2 implements IWorldGenMonumentPieceSelector {

    private WorldGenMonumentPieceSelector2() {}

    public boolean a(WorldGenMonumentStateTracker worldgenmonumentstatetracker) {
        return true;
    }

    public WorldGenMonumentPiece a(EnumDirection enumdirection, WorldGenMonumentStateTracker worldgenmonumentstatetracker, Random random) {
        worldgenmonumentstatetracker.d = true;
        return new WorldGenMonumentPieceSimple(enumdirection, worldgenmonumentstatetracker, random);
    }

    WorldGenMonumentPieceSelector2(SwitchHelperDirection5 switchhelperdirection5) {
        this();
    }
}
