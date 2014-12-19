package net.minecraft.server;

public enum EnumArmorMaterial {

    LEATHER("leather", 5, new int[] { 1, 3, 2, 1}, 15), CHAIN("chainmail", 15, new int[] { 2, 5, 4, 1}, 12), IRON("iron", 15, new int[] { 2, 6, 5, 2}, 9), GOLD("gold", 7, new int[] { 2, 5, 3, 1}, 25), DIAMOND("diamond", 33, new int[] { 3, 8, 6, 3}, 10);

    private final String f;
    private final int g;
    private final int[] h;
    private final int i;

    private EnumArmorMaterial(String s, int i, int[] aint, int j) {
        this.f = s;
        this.g = i;
        this.h = aint;
        this.i = j;
    }

    public int a(int i) {
        return ItemArmor.d()[i] * this.g;
    }

    public int b(int i) {
        return this.h[i];
    }

    public int a() {
        return this.i;
    }

    public Item b() {
        return this == EnumArmorMaterial.LEATHER ? Items.LEATHER : (this == EnumArmorMaterial.CHAIN ? Items.IRON_INGOT : (this == EnumArmorMaterial.GOLD ? Items.GOLD_INGOT : (this == EnumArmorMaterial.IRON ? Items.IRON_INGOT : (this == EnumArmorMaterial.DIAMOND ? Items.DIAMOND : null))));
    }
}
