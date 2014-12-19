package net.minecraft.server;

public class EntityMagmaCube extends EntitySlime {

    public EntityMagmaCube(World world) {
        super(world);
        this.fireProof = true;
    }

    protected void aW() {
        super.aW();
        this.getAttributeInstance(GenericAttributes.d).setValue(0.20000000298023224D);
    }

    public boolean bQ() {
        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL;
    }

    public boolean canSpawn() {
        return this.world.a(this.getBoundingBox(), (Entity) this) && this.world.getCubes(this, this.getBoundingBox()).isEmpty() && !this.world.containsLiquid(this.getBoundingBox());
    }

    public int bq() {
        return this.getSize() * 3;
    }

    public float c(float f) {
        return 1.0F;
    }

    protected EnumParticle n() {
        return EnumParticle.FLAME;
    }

    protected EntitySlime cd() {
        return new EntityMagmaCube(this.world);
    }

    protected Item getLoot() {
        return Items.MAGMA_CREAM;
    }

    protected void dropDeathLoot(boolean flag, int i) {
        Item item = this.getLoot();

        if (item != null && this.getSize() > 1) {
            int j = this.random.nextInt(4) - 2;

            if (i > 0) {
                j += this.random.nextInt(i + 1);
            }

            for (int k = 0; k < j; ++k) {
                this.a(item, 1);
            }
        }

    }

    public boolean isBurning() {
        return false;
    }

    protected int ce() {
        return super.ce() * 4;
    }

    protected void cf() {
        this.a *= 0.9F;
    }

    protected void bE() {
        this.motY = (double) (0.42F + (float) this.getSize() * 0.1F);
        this.ai = true;
    }

    protected void bG() {
        this.motY = (double) (0.22F + (float) this.getSize() * 0.05F);
        this.ai = true;
    }

    public void e(float f, float f1) {}

    protected boolean cg() {
        return true;
    }

    protected int ch() {
        return super.ch() + 2;
    }

    protected String ci() {
        return this.getSize() > 1 ? "mob.magmacube.big" : "mob.magmacube.small";
    }

    protected boolean cj() {
        return true;
    }
}
