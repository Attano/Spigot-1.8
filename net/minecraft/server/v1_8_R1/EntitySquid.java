package net.minecraft.server;

import org.bukkit.craftbukkit.TrigMath; // CraftBukkit

public class EntitySquid extends EntityWaterAnimal {

    public float a;
    public float b;
    public float c;
    public float bi;
    public float bj;
    public float bk;
    public float bl;
    public float bm;
    private float bn;
    private float bo;
    private float bp;
    private float bq;
    private float br;
    private float bs;

    public EntitySquid(World world) {
        super(world);
        this.a(0.95F, 0.95F);
        this.random.setSeed((long) (1 + this.getId()));
        this.bo = 1.0F / (this.random.nextFloat() + 1.0F) * 0.2F;
        this.goalSelector.a(0, new PathfinderGoalSquid(this));
    }

    protected void aW() {
        super.aW();
        this.getAttributeInstance(GenericAttributes.maxHealth).setValue(10.0D);
    }

    public float getHeadHeight() {
        return this.length * 0.5F;
    }

    protected String z() {
        return null;
    }

    protected String bn() {
        return null;
    }

    protected String bo() {
        return null;
    }

    protected float bA() {
        return 0.4F;
    }

    protected Item getLoot() {
        return null;
    }

    protected boolean r_() {
        return false;
    }

    protected void dropDeathLoot(boolean flag, int i) {
        int j = this.random.nextInt(3 + i) + 1;

        for (int k = 0; k < j; ++k) {
            this.a(new ItemStack(Items.DYE, 1, EnumColor.BLACK.getInvColorIndex()), 0.0F);
        }

    }

    /* CraftBukkit start - Delegate to Entity to use existing inWater value
    public boolean V() {
        return this.world.a(this.getBoundingBox().grow(0.0D, -0.6000000238418579D, 0.0D), Material.WATER, (Entity) this);
    }
    // CraftBukkit end */

    public void m() {
        super.m();
        this.b = this.a;
        this.bi = this.c;
        this.bk = this.bj;
        this.bm = this.bl;
        this.bj += this.bo;
        if ((double) this.bj > 6.283185307179586D) {
            if (this.world.isStatic) {
                this.bj = 6.2831855F;
            } else {
                this.bj = (float) ((double) this.bj - 6.283185307179586D);
                if (this.random.nextInt(10) == 0) {
                    this.bo = 1.0F / (this.random.nextFloat() + 1.0F) * 0.2F;
                }

                this.world.broadcastEntityEffect(this, (byte) 19);
            }
        }

        if (this.inWater) {
            float f;

            if (this.bj < 3.1415927F) {
                f = this.bj / 3.1415927F;
                this.bl = MathHelper.sin(f * f * 3.1415927F) * 3.1415927F * 0.25F;
                if ((double) f > 0.75D) {
                    this.bn = 1.0F;
                    this.bp = 1.0F;
                } else {
                    this.bp *= 0.8F;
                }
            } else {
                this.bl = 0.0F;
                this.bn *= 0.9F;
                this.bp *= 0.99F;
            }

            if (!this.world.isStatic) {
                this.motX = (double) (this.bq * this.bn);
                this.motY = (double) (this.br * this.bn);
                this.motZ = (double) (this.bs * this.bn);
            }

            f = MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ);
            // CraftBukkit - Math -> TrigMath
            this.aG += (-((float) TrigMath.atan2(this.motX, this.motZ)) * 180.0F / 3.1415927F - this.aG) * 0.1F;
            this.yaw = this.aG;
            this.c = (float) ((double) this.c + 3.141592653589793D * (double) this.bp * 1.5D);
            // CraftBukkit - Math -> TrigMath
            this.a += (-((float) TrigMath.atan2((double) f, this.motY)) * 180.0F / 3.1415927F - this.a) * 0.1F;
        } else {
            this.bl = MathHelper.e(MathHelper.sin(this.bj)) * 3.1415927F * 0.25F;
            if (!this.world.isStatic) {
                this.motX = 0.0D;
                this.motY -= 0.08D;
                this.motY *= 0.9800000190734863D;
                this.motZ = 0.0D;
            }

            this.a = (float) ((double) this.a + (double) (-90.0F - this.a) * 0.02D);
        }

    }

    public void g(float f, float f1) {
        this.move(this.motX, this.motY, this.motZ);
    }

    public boolean bQ() {
        return this.locY > 45.0D && this.locY < 63.0D && super.bQ();
    }

    public void b(float f, float f1, float f2) {
        this.bq = f;
        this.br = f1;
        this.bs = f2;
    }

    public boolean n() {
        return this.bq != 0.0F || this.br != 0.0F || this.bs != 0.0F;
    }

    static boolean a(EntitySquid entitysquid) {
        return entitysquid.inWater;
    }
}
