package net.minecraft.server;

public class EntityRabbit extends EntityAnimal {

    private PathfinderGoalRabbitAvoidTarget bk;
    private int bm = 0;
    private int bn = 0;
    private boolean bo = false;
    private boolean bp = false;
    private int bq = 0;
    private EnumRabbitState br;
    private int bs;
    private EntityHuman bt;

    public EntityRabbit(World world) {
        super(world);
        this.br = EnumRabbitState.HOP;
        this.bs = 0;
        this.bt = null;
        this.a(0.6F, 0.7F);
        this.g = new ControllerJumpRabbit(this, this);
        this.moveController = new ControllerMoveRabbit(this);
        ((Navigation) this.getNavigation()).a(true);
        this.navigation.a(2.5F);
        this.goalSelector.a(1, new PathfinderGoalFloat(this));
        this.goalSelector.a(1, new PathfinderGoalRabbitPanic(this, 1.33D));
        this.goalSelector.a(2, new PathfinderGoalTempt(this, 1.0D, Items.CARROT, false));
        this.goalSelector.a(3, new PathfinderGoalBreed(this, 0.8D));
        this.goalSelector.a(5, new PathfinderGoalEatCarrots(this));
        this.goalSelector.a(5, new PathfinderGoalRandomStroll(this, 0.6D));
        this.goalSelector.a(11, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 10.0F));
        this.bk = new PathfinderGoalRabbitAvoidTarget(this, new EntitySelectorRabbitWolf(this), 16.0F, 1.33D, 1.33D);
        this.goalSelector.a(4, this.bk);
        this.b(0.0D);
    }

    protected float bD() {
        return this.moveController.a() && this.moveController.e() > this.locY + 0.5D ? 0.5F : this.br.b();
    }

    public void a(EnumRabbitState enumrabbitstate) {
        this.br = enumrabbitstate;
    }

    public void b(double d0) {
        this.getNavigation().a(d0);
        this.moveController.a(this.moveController.d(), this.moveController.e(), this.moveController.f(), d0);
    }

    public void a(boolean flag, EnumRabbitState enumrabbitstate) {
        super.i(flag);
        if (!flag) {
            if (this.br == EnumRabbitState.ATTACK) {
                this.br = EnumRabbitState.HOP;
            }
        } else {
            this.b(1.5D * (double) enumrabbitstate.a());
            this.makeSound(this.ck(), this.bA(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * 0.8F);
        }

        this.bo = flag;
    }

    public void b(EnumRabbitState enumrabbitstate) {
        this.a(true, enumrabbitstate);
        this.bn = enumrabbitstate.d();
        this.bm = 0;
    }

    public boolean cj() {
        return this.bo;
    }

    protected void h() {
        super.h();
        this.datawatcher.a(18, Byte.valueOf((byte) 0));
    }

    public void E() {
        if (this.moveController.b() > 0.8D) {
            this.a(EnumRabbitState.SPRINT);
        } else if (this.br != EnumRabbitState.ATTACK) {
            this.a(EnumRabbitState.HOP);
        }

        if (this.bq > 0) {
            --this.bq;
        }

        if (this.bs > 0) {
            this.bs -= this.random.nextInt(3);
            if (this.bs < 0) {
                this.bs = 0;
            }
        }

        if (this.onGround) {
            if (!this.bp) {
                this.a(false, EnumRabbitState.NONE);
                this.cu();
            }

            if (this.cl() == 99 && this.bq == 0) {
                EntityLiving entityliving = this.getGoalTarget();

                if (entityliving != null && this.h(entityliving) < 16.0D) {
                    this.a(entityliving.locX, entityliving.locZ);
                    this.moveController.a(entityliving.locX, entityliving.locY, entityliving.locZ, this.moveController.b());
                    this.b(EnumRabbitState.ATTACK);
                    this.bp = true;
                }
            }

            ControllerJumpRabbit controllerjumprabbit = (ControllerJumpRabbit) this.g;

            if (!controllerjumprabbit.c()) {
                if (this.moveController.a() && this.bq == 0) {
                    PathEntity pathentity = this.navigation.j();
                    Vec3D vec3d = new Vec3D(this.moveController.d(), this.moveController.e(), this.moveController.f());

                    if (pathentity != null && pathentity.e() < pathentity.d()) {
                        vec3d = pathentity.a((Entity) this);
                    }

                    this.a(vec3d.a, vec3d.c);
                    this.b(this.br);
                }
            } else if (!controllerjumprabbit.d()) {
                this.cr();
            }
        }

        this.bp = this.onGround;
    }

    public void Y() {}

    private void a(double d0, double d1) {
        this.yaw = (float) (Math.atan2(d1 - this.locZ, d0 - this.locX) * 180.0D / 3.1415927410125732D) - 90.0F;
    }

    private void cr() {
        ((ControllerJumpRabbit) this.g).a(true);
    }

    private void cs() {
        ((ControllerJumpRabbit) this.g).a(false);
    }

    private void ct() {
        this.bq = this.cm();
    }

    private void cu() {
        this.ct();
        this.cs();
    }

    public void m() {
        super.m();
        if (this.bm != this.bn) {
            if (this.bm == 0 && !this.world.isStatic) {
                this.world.broadcastEntityEffect(this, (byte) 1);
            }

            ++this.bm;
        } else if (this.bn != 0) {
            this.bm = 0;
            this.bn = 0;
        }

    }

    protected void aW() {
        super.aW();
        this.getAttributeInstance(GenericAttributes.maxHealth).setValue(10.0D);
        this.getAttributeInstance(GenericAttributes.d).setValue(0.30000001192092896D);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setInt("RabbitType", this.cl());
        nbttagcompound.setInt("MoreCarrotTicks", this.bs);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.r(nbttagcompound.getInt("RabbitType"));
        this.bs = nbttagcompound.getInt("MoreCarrotTicks");
    }

    protected String ck() {
        return "mob.rabbit.hop";
    }

    protected String z() {
        return "mob.rabbit.idle";
    }

    protected String bn() {
        return "mob.rabbit.hurt";
    }

    protected String bo() {
        return "mob.rabbit.death";
    }

    public boolean r(Entity entity) {
        if (this.cl() == 99) {
            this.makeSound("mob.attack", 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            return entity.damageEntity(DamageSource.mobAttack(this), 8.0F);
        } else {
            return entity.damageEntity(DamageSource.mobAttack(this), 3.0F);
        }
    }

    public int bq() {
        return this.cl() == 99 ? 8 : super.bq();
    }

    public boolean damageEntity(DamageSource damagesource, float f) {
        return this.isInvulnerable(damagesource) ? false : super.damageEntity(damagesource, f);
    }

    protected void getRareDrop() {
        this.a(new ItemStack(Items.RABBIT_FOOT, 1), 0.0F);
    }

    protected void dropDeathLoot(boolean flag, int i) {
        int j = this.random.nextInt(2) + this.random.nextInt(1 + i);

        int k;

        for (k = 0; k < j; ++k) {
            this.a(Items.RABBIT_HIDE, 1);
        }

        j = this.random.nextInt(2);

        for (k = 0; k < j; ++k) {
            if (this.isBurning()) {
                this.a(Items.COOKED_RABBIT, 1);
            } else {
                this.a(Items.RABBIT, 1);
            }
        }

    }

    private boolean a(Item item) {
        return item == Items.CARROT || item == Items.GOLDEN_CARROT || item == Item.getItemOf(Blocks.YELLOW_FLOWER);
    }

    public EntityRabbit b(EntityAgeable entityageable) {
        EntityRabbit entityrabbit = new EntityRabbit(this.world);

        if (entityageable instanceof EntityRabbit) {
            entityrabbit.r(this.random.nextBoolean() ? this.cl() : ((EntityRabbit) entityageable).cl());
        }

        return entityrabbit;
    }

    public boolean d(ItemStack itemstack) {
        return itemstack != null && this.a(itemstack.getItem());
    }

    public int cl() {
        return this.datawatcher.getByte(18);
    }

    public void r(int i) {
        if (i == 99) {
            this.goalSelector.a((PathfinderGoal) this.bk);
            this.goalSelector.a(4, new PathfinderGoalKillerRabbitMeleeAttack(this));
            this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, false, new Class[0]));
            this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
            this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityWolf.class, true));
            if (!this.hasCustomName()) {
                this.setCustomName(LocaleI18n.get("entity.KillerBunny.name"));
            }
        }

        this.datawatcher.watch(18, Byte.valueOf((byte) i));
    }

    public GroupDataEntity prepare(DifficultyDamageScaler difficultydamagescaler, GroupDataEntity groupdataentity) {
        Object object = super.prepare(difficultydamagescaler, groupdataentity);
        int i = this.random.nextInt(6);
        boolean flag = false;

        if (object instanceof GroupDataRabbit) {
            i = ((GroupDataRabbit) object).a;
            flag = true;
        } else {
            object = new GroupDataRabbit(i);
        }

        this.r(i);
        if (flag) {
            this.setAgeRaw(-24000);
        }

        return (GroupDataEntity) object;
    }

    private boolean cv() {
        return this.bs == 0;
    }

    protected int cm() {
        return this.br.c();
    }

    protected void cn() {
        this.world.addParticle(EnumParticle.BLOCK_DUST, this.locX + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, this.locY + 0.5D + (double) (this.random.nextFloat() * this.length), this.locZ + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, 0.0D, 0.0D, 0.0D, new int[] { Block.getCombinedId(Blocks.CARROTS.fromLegacyData(7))});
        this.bs = 100;
    }

    public EntityAgeable createChild(EntityAgeable entityageable) {
        return this.b(entityageable);
    }

    static boolean a(EntityRabbit entityrabbit) {
        return entityrabbit.cv();
    }
}
