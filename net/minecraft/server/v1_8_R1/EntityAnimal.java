package net.minecraft.server;

public abstract class EntityAnimal extends EntityAgeable implements IAnimal {

    protected Block bl;
    private int bk;
    private EntityHuman bm;

    public EntityAnimal(World world) {
        super(world);
        this.bl = Blocks.GRASS;
    }

    protected void E() {
        if (this.getAge() != 0) {
            this.bk = 0;
        }

        super.E();
    }

    public void m() {
        super.m();
        if (this.getAge() != 0) {
            this.bk = 0;
        }

        if (this.bk > 0) {
            --this.bk;
            if (this.bk % 10 == 0) {
                double d0 = this.random.nextGaussian() * 0.02D;
                double d1 = this.random.nextGaussian() * 0.02D;
                double d2 = this.random.nextGaussian() * 0.02D;

                this.world.addParticle(EnumParticle.HEART, this.locX + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, this.locY + 0.5D + (double) (this.random.nextFloat() * this.length), this.locZ + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, d0, d1, d2, new int[0]);
            }
        }

    }

    public boolean damageEntity(DamageSource damagesource, float f) {
        if (this.isInvulnerable(damagesource)) {
            return false;
        } else {
            this.bk = 0;
            return super.damageEntity(damagesource, f);
        }
    }

    public float a(BlockPosition blockposition) {
        return this.world.getType(blockposition.down()).getBlock() == Blocks.GRASS ? 10.0F : this.world.o(blockposition) - 0.5F;
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setInt("InLove", this.bk);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.bk = nbttagcompound.getInt("InLove");
    }

    public boolean bQ() {
        int i = MathHelper.floor(this.locX);
        int j = MathHelper.floor(this.getBoundingBox().b);
        int k = MathHelper.floor(this.locZ);
        BlockPosition blockposition = new BlockPosition(i, j, k);

        return this.world.getType(blockposition.down()).getBlock() == this.bl && this.world.k(blockposition) > 8 && super.bQ();
    }

    public int w() {
        return 120;
    }

    protected boolean isTypeNotPersistent() {
        return false;
    }

    protected int getExpValue(EntityHuman entityhuman) {
        return 1 + this.world.random.nextInt(3);
    }

    public boolean d(ItemStack itemstack) {
        return itemstack == null ? false : itemstack.getItem() == Items.WHEAT;
    }

    public boolean a(EntityHuman entityhuman) {
        ItemStack itemstack = entityhuman.inventory.getItemInHand();

        if (itemstack != null) {
            if (this.d(itemstack) && this.getAge() == 0 && this.bk <= 0) {
                this.a(entityhuman, itemstack);
                this.c(entityhuman);
                return true;
            }

            if (this.isBaby() && this.d(itemstack)) {
                this.a(entityhuman, itemstack);
                this.setAge((int) ((float) (-this.getAge() / 20) * 0.1F), true);
                return true;
            }
        }

        return super.a(entityhuman);
    }

    protected void a(EntityHuman entityhuman, ItemStack itemstack) {
        if (!entityhuman.abilities.canInstantlyBuild) {
            --itemstack.count;
            if (itemstack.count <= 0) {
                entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, (ItemStack) null);
            }
        }

    }

    public void c(EntityHuman entityhuman) {
        this.bk = 600;
        this.bm = entityhuman;
        this.world.broadcastEntityEffect(this, (byte) 18);
    }

    public EntityHuman co() {
        return this.bm;
    }

    public boolean cp() {
        return this.bk > 0;
    }

    public void cq() {
        this.bk = 0;
    }

    public boolean mate(EntityAnimal entityanimal) {
        return entityanimal == this ? false : (entityanimal.getClass() != this.getClass() ? false : this.cp() && entityanimal.cp());
    }
}
