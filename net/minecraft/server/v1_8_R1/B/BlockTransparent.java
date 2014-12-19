package net.minecraft.server;

public class BlockTransparent extends Block {

    protected boolean Q;

    protected BlockTransparent(Material material, boolean flag) {
        super(material);
        this.Q = flag;
    }

    public boolean c() {
        return false;
    }
}
