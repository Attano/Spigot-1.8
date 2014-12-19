package net.minecraft.server;

import java.util.Random;

interface IWorldGenMonumentPieceSelector {

    boolean a(WorldGenMonumentStateTracker worldgenmonumentstatetracker);

    WorldGenMonumentPiece a(EnumDirection enumdirection, WorldGenMonumentStateTracker worldgenmonumentstatetracker, Random random);
}
