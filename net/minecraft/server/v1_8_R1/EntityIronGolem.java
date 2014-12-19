package net.minecraft.server;

public class EntityIronGolem extends EntityGolem {

    private int b;
    Village a;
    private int c;
    private int bk;

    public EntityIronGolem(World world) {
        super(world);
        this.a(1.4F, 2.9F);
        ((Navigation) this.getNavigation()).a(true);
        this.goalSelector.a(1, new PathfinderGoalMeleeAttack(this, 1.0D, true));
        this.goalSelector.a(2, new PathfinderGoalMoveTowardsTarget(this, 0.9D, 32.0F));
        this.goalSelector.a(3, new PathfinderGoalMoveThroughVillage(this, 0.6D, true));
        this.goalSelector.a(4, new PathfinderGoalMoveTowardsRestriction(this, 1.0D));
        this.goalSelector.a(5, new PathfinderGoalOfferFlower(this));
        this.goalSelector.a(6, new PathfinderGoalRandomStroll(this, 0.6D));
        this.goalSelector.a(7, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 6.0F));
        this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
        this.targetSelector.a(1, new PathfinderGoalDefendVillage(this));
        this.targetSelector.a(2, new PathfinderGoalHurtByTarget(this, false, new Class[0]));
        this.targetSelector.a(3, new PathfinderGoalNearestGolemTarget(this, EntityInsentient.class, 10, false, true, IMonster.e));
    }

    protected void h() {
        super.h();
        this.datawatcher.a(16, Byte.valueOf((byte) 0));
    }

    protected void E() {
        if (--this.b <= 0) {
            this.b = 70 + this.random.nextInt(50);
            this.a = this.world.ae().getClosestVillage(new BlockPosition(this), 32);
            if (this.a == null) {
                this.ch();
            } else {
                BlockPosition blockposition = this.a.a();

                this.a(blockposition, (int) ((float) this.a.b() * 0.6F));
            }
        }

        super.E();
    }

    protected void aW() {
        super.aW();
        this.getAttributeInstance(GenericAttributes.maxHealth).setValue(100.0D);
        this.getAttributeInstance(GenericAttributes.d).setValue(0.25D);
    }

    protected int j(int i) {
        return i;
    }

    protected void s(Entity entity) {
        if (entity instanceof IMonster && this.bb().nextInt(20) == 0) {
            this.setGoalTarget((EntityLiving) entity, org.bukkit.event.entity.EntityTargetLivingEntityEvent.TargetReason.COLLISION, true); // CraftBukkit - set reason
        }

        super.s(entity);
    }

    public void m() {
        super.m();
        if (this.c > 0) {
            --this.c;
        }

        if (this.bk > 0) {
            --this.bk;
        }

        if (this.motX * this.motX + this.motZ * this.motZ > 2.500000277905201E-7D && this.random.nextInt(5) == 0) {
            int i = MathHelper.floor(this.locX);
            int j = MathHelper.floor(this.locY - 0.20000000298023224D);
            int k = MathHelper.floor(this.locZ);
            IBlockData iblockdata = this.world.getType(new BlockPosition(i, j, k));
            Block block = iblockdata.getBlock();

            if (block.getMaterial() != Material.AIR) {
                this.world.addParticle(EnumParticle.BLOCK_CRACK, this.locX + ((double) this.random.nextFloat() - 0.5D) * (double) this.width, this.getBoundingBox().b + 0.1D, this.locZ + ((double) this.random.nextFloat() - 0.5D) * (double) this.width, 4.0D * ((double) this.random.nextFloat() - 0.5D), 0.5D, ((double) this.random.nextFloat() - 0.5D) * 4.0D, new int[] { Block.getCombinedId(iblockdata)});
            }
        }

    }

    public boolean a(Class oclass) {
        return this.isPlayerCreated() && EntityHuman.class.isAssignableFrom(oclass) ? false : super.a(oclass);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setBoolean("PlayerCreated", this.isPlayerCreated());
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.setPlayerCreated(nbttagcompound.getBoolean("PlayerCreated"));
    }

    public boolean r(Entity entity) {
        this.c = 10;
        this.world.broadcastEntityEffect(this, (byte) 4);
        boolean flag = entity.damageEntity(DamageSource.mobAttack(this), (float) (7 + this.random.nextInt(15)));

        if (flag) {
            entity.motY += 0.4000000059604645D;
            this.a((EntityLiving) this, entity);
        }

        this.makeSound("mob.irongolem.throw", 1.0F, 1.0F);
        return flag;
    }

    public Village n() {
        return this.a;
    }

    public void a(boolean flag) {
        this.bk = flag ? 400 : 0;
        this.world.broadcastEntityEffect(this, (byte) 11);
    }

    protected String bn() {
        return "mob.irongolem.hit";
    }

    protected String bo() {
        return "mob.irongolem.death";
    }

    protected void a(BlockPosition blockposition, Block block) {
        this.makeSound("mob.irongolem.walk", 1.0F, 1.0F);
    }

    protected void dropDeathLoot(boolean flag, int i) {
        int j = this.random.nextInt(3);

        int k;

        for (k = 0; k < j; ++k) {
            this.a(Item.getItemOf(Blocks.RED_FLOWER), 1, (float) EnumFlowerVarient.POPPY.b());
        }

        k = 3 + this.random.nextInt(3);

        for (int l = 0; l < k; ++l) {
            this.a(Items.IRON_INGOT, 1);
        }

    }

    public int ck() {
        return this.bk;
    }

    public boolean isPlayerCreated() {
        return (this.datawatcher.getByte(16) & 1) != 0;
    }

    public void setPlayerCreated(boolean flag) {
        byte b0 = this.datawatcher.getByte(16);

        if (flag) {
            this.datawatcher.watch(16, Byte.valueOf((byte) (b0 | 1)));
        } else {
            this.datawatcher.watch(16, Byte.valueOf((byte) (b0 & -2)));
        }

    }

    public void die(DamageSource damagesource) {
        if (!this.isPlayerCreated() && this.killer != null && this.a != null) {
            this.a.a(this.killer.getName(), -5);
        }

        super.die(damagesource);
    }
}
