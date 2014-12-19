package net.minecraft.server;

public class ChunkCoordinatesPortal extends BlockPosition {

    public long b;
    final PortalTravelAgent c;

    public ChunkCoordinatesPortal(PortalTravelAgent portaltravelagent, BlockPosition blockposition, long i) {
        super(blockposition.getX(), blockposition.getY(), blockposition.getZ());
        this.c = portaltravelagent;
        this.b = i;
    }
}
