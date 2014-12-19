package net.minecraft.server;

public class TileEntityContainerAnvil implements ITileEntityContainer {

    private final World a;
    private final BlockPosition b;

    public TileEntityContainerAnvil(World world, BlockPosition blockposition) {
        this.a = world;
        this.b = blockposition;
    }

    public String getName() {
        return "anvil";
    }

    public boolean hasCustomName() {
        return false;
    }

    public IChatBaseComponent getScoreboardDisplayName() {
        return new ChatMessage(Blocks.ANVIL.a() + ".name", new Object[0]);
    }

    public Container createContainer(PlayerInventory playerinventory, EntityHuman entityhuman) {
        return new ContainerAnvil(playerinventory, this.a, this.b, entityhuman);
    }

    public String getContainerName() {
        return "minecraft:anvil";
    }
}
