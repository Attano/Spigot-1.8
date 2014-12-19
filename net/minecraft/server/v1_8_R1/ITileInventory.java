package net.minecraft.server;

public interface ITileInventory extends IInventory, ITileEntityContainer {

    boolean q_();

    void a(ChestLock chestlock);

    ChestLock i();
}
