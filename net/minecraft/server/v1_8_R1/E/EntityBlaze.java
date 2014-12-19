package net.minecraft.server;

public class EntityBlaze extends EntityMonster {

    private float b = 0.5F;
    private int c;

    public EntityBlaze(World world) {
        super(world);
        this.fireProof = true;
        this.b_ = 10;
        this.goalSelector.a(4, new PathfinderGoalBlazeFireball(this));
        this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 1.0D));
        this.goalSelector.a(7, new PathfinderGoalRandomStroll(this, 1.0D));
        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
        this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, true, new Class[0]));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
    }

    protected void aW() {
        super.aW();
        this.getAttributeInstance(GenericAttributes.e).setValue(6.0D);
        this.getAttributeInstance(GenericAttributes.d).setValue(0.23000000417232513D);
        this.getAttributeInstance(GenericAttributes.b).setValue(48.0D);
    }

    protected void h() {
        super.h();
        this.datawatcher.a(16, new Byte((byte) 0));
    }

    protected String z() {
        return "mob.blaze.breathe";
    }

    protected String bn() {
        return "mob.blaze.hit";
    }

    protected String bo() {
        return "mob.blaze.death";
    }

    public float c(float f) {
        return 1.0F;
    }

    public void m() {
        if (!this.onGround && this.motY < 0.0D) {
            this.motY *= 0.6D;
        }

        if (this.world.isStatic) {
            if (this.random.nextInt(24) == 0 && !this.R()) {
                this.world.a(this.locX + 0.5D, this.locY + 0.5D, this.locZ + 0.5D, "fire.fire", 1.0F + this.random.nextFloat(), this.random.nextFloat() * 0.7F + 0.3F, false);
            }

            for (int i = 0; i < 2; ++i) {
                this.world.addParticle(EnumParticle.SMOKE_LARGE, this.locX + (this.random.nextDouble() - 0.5D) * (double) this.width, this.locY + this.random.nextDouble() * (double) this.length, this.locZ + (this.random.nextDouble() - 0.5D) * (double) this.width, 0.0D, 0.0D, 0.0D, new int[0]);
            }
        }

        super.m();
    }

    protected void E() {
        if (this.U()) {
            this.damageEntity(DamageSource.DROWN, 1.0F);
        }

        --this.c;
        if (this.c <= 0) {
            this.c = 100;
            this.b = 0.5F + (float) this.random.nextGaussian() * 3.0F;
        }

        EntityLiving entityliving = this.getGoalTarget();

        if (entityliving != null && entityliving.locY + (double) entityliving.getHeadHeight() > this.locY + (double) this.getHeadHeight() + (double) this.b) {
            this.motY += (0.30000001192092896D - this.motY) * 0.30000001192092896D;
            this.ai = true;
        }

        super.E();
    }

    public void e(float f, float f1) {}

    protected Item getLoot() {
        return Items.BLAZE_ROD;
    }

    public boolean isBurning() {
        return this.n();
    }

    protected void dropDeathLoot(boolean flag, int i) {
        if (flag) {
            int j = this.random.nextInt(2 + i);

            for (int k = 0; k < j; ++k) {
                this.a(Items.BLAZE_ROD, 1);
            }
        }

    }

    public boolean n() {
        return (this.datawatcher.getByte(16) & 1) != 0;
    }

    public void a(boolean flag) {
        byte b0 = this.datawatcher.getByte(16);

        if (flag) {
            b0 = (byte) (b0 | 1);
        } else {
            b0 &= -2;
        }

        this.datawatcher.watch(16, Byte.valueOf(b0));
    }

    protected boolean m_() {
        return true;
    }
}
