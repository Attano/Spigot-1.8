package net.minecraft.server;

public class BlockHalfTransparent extends Block {

    private boolean a;

    protected BlockHalfTransparent(Material material, boolean flag) {
        super(material);
        this.a = flag;
    }

    public boolean c() {
        return false;
    }
}
