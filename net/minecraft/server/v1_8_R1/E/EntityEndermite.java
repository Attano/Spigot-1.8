package net.minecraft.server;

public class EntityEndermite extends EntityMonster {

    private int b = 0;
    private boolean c = false;

    public EntityEndermite(World world) {
        super(world);
        this.b_ = 3;
        this.a(0.4F, 0.3F);
        this.goalSelector.a(1, new PathfinderGoalFloat(this));
        this.goalSelector.a(2, new PathfinderGoalMeleeAttack(this, EntityHuman.class, 1.0D, false));
        this.goalSelector.a(3, new PathfinderGoalRandomStroll(this, 1.0D));
        this.goalSelector.a(7, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
        this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, true, new Class[0]));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
    }

    public float getHeadHeight() {
        return 0.1F;
    }

    protected void aW() {
        super.aW();
        this.getAttributeInstance(GenericAttributes.maxHealth).setValue(8.0D);
        this.getAttributeInstance(GenericAttributes.d).setValue(0.25D);
        this.getAttributeInstance(GenericAttributes.e).setValue(2.0D);
    }

    protected boolean r_() {
        return false;
    }

    protected String z() {
        return "mob.silverfish.say";
    }

    protected String bn() {
        return "mob.silverfish.hit";
    }

    protected String bo() {
        return "mob.silverfish.kill";
    }

    protected void a(BlockPosition blockposition, Block block) {
        this.makeSound("mob.silverfish.step", 0.15F, 1.0F);
    }

    protected Item getLoot() {
        return null;
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.b = nbttagcompound.getInt("Lifetime");
        this.c = nbttagcompound.getBoolean("PlayerSpawned");
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setInt("Lifetime", this.b);
        nbttagcompound.setBoolean("PlayerSpawned", this.c);
    }

    public void s_() {
        this.aG = this.yaw;
        super.s_();
    }

    public boolean n() {
        return this.c;
    }

    public void a(boolean flag) {
        this.c = flag;
    }

    public void m() {
        super.m();
        if (this.world.isStatic) {
            for (int i = 0; i < 2; ++i) {
                this.world.addParticle(EnumParticle.PORTAL, this.locX + (this.random.nextDouble() - 0.5D) * (double) this.width, this.locY + this.random.nextDouble() * (double) this.length, this.locZ + (this.random.nextDouble() - 0.5D) * (double) this.width, (this.random.nextDouble() - 0.5D) * 2.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 2.0D, new int[0]);
            }
        } else {
            if (!this.isPersistent()) {
                ++this.b;
            }

            if (this.b >= 2400) {
                this.die();
            }
        }

    }

    protected boolean m_() {
        return true;
    }

    public boolean bQ() {
        if (super.bQ()) {
            EntityHuman entityhuman = this.world.findNearbyPlayer(this, 5.0D);

            return entityhuman == null;
        } else {
            return false;
        }
    }

    public EnumMonsterType getMonsterType() {
        return EnumMonsterType.ARTHROPOD;
    }
}
