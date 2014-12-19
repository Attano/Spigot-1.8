package net.minecraft.server;

public enum EnumToolMaterial {

    WOOD(0, 59, 2.0F, 0.0F, 15), STONE(1, 131, 4.0F, 1.0F, 5), IRON(2, 250, 6.0F, 2.0F, 14), EMERALD(3, 1561, 8.0F, 3.0F, 10), GOLD(0, 32, 12.0F, 0.0F, 22);

    private final int f;
    private final int g;
    private final float h;
    private final float i;
    private final int j;

    private EnumToolMaterial(int i, int j, float f, float f1, int k) {
        this.f = i;
        this.g = j;
        this.h = f;
        this.i = f1;
        this.j = k;
    }

    public int a() {
        return this.g;
    }

    public float b() {
        return this.h;
    }

    public float c() {
        return this.i;
    }

    public int d() {
        return this.f;
    }

    public int e() {
        return this.j;
    }

    public Item f() {
        return this == EnumToolMaterial.WOOD ? Item.getItemOf(Blocks.PLANKS) : (this == EnumToolMaterial.STONE ? Item.getItemOf(Blocks.COBBLESTONE) : (this == EnumToolMaterial.GOLD ? Items.GOLD_INGOT : (this == EnumToolMaterial.IRON ? Items.IRON_INGOT : (this == EnumToolMaterial.EMERALD ? Items.DIAMOND : null))));
    }
}
