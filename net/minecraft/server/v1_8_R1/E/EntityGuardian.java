package net.minecraft.server;

import com.google.common.base.Predicate;
import java.util.Iterator;
import java.util.List;

public class EntityGuardian extends EntityMonster {

    private float b;
    private float c;
    private float bk;
    private float bl;
    private float bm;
    private EntityLiving bn;
    private int bo;
    private boolean bp;
    private PathfinderGoalRandomStroll bq;

    public EntityGuardian(World world) {
        super(world);
        this.b_ = 10;
        this.a(0.85F, 0.85F);
        this.goalSelector.a(4, new PathfinderGoalGuardianAttack(this));
        PathfinderGoalMoveTowardsRestriction pathfindergoalmovetowardsrestriction;

        this.goalSelector.a(5, pathfindergoalmovetowardsrestriction = new PathfinderGoalMoveTowardsRestriction(this, 1.0D));
        this.goalSelector.a(7, this.bq = new PathfinderGoalRandomStroll(this, 1.0D, 80));
        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityGuardian.class, 12.0F, 0.01F));
        this.goalSelector.a(9, new PathfinderGoalRandomLookaround(this));
        this.bq.a(3);
        pathfindergoalmovetowardsrestriction.a(3);
        this.targetSelector.a(1, new PathfinderGoalNearestAttackableTarget(this, EntityLiving.class, 10, true, false, new EntitySelectorGuardianTargetHumanSquid(this)));
        this.moveController = new ControllerMoveGuardian(this);
        this.c = this.b = this.random.nextFloat();
    }

    protected void aW() {
        super.aW();
        this.getAttributeInstance(GenericAttributes.e).setValue(6.0D);
        this.getAttributeInstance(GenericAttributes.d).setValue(0.5D);
        this.getAttributeInstance(GenericAttributes.b).setValue(16.0D);
        this.getAttributeInstance(GenericAttributes.maxHealth).setValue(30.0D);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.a(nbttagcompound.getBoolean("Elder"));
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setBoolean("Elder", this.cl());
    }

    protected NavigationAbstract b(World world) {
        return new NavigationGuardian(this, world);
    }

    protected void h() {
        super.h();
        this.datawatcher.a(16, Integer.valueOf(0));
        this.datawatcher.a(17, Integer.valueOf(0));
    }

    private boolean a(int i) {
        return (this.datawatcher.getInt(16) & i) != 0;
    }

    private void a(int i, boolean flag) {
        int j = this.datawatcher.getInt(16);

        if (flag) {
            this.datawatcher.watch(16, Integer.valueOf(j | i));
        } else {
            this.datawatcher.watch(16, Integer.valueOf(j & ~i));
        }

    }

    public boolean n() {
        return this.a(2);
    }

    private void l(boolean flag) {
        this.a(2, flag);
    }

    public int ck() {
        return this.cl() ? 60 : 80;
    }

    public boolean cl() {
        return this.a(4);
    }

    public void a(boolean flag) {
        this.a(4, flag);
        if (flag) {
            this.a(1.9975F, 1.9975F);
            this.getAttributeInstance(GenericAttributes.d).setValue(0.30000001192092896D);
            this.getAttributeInstance(GenericAttributes.e).setValue(8.0D);
            this.getAttributeInstance(GenericAttributes.maxHealth).setValue(80.0D);
            this.bW();
            this.bq.b(400);
        }

    }

    private void b(int i) {
        this.datawatcher.watch(17, Integer.valueOf(i));
    }

    public boolean cn() {
        return this.datawatcher.getInt(17) != 0;
    }

    public EntityLiving co() {
        if (!this.cn()) {
            return null;
        } else if (this.world.isStatic) {
            if (this.bn != null) {
                return this.bn;
            } else {
                Entity entity = this.world.a(this.datawatcher.getInt(17));

                if (entity instanceof EntityLiving) {
                    this.bn = (EntityLiving) entity;
                    return this.bn;
                } else {
                    return null;
                }
            }
        } else {
            return this.getGoalTarget();
        }
    }

    public void i(int i) {
        super.i(i);
        if (i == 16) {
            if (this.cl() && this.width < 1.0F) {
                this.a(1.9975F, 1.9975F);
            }
        } else if (i == 17) {
            this.bo = 0;
            this.bn = null;
        }

    }

    public int w() {
        return 160;
    }

    protected String z() {
        return !this.V() ? "mob.guardian.land.idle" : (this.cl() ? "mob.guardian.elder.idle" : "mob.guardian.idle");
    }

    protected String bn() {
        return !this.V() ? "mob.guardian.land.hit" : (this.cl() ? "mob.guardian.elder.hit" : "mob.guardian.hit");
    }

    protected String bo() {
        return !this.V() ? "mob.guardian.land.death" : (this.cl() ? "mob.guardian.elder.death" : "mob.guardian.death");
    }

    protected boolean r_() {
        return false;
    }

    public float getHeadHeight() {
        return this.length * 0.5F;
    }

    public float a(BlockPosition blockposition) {
        return this.world.getType(blockposition).getBlock().getMaterial() == Material.WATER ? 10.0F + this.world.o(blockposition) - 0.5F : super.a(blockposition);
    }

    public void m() {
        if (this.world.isStatic) {
            this.c = this.b;
            if (!this.V()) {
                this.bk = 2.0F;
                if (this.motY > 0.0D && this.bp && !this.R()) {
                    this.world.a(this.locX, this.locY, this.locZ, "mob.guardian.flop", 1.0F, 1.0F, false);
                }

                this.bp = this.motY < 0.0D && this.world.d((new BlockPosition(this)).down(), false);
            } else if (this.n()) {
                if (this.bk < 0.5F) {
                    this.bk = 4.0F;
                } else {
                    this.bk += (0.5F - this.bk) * 0.1F;
                }
            } else {
                this.bk += (0.125F - this.bk) * 0.2F;
            }

            this.b += this.bk;
            this.bm = this.bl;
            if (!this.V()) {
                this.bl = this.random.nextFloat();
            } else if (this.n()) {
                this.bl += (0.0F - this.bl) * 0.25F;
            } else {
                this.bl += (1.0F - this.bl) * 0.06F;
            }

            if (this.n() && this.V()) {
                Vec3D vec3d = this.d(0.0F);

                for (int i = 0; i < 2; ++i) {
                    this.world.addParticle(EnumParticle.WATER_BUBBLE, this.locX + (this.random.nextDouble() - 0.5D) * (double) this.width - vec3d.a * 1.5D, this.locY + this.random.nextDouble() * (double) this.length - vec3d.b * 1.5D, this.locZ + (this.random.nextDouble() - 0.5D) * (double) this.width - vec3d.c * 1.5D, 0.0D, 0.0D, 0.0D, new int[0]);
                }
            }

            if (this.cn()) {
                if (this.bo < this.ck()) {
                    ++this.bo;
                }

                EntityLiving entityliving = this.co();

                if (entityliving != null) {
                    this.getControllerLook().a(entityliving, 90.0F, 90.0F);
                    this.getControllerLook().a();
                    double d0 = (double) this.p(0.0F);
                    double d1 = entityliving.locX - this.locX;
                    double d2 = entityliving.locY + (double) (entityliving.length * 0.5F) - (this.locY + (double) this.getHeadHeight());
                    double d3 = entityliving.locZ - this.locZ;
                    double d4 = Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);

                    d1 /= d4;
                    d2 /= d4;
                    d3 /= d4;
                    double d5 = this.random.nextDouble();

                    while (d5 < d4) {
                        d5 += 1.8D - d0 + this.random.nextDouble() * (1.7D - d0);
                        this.world.addParticle(EnumParticle.WATER_BUBBLE, this.locX + d1 * d5, this.locY + d2 * d5 + (double) this.getHeadHeight(), this.locZ + d3 * d5, 0.0D, 0.0D, 0.0D, new int[0]);
                    }
                }
            }
        }

        if (this.inWater) {
            this.setAirTicks(300);
        } else if (this.onGround) {
            this.motY += 0.5D;
            this.motX += (double) ((this.random.nextFloat() * 2.0F - 1.0F) * 0.4F);
            this.motZ += (double) ((this.random.nextFloat() * 2.0F - 1.0F) * 0.4F);
            this.yaw = this.random.nextFloat() * 360.0F;
            this.onGround = false;
            this.ai = true;
        }

        if (this.cn()) {
            this.yaw = this.aI;
        }

        super.m();
    }

    public float p(float f) {
        return ((float) this.bo + f) / (float) this.ck();
    }

    protected void E() {
        super.E();
        if (this.cl()) {
            boolean flag = true;
            boolean flag1 = true;
            boolean flag2 = true;
            boolean flag3 = true;

            if ((this.ticksLived + this.getId()) % 1200 == 0) {
                MobEffectList mobeffectlist = MobEffectList.SLOWER_DIG;
                List list = this.world.b(EntityPlayer.class, (Predicate) (new EntityGuardianInnerClass1(this)));
                Iterator iterator = list.iterator();

                while (iterator.hasNext()) {
                    EntityPlayer entityplayer = (EntityPlayer) iterator.next();

                    if (!entityplayer.hasEffect(mobeffectlist) || entityplayer.getEffect(mobeffectlist).getAmplifier() < 2 || entityplayer.getEffect(mobeffectlist).getDuration() < 1200) {
                        entityplayer.playerConnection.sendPacket(new PacketPlayOutGameStateChange(10, 0.0F));
                        entityplayer.addEffect(new MobEffect(mobeffectlist.id, 6000, 2));
                    }
                }
            }

            if (!this.ci()) {
                this.a(new BlockPosition(this), 16);
            }
        }

    }

    protected void dropDeathLoot(boolean flag, int i) {
        int j = this.random.nextInt(3) + this.random.nextInt(i + 1);

        if (j > 0) {
            this.a(new ItemStack(Items.PRISMARINE_SHARD, j, 0), 1.0F);
        }

        if (this.random.nextInt(3 + i) > 1) {
            this.a(new ItemStack(Items.FISH, 1, EnumFish.COD.a()), 1.0F);
        } else if (this.random.nextInt(3 + i) > 1) {
            this.a(new ItemStack(Items.PRISMARINE_CRYSTALS, 1, 0), 1.0F);
        }

        if (flag && this.cl()) {
            this.a(new ItemStack(Blocks.SPONGE, 1, 1), 1.0F);
        }

    }

    protected void getRareDrop() {
        ItemStack itemstack = ((PossibleFishingResult) WeightedRandom.a(this.random, EntityFishingHook.j())).a(this.random);

        this.a(itemstack, 1.0F);
    }

    protected boolean m_() {
        return true;
    }

    public boolean canSpawn() {
        return this.world.a(this.getBoundingBox(), (Entity) this) && this.world.getCubes(this, this.getBoundingBox()).isEmpty();
    }

    public boolean bQ() {
        return (this.random.nextInt(20) == 0 || !this.world.j(new BlockPosition(this))) && super.bQ();
    }

    public boolean damageEntity(DamageSource damagesource, float f) {
        if (!this.n() && !damagesource.isMagic() && damagesource.i() instanceof EntityLiving) {
            EntityLiving entityliving = (EntityLiving) damagesource.i();

            if (!damagesource.isExplosion()) {
                entityliving.damageEntity(DamageSource.a(this), 2.0F);
                entityliving.makeSound("damage.thorns", 0.5F, 1.0F);
            }
        }

        this.bq.f();
        return super.damageEntity(damagesource, f);
    }

    public int bP() {
        return 180;
    }

    public void g(float f, float f1) {
        if (this.bL()) {
            if (this.V()) {
                this.a(f, f1, 0.1F);
                this.move(this.motX, this.motY, this.motZ);
                this.motX *= 0.8999999761581421D;
                this.motY *= 0.8999999761581421D;
                this.motZ *= 0.8999999761581421D;
                if (!this.n() && this.getGoalTarget() == null) {
                    this.motY -= 0.005D;
                }
            } else {
                super.g(f, f1);
            }
        } else {
            super.g(f, f1);
        }

    }

    static void a(EntityGuardian entityguardian, int i) {
        entityguardian.b(i);
    }

    static PathfinderGoalRandomStroll a(EntityGuardian entityguardian) {
        return entityguardian.bq;
    }

    static void a(EntityGuardian entityguardian, boolean flag) {
        entityguardian.l(flag);
    }
}
