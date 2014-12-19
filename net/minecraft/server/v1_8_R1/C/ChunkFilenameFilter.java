package net.minecraft.server;

import java.io.File;
import java.io.FilenameFilter;

class ChunkFilenameFilter implements FilenameFilter {

    final WorldLoaderServer a;

    ChunkFilenameFilter(WorldLoaderServer worldloaderserver) {
        this.a = worldloaderserver;
    }

    public boolean accept(File file, String s) {
        return s.endsWith(".mcr");
    }
}
