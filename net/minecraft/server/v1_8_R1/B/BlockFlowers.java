package net.minecraft.server;

import com.google.common.base.Predicate;

public abstract class BlockFlowers extends BlockPlant {

    protected BlockStateEnum TYPE;

    protected BlockFlowers() {
        super(Material.PLANT);
        this.j(this.blockStateList.getBlockData().set(this.l(), this.j() == EnumFlowerType.RED ? EnumFlowerVarient.POPPY : EnumFlowerVarient.DANDELION));
    }

    public int getDropData(IBlockData iblockdata) {
        return ((EnumFlowerVarient) iblockdata.get(this.l())).b();
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(this.l(), EnumFlowerVarient.a(this.j(), i));
    }

    public abstract EnumFlowerType j();

    public IBlockState l() {
        if (this.TYPE == null) {
            this.TYPE = BlockStateEnum.a("type", EnumFlowerVarient.class, (Predicate) (new BlockFlowersInnerClass1(this)));
        }

        return this.TYPE;
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((EnumFlowerVarient) iblockdata.get(this.l())).b();
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { this.l()});
    }
}
