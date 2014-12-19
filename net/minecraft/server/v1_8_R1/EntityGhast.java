package net.minecraft.server;

public class EntityGhast extends EntityFlying implements IMonster {

    private int a = 1;

    public EntityGhast(World world) {
        super(world);
        this.a(4.0F, 4.0F);
        this.fireProof = true;
        this.b_ = 5;
        this.moveController = new ControllerGhast(this);
        this.goalSelector.a(5, new PathfinderGoalGhastIdleMove(this));
        this.goalSelector.a(7, new PathfinderGoalGhastMoveTowardsTarget(this));
        this.goalSelector.a(7, new PathfinderGoalGhastAttackTarget(this));
        this.targetSelector.a(1, new PathfinderGoalTargetNearestPlayer(this));
    }

    public void a(boolean flag) {
        this.datawatcher.watch(16, Byte.valueOf((byte) (flag ? 1 : 0)));
    }

    public int cd() {
        return this.a;
    }

    public void s_() {
        super.s_();
        if (!this.world.isStatic && this.world.getDifficulty() == EnumDifficulty.PEACEFUL) {
            this.die();
        }

    }

    public boolean damageEntity(DamageSource damagesource, float f) {
        if (this.isInvulnerable(damagesource)) {
            return false;
        } else if ("fireball".equals(damagesource.p()) && damagesource.getEntity() instanceof EntityHuman) {
            super.damageEntity(damagesource, 1000.0F);
            ((EntityHuman) damagesource.getEntity()).b((Statistic) AchievementList.z);
            return true;
        } else {
            return super.damageEntity(damagesource, f);
        }
    }

    protected void h() {
        super.h();
        this.datawatcher.a(16, Byte.valueOf((byte) 0));
    }

    protected void aW() {
        super.aW();
        this.getAttributeInstance(GenericAttributes.maxHealth).setValue(10.0D);
        this.getAttributeInstance(GenericAttributes.b).setValue(100.0D);
    }

    protected String z() {
        return "mob.ghast.moan";
    }

    protected String bn() {
        return "mob.ghast.scream";
    }

    protected String bo() {
        return "mob.ghast.death";
    }

    protected Item getLoot() {
        return Items.GUNPOWDER;
    }

    protected void dropDeathLoot(boolean flag, int i) {
        int j = this.random.nextInt(2) + this.random.nextInt(1 + i);

        int k;

        for (k = 0; k < j; ++k) {
            this.a(Items.GHAST_TEAR, 1);
        }

        j = this.random.nextInt(3) + this.random.nextInt(1 + i);

        for (k = 0; k < j; ++k) {
            this.a(Items.GUNPOWDER, 1);
        }

    }

    protected float bA() {
        return 10.0F;
    }

    public boolean bQ() {
        return this.random.nextInt(20) == 0 && super.bQ() && this.world.getDifficulty() != EnumDifficulty.PEACEFUL;
    }

    public int bU() {
        return 1;
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setInt("ExplosionPower", this.a);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        if (nbttagcompound.hasKeyOfType("ExplosionPower", 99)) {
            this.a = nbttagcompound.getInt("ExplosionPower");
        }

    }

    public float getHeadHeight() {
        return 2.6F;
    }
}
