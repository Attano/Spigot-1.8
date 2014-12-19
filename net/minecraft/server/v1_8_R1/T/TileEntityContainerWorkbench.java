package net.minecraft.server;

public class TileEntityContainerWorkbench implements ITileEntityContainer {

    private final World a;
    private final BlockPosition b;

    public TileEntityContainerWorkbench(World world, BlockPosition blockposition) {
        this.a = world;
        this.b = blockposition;
    }

    public String getName() {
        return null;
    }

    public boolean hasCustomName() {
        return false;
    }

    public IChatBaseComponent getScoreboardDisplayName() {
        return new ChatMessage(Blocks.CRAFTING_TABLE.a() + ".name", new Object[0]);
    }

    public Container createContainer(PlayerInventory playerinventory, EntityHuman entityhuman) {
        return new ContainerWorkbench(playerinventory, this.a, this.b);
    }

    public String getContainerName() {
        return "minecraft:crafting_table";
    }
}
