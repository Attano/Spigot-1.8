package net.minecraft.server;

import java.util.Iterator;
import org.bukkit.craftbukkit.entity.CraftVillager;

public class EntityVillager extends EntityAgeable implements NPC, IMerchant {

    private int profession;
    private boolean bm;
    private boolean bn;
    Village village;
    private EntityHuman tradingPlayer;
    private MerchantRecipeList bp;
    private int bq;
    private boolean br;
    private boolean bs;
    private int riches;
    private String bu;
    private int bv;
    private int bw;
    private boolean bx;
    private boolean by;
    public InventorySubcontainer inventory;
    private static final IMerchantRecipeOption[][][][] bA = new IMerchantRecipeOption[][][][] { { { { new MerchantRecipeOptionBuy(Items.WHEAT, new MerchantOptionRandomRange(18, 22)), new MerchantRecipeOptionBuy(Items.POTATO, new MerchantOptionRandomRange(15, 19)), new MerchantRecipeOptionBuy(Items.CARROT, new MerchantOptionRandomRange(15, 19)), new MerchantRecipeOptionSell(Items.BREAD, new MerchantOptionRandomRange(-4, -2))}, { new MerchantRecipeOptionBuy(Item.getItemOf(Blocks.PUMPKIN), new MerchantOptionRandomRange(8, 13)), new MerchantRecipeOptionSell(Items.PUMPKIN_PIE, new MerchantOptionRandomRange(-3, -2))}, { new MerchantRecipeOptionBuy(Item.getItemOf(Blocks.MELON_BLOCK), new MerchantOptionRandomRange(7, 12)), new MerchantRecipeOptionSell(Items.APPLE, new MerchantOptionRandomRange(-5, -7))}, { new MerchantRecipeOptionSell(Items.COOKIE, new MerchantOptionRandomRange(-6, -10)), new MerchantRecipeOptionSell(Items.CAKE, new MerchantOptionRandomRange(1, 1))}}, { { new MerchantRecipeOptionBuy(Items.STRING, new MerchantOptionRandomRange(15, 20)), new MerchantRecipeOptionBuy(Items.COAL, new MerchantOptionRandomRange(16, 24)), new MerchantRecipeOptionProcess(Items.FISH, new MerchantOptionRandomRange(6, 6), Items.COOKED_FISH, new MerchantOptionRandomRange(6, 6))}, { new MerchantRecipeOptionEnchant(Items.FISHING_ROD, new MerchantOptionRandomRange(7, 8))}}, { { new MerchantRecipeOptionBuy(Item.getItemOf(Blocks.WOOL), new MerchantOptionRandomRange(16, 22)), new MerchantRecipeOptionSell(Items.SHEARS, new MerchantOptionRandomRange(3, 4))}, { new MerchantRecipeOptionSell(new ItemStack(Item.getItemOf(Blocks.WOOL), 1, 0), new MerchantOptionRandomRange(1, 2)), new MerchantRecipeOptionSell(new ItemStack(Item.getItemOf(Blocks.WOOL), 1, 1), new MerchantOptionRandomRange(1, 2)), new MerchantRecipeOptionSell(new ItemStack(Item.getItemOf(Blocks.WOOL), 1, 2), new MerchantOptionRandomRange(1, 2)), new MerchantRecipeOptionSell(new ItemStack(Item.getItemOf(Blocks.WOOL), 1, 3), new MerchantOptionRandomRange(1, 2)), new MerchantRecipeOptionSell(new ItemStack(Item.getItemOf(Blocks.WOOL), 1, 4), new MerchantOptionRandomRange(1, 2)), new MerchantRecipeOptionSell(new ItemStack(Item.getItemOf(Blocks.WOOL), 1, 5), new MerchantOptionRandomRange(1, 2)), new MerchantRecipeOptionSell(new ItemStack(Item.getItemOf(Blocks.WOOL), 1, 6), new MerchantOptionRandomRange(1, 2)), new MerchantRecipeOptionSell(new ItemStack(Item.getItemOf(Blocks.WOOL), 1, 7), new MerchantOptionRandomRange(1, 2)), new MerchantRecipeOptionSell(new ItemStack(Item.getItemOf(Blocks.WOOL), 1, 8), new MerchantOptionRandomRange(1, 2)), new MerchantRecipeOptionSell(new ItemStack(Item.getItemOf(Blocks.WOOL), 1, 9), new MerchantOptionRandomRange(1, 2)), new MerchantRecipeOptionSell(new ItemStack(Item.getItemOf(Blocks.WOOL), 1, 10), new MerchantOptionRandomRange(1, 2)), new MerchantRecipeOptionSell(new ItemStack(Item.getItemOf(Blocks.WOOL), 1, 11), new MerchantOptionRandomRange(1, 2)), new MerchantRecipeOptionSell(new ItemStack(Item.getItemOf(Blocks.WOOL), 1, 12), new MerchantOptionRandomRange(1, 2)), new MerchantRecipeOptionSell(new ItemStack(Item.getItemOf(Blocks.WOOL), 1, 13), new MerchantOptionRandomRange(1, 2)), new MerchantRecipeOptionSell(new ItemStack(Item.getItemOf(Blocks.WOOL), 1, 14), new MerchantOptionRandomRange(1, 2)), new MerchantRecipeOptionSell(new ItemStack(Item.getItemOf(Blocks.WOOL), 1, 15), new MerchantOptionRandomRange(1, 2))}}, { { new MerchantRecipeOptionBuy(Items.STRING, new MerchantOptionRandomRange(15, 20)), new MerchantRecipeOptionSell(Items.ARROW, new MerchantOptionRandomRange(-12, -8))}, { new MerchantRecipeOptionSell(Items.BOW, new MerchantOptionRandomRange(2, 3)), new MerchantRecipeOptionProcess(Item.getItemOf(Blocks.GRAVEL), new MerchantOptionRandomRange(10, 10), Items.FLINT, new MerchantOptionRandomRange(6, 10))}}}, { { { new MerchantRecipeOptionBuy(Items.PAPER, new MerchantOptionRandomRange(24, 36)), new MerchantRecipeOptionBook()}, { new MerchantRecipeOptionBuy(Items.BOOK, new MerchantOptionRandomRange(8, 10)), new MerchantRecipeOptionSell(Items.COMPASS, new MerchantOptionRandomRange(10, 12)), new MerchantRecipeOptionSell(Item.getItemOf(Blocks.BOOKSHELF), new MerchantOptionRandomRange(3, 4))}, { new MerchantRecipeOptionBuy(Items.WRITTEN_BOOK, new MerchantOptionRandomRange(2, 2)), new MerchantRecipeOptionSell(Items.CLOCK, new MerchantOptionRandomRange(10, 12)), new MerchantRecipeOptionSell(Item.getItemOf(Blocks.GLASS), new MerchantOptionRandomRange(-5, -3))}, { new MerchantRecipeOptionBook()}, { new MerchantRecipeOptionBook()}, { new MerchantRecipeOptionSell(Items.NAME_TAG, new MerchantOptionRandomRange(20, 22))}}}, { { { new MerchantRecipeOptionBuy(Items.ROTTEN_FLESH, new MerchantOptionRandomRange(36, 40)), new MerchantRecipeOptionBuy(Items.GOLD_INGOT, new MerchantOptionRandomRange(8, 10))}, { new MerchantRecipeOptionSell(Items.REDSTONE, new MerchantOptionRandomRange(-4, -1)), new MerchantRecipeOptionSell(new ItemStack(Items.DYE, 1, EnumColor.BLUE.getInvColorIndex()), new MerchantOptionRandomRange(-2, -1))}, { new MerchantRecipeOptionSell(Items.ENDER_EYE, new MerchantOptionRandomRange(7, 11)), new MerchantRecipeOptionSell(Item.getItemOf(Blocks.GLOWSTONE), new MerchantOptionRandomRange(-3, -1))}, { new MerchantRecipeOptionSell(Items.EXPERIENCE_BOTTLE, new MerchantOptionRandomRange(3, 11))}}}, { { { new MerchantRecipeOptionBuy(Items.COAL, new MerchantOptionRandomRange(16, 24)), new MerchantRecipeOptionSell(Items.IRON_HELMET, new MerchantOptionRandomRange(4, 6))}, { new MerchantRecipeOptionBuy(Items.IRON_INGOT, new MerchantOptionRandomRange(7, 9)), new MerchantRecipeOptionSell(Items.IRON_CHESTPLATE, new MerchantOptionRandomRange(10, 14))}, { new MerchantRecipeOptionBuy(Items.DIAMOND, new MerchantOptionRandomRange(3, 4)), new MerchantRecipeOptionEnchant(Items.DIAMOND_CHESTPLATE, new MerchantOptionRandomRange(16, 19))}, { new MerchantRecipeOptionSell(Items.CHAINMAIL_BOOTS, new MerchantOptionRandomRange(5, 7)), new MerchantRecipeOptionSell(Items.CHAINMAIL_LEGGINGS, new MerchantOptionRandomRange(9, 11)), new MerchantRecipeOptionSell(Items.CHAINMAIL_HELMET, new MerchantOptionRandomRange(5, 7)), new MerchantRecipeOptionSell(Items.CHAINMAIL_CHESTPLATE, new MerchantOptionRandomRange(11, 15))}}, { { new MerchantRecipeOptionBuy(Items.COAL, new MerchantOptionRandomRange(16, 24)), new MerchantRecipeOptionSell(Items.IRON_AXE, new MerchantOptionRandomRange(6, 8))}, { new MerchantRecipeOptionBuy(Items.IRON_INGOT, new MerchantOptionRandomRange(7, 9)), new MerchantRecipeOptionEnchant(Items.IRON_SWORD, new MerchantOptionRandomRange(9, 10))}, { new MerchantRecipeOptionBuy(Items.DIAMOND, new MerchantOptionRandomRange(3, 4)), new MerchantRecipeOptionEnchant(Items.DIAMOND_SWORD, new MerchantOptionRandomRange(12, 15)), new MerchantRecipeOptionEnchant(Items.DIAMOND_AXE, new MerchantOptionRandomRange(9, 12))}}, { { new MerchantRecipeOptionBuy(Items.COAL, new MerchantOptionRandomRange(16, 24)), new MerchantRecipeOptionEnchant(Items.IRON_SHOVEL, new MerchantOptionRandomRange(5, 7))}, { new MerchantRecipeOptionBuy(Items.IRON_INGOT, new MerchantOptionRandomRange(7, 9)), new MerchantRecipeOptionEnchant(Items.IRON_PICKAXE, new MerchantOptionRandomRange(9, 11))}, { new MerchantRecipeOptionBuy(Items.DIAMOND, new MerchantOptionRandomRange(3, 4)), new MerchantRecipeOptionEnchant(Items.DIAMOND_PICKAXE, new MerchantOptionRandomRange(12, 15))}}}, { { { new MerchantRecipeOptionBuy(Items.PORKCHOP, new MerchantOptionRandomRange(14, 18)), new MerchantRecipeOptionBuy(Items.CHICKEN, new MerchantOptionRandomRange(14, 18))}, { new MerchantRecipeOptionBuy(Items.COAL, new MerchantOptionRandomRange(16, 24)), new MerchantRecipeOptionSell(Items.COOKED_PORKCHOP, new MerchantOptionRandomRange(-7, -5)), new MerchantRecipeOptionSell(Items.COOKED_CHICKEN, new MerchantOptionRandomRange(-8, -6))}}, { { new MerchantRecipeOptionBuy(Items.LEATHER, new MerchantOptionRandomRange(9, 12)), new MerchantRecipeOptionSell(Items.LEATHER_LEGGINGS, new MerchantOptionRandomRange(2, 4))}, { new MerchantRecipeOptionEnchant(Items.LEATHER_CHESTPLATE, new MerchantOptionRandomRange(7, 12))}, { new MerchantRecipeOptionSell(Items.SADDLE, new MerchantOptionRandomRange(8, 10))}}}};

    public EntityVillager(World world) {
        this(world, 0);
    }

    public EntityVillager(World world, int i) {
        super(world);
        this.inventory = new InventorySubcontainer("Items", false, 8, (CraftVillager) this.getBukkitEntity()); // CraftBukkit add argument
        this.setProfession(i);
        this.a(0.6F, 1.8F);
        ((Navigation) this.getNavigation()).b(true);
        ((Navigation) this.getNavigation()).a(true);
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(1, new PathfinderGoalAvoidTarget(this, new EntityVillagerInnerClass1(this), 8.0F, 0.6D, 0.6D));
        this.goalSelector.a(1, new PathfinderGoalTradeWithPlayer(this));
        this.goalSelector.a(1, new PathfinderGoalLookAtTradingPlayer(this));
        this.goalSelector.a(2, new PathfinderGoalMoveIndoors(this));
        this.goalSelector.a(3, new PathfinderGoalRestrictOpenDoor(this));
        this.goalSelector.a(4, new PathfinderGoalOpenDoor(this, true));
        this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 0.6D));
        this.goalSelector.a(6, new PathfinderGoalMakeLove(this));
        this.goalSelector.a(7, new PathfinderGoalTakeFlower(this));
        this.goalSelector.a(9, new PathfinderGoalInteract(this, EntityHuman.class, 3.0F, 1.0F));
        this.goalSelector.a(9, new PathfinderGoalInteractVillagers(this));
        this.goalSelector.a(9, new PathfinderGoalRandomStroll(this, 0.6D));
        this.goalSelector.a(10, new PathfinderGoalLookAtPlayer(this, EntityInsentient.class, 8.0F));
        this.j(true);
    }

    private void ct() {
        if (!this.by) {
            this.by = true;
            if (this.isBaby()) {
                this.goalSelector.a(8, new PathfinderGoalPlay(this, 0.32D));
            } else if (this.getProfession() == 0) {
                this.goalSelector.a(6, new PathfinderGoalVillagerFarm(this, 0.6D));
            }

        }
    }

    protected void n() {
        if (this.getProfession() == 0) {
            this.goalSelector.a(8, new PathfinderGoalVillagerFarm(this, 0.6D));
        }

        super.n();
    }

    protected void aW() {
        super.aW();
        this.getAttributeInstance(GenericAttributes.d).setValue(0.5D);
    }

    protected void E() {
        if (--this.profession <= 0) {
            BlockPosition blockposition = new BlockPosition(this);

            this.world.ae().a(blockposition);
            this.profession = 70 + this.random.nextInt(50);
            this.village = this.world.ae().getClosestVillage(blockposition, 32);
            if (this.village == null) {
                this.ch();
            } else {
                BlockPosition blockposition1 = this.village.a();

                this.a(blockposition1, (int) ((float) this.village.b() * 1.0F));
                if (this.bx) {
                    this.bx = false;
                    this.village.b(5);
                }
            }
        }

        if (!this.cm() && this.bq > 0) {
            --this.bq;
            if (this.bq <= 0) {
                if (this.br) {
                    Iterator iterator = this.bp.iterator();

                    while (iterator.hasNext()) {
                        MerchantRecipe merchantrecipe = (MerchantRecipe) iterator.next();

                        if (merchantrecipe.h()) {
                            merchantrecipe.a(this.random.nextInt(6) + this.random.nextInt(6) + 2);
                        }
                    }

                    this.cu();
                    this.br = false;
                    if (this.village != null && this.bu != null) {
                        this.world.broadcastEntityEffect(this, (byte) 14);
                        this.village.a(this.bu, 1);
                    }
                }

                this.addEffect(new MobEffect(MobEffectList.REGENERATION.id, 200, 0));
            }
        }

        super.E();
    }

    public boolean a(EntityHuman entityhuman) {
        ItemStack itemstack = entityhuman.inventory.getItemInHand();
        boolean flag = itemstack != null && itemstack.getItem() == Items.SPAWN_EGG;

        if (!flag && this.isAlive() && !this.cm() && !this.isBaby()) {
            if (!this.world.isStatic && (this.bp == null || this.bp.size() > 0)) {
                this.a_(entityhuman);
                entityhuman.openTrade(this);
            }

            entityhuman.b(StatisticList.F);
            return true;
        } else {
            return super.a(entityhuman);
        }
    }

    protected void h() {
        super.h();
        this.datawatcher.a(16, Integer.valueOf(0));
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setInt("Profession", this.getProfession());
        nbttagcompound.setInt("Riches", this.riches);
        nbttagcompound.setInt("Career", this.bv);
        nbttagcompound.setInt("CareerLevel", this.bw);
        nbttagcompound.setBoolean("Willing", this.bs);
        if (this.bp != null) {
            nbttagcompound.set("Offers", this.bp.a());
        }

        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.inventory.getSize(); ++i) {
            ItemStack itemstack = this.inventory.getItem(i);

            if (itemstack != null) {
                nbttaglist.add(itemstack.save(new NBTTagCompound()));
            }
        }

        nbttagcompound.set("Inventory", nbttaglist);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.setProfession(nbttagcompound.getInt("Profession"));
        this.riches = nbttagcompound.getInt("Riches");
        this.bv = nbttagcompound.getInt("Career");
        this.bw = nbttagcompound.getInt("CareerLevel");
        this.bs = nbttagcompound.getBoolean("Willing");
        if (nbttagcompound.hasKeyOfType("Offers", 10)) {
            NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Offers");

            this.bp = new MerchantRecipeList(nbttagcompound1);
        }

        NBTTagList nbttaglist = nbttagcompound.getList("Inventory", 10);

        for (int i = 0; i < nbttaglist.size(); ++i) {
            ItemStack itemstack = ItemStack.createStack(nbttaglist.get(i));

            if (itemstack != null) {
                this.inventory.a(itemstack);
            }
        }

        this.j(true);
        this.ct();
    }

    protected boolean isTypeNotPersistent() {
        return false;
    }

    protected String z() {
        return this.cm() ? "mob.villager.haggle" : "mob.villager.idle";
    }

    protected String bn() {
        return "mob.villager.hit";
    }

    protected String bo() {
        return "mob.villager.death";
    }

    public void setProfession(int i) {
        this.datawatcher.watch(16, Integer.valueOf(i));
    }

    public int getProfession() {
        return Math.max(this.datawatcher.getInt(16) % 5, 0);
    }

    public boolean ck() {
        return this.bm;
    }

    public void l(boolean flag) {
        this.bm = flag;
    }

    public void m(boolean flag) {
        this.bn = flag;
    }

    public boolean cl() {
        return this.bn;
    }

    public void b(EntityLiving entityliving) {
        super.b(entityliving);
        if (this.village != null && entityliving != null) {
            this.village.a(entityliving);
            if (entityliving instanceof EntityHuman) {
                byte b0 = -1;

                if (this.isBaby()) {
                    b0 = -3;
                }

                this.village.a(entityliving.getName(), b0);
                if (this.isAlive()) {
                    this.world.broadcastEntityEffect(this, (byte) 13);
                }
            }
        }

    }

    public void die(DamageSource damagesource) {
        if (this.village != null) {
            Entity entity = damagesource.getEntity();

            if (entity != null) {
                if (entity instanceof EntityHuman) {
                    this.village.a(entity.getName(), -2);
                } else if (entity instanceof IMonster) {
                    this.village.h();
                }
            } else {
                EntityHuman entityhuman = this.world.findNearbyPlayer(this, 16.0D);

                if (entityhuman != null) {
                    this.village.h();
                }
            }
        }

        super.die(damagesource);
    }

    public void a_(EntityHuman entityhuman) {
        this.tradingPlayer = entityhuman;
    }

    public EntityHuman u_() {
        return this.tradingPlayer;
    }

    public boolean cm() {
        return this.tradingPlayer != null;
    }

    public boolean n(boolean flag) {
        if (!this.bs && flag && this.cp()) {
            boolean flag1 = false;

            for (int i = 0; i < this.inventory.getSize(); ++i) {
                ItemStack itemstack = this.inventory.getItem(i);

                if (itemstack != null) {
                    if (itemstack.getItem() == Items.BREAD && itemstack.count >= 3) {
                        flag1 = true;
                        this.inventory.splitStack(i, 3);
                    } else if ((itemstack.getItem() == Items.POTATO || itemstack.getItem() == Items.CARROT) && itemstack.count >= 12) {
                        flag1 = true;
                        this.inventory.splitStack(i, 12);
                    }
                }

                if (flag1) {
                    this.world.broadcastEntityEffect(this, (byte) 18);
                    this.bs = true;
                    break;
                }
            }
        }

        return this.bs;
    }

    public void o(boolean flag) {
        this.bs = flag;
    }

    public void a(MerchantRecipe merchantrecipe) {
        merchantrecipe.g();
        this.a_ = -this.w();
        this.makeSound("mob.villager.yes", this.bA(), this.bB());
        int i = 3 + this.random.nextInt(4);

        if (merchantrecipe.e() == 1 || this.random.nextInt(5) == 0) {
            this.bq = 40;
            this.br = true;
            this.bs = true;
            if (this.tradingPlayer != null) {
                this.bu = this.tradingPlayer.getName();
            } else {
                this.bu = null;
            }

            i += 5;
        }

        if (merchantrecipe.getBuyItem1().getItem() == Items.EMERALD) {
            this.riches += merchantrecipe.getBuyItem1().count;
        }

        if (merchantrecipe.j()) {
            this.world.addEntity(new EntityExperienceOrb(this.world, this.locX, this.locY + 0.5D, this.locZ, i));
        }

    }

    public void a_(ItemStack itemstack) {
        if (!this.world.isStatic && this.a_ > -this.w() + 20) {
            this.a_ = -this.w();
            if (itemstack != null) {
                this.makeSound("mob.villager.yes", this.bA(), this.bB());
            } else {
                this.makeSound("mob.villager.no", this.bA(), this.bB());
            }
        }

    }

    public MerchantRecipeList getOffers(EntityHuman entityhuman) {
        if (this.bp == null) {
            this.cu();
        }

        return this.bp;
    }

    private void cu() {
        IMerchantRecipeOption[][][] aimerchantrecipeoption = EntityVillager.bA[this.getProfession()];

        if (this.bv != 0 && this.bw != 0) {
            ++this.bw;
        } else {
            this.bv = this.random.nextInt(aimerchantrecipeoption.length) + 1;
            this.bw = 1;
        }

        if (this.bp == null) {
            this.bp = new MerchantRecipeList();
        }

        int i = this.bv - 1;
        int j = this.bw - 1;
        IMerchantRecipeOption[][] aimerchantrecipeoption1 = aimerchantrecipeoption[i];

        if (j < aimerchantrecipeoption1.length) {
            IMerchantRecipeOption[] aimerchantrecipeoption2 = aimerchantrecipeoption1[j];
            IMerchantRecipeOption[] aimerchantrecipeoption3 = aimerchantrecipeoption2;
            int k = aimerchantrecipeoption2.length;

            for (int l = 0; l < k; ++l) {
                IMerchantRecipeOption imerchantrecipeoption = aimerchantrecipeoption3[l];

                imerchantrecipeoption.a(this.bp, this.random);
            }
        }

    }

    public IChatBaseComponent getScoreboardDisplayName() {
        String s = this.getCustomName();

        if (s != null && s.length() > 0) {
            return new ChatComponentText(s);
        } else {
            if (this.bp == null) {
                this.cu();
            }

            String s1 = null;

            switch (this.getProfession()) {
            case 0:
                if (this.bv == 1) {
                    s1 = "farmer";
                } else if (this.bv == 2) {
                    s1 = "fisherman";
                } else if (this.bv == 3) {
                    s1 = "shepherd";
                } else if (this.bv == 4) {
                    s1 = "fletcher";
                }
                break;

            case 1:
                s1 = "librarian";
                break;

            case 2:
                s1 = "cleric";
                break;

            case 3:
                if (this.bv == 1) {
                    s1 = "armor";
                } else if (this.bv == 2) {
                    s1 = "weapon";
                } else if (this.bv == 3) {
                    s1 = "tool";
                }
                break;

            case 4:
                if (this.bv == 1) {
                    s1 = "butcher";
                } else if (this.bv == 2) {
                    s1 = "leather";
                }
            }

            if (s1 != null) {
                ChatMessage chatmessage = new ChatMessage("entity.Villager." + s1, new Object[0]);

                chatmessage.getChatModifier().setChatHoverable(this.aP());
                chatmessage.getChatModifier().setInsertion(this.getUniqueID().toString());
                return chatmessage;
            } else {
                return super.getScoreboardDisplayName();
            }
        }
    }

    public float getHeadHeight() {
        float f = 1.62F;

        if (this.isBaby()) {
            f = (float) ((double) f - 0.81D);
        }

        return f;
    }

    public GroupDataEntity prepare(DifficultyDamageScaler difficultydamagescaler, GroupDataEntity groupdataentity) {
        groupdataentity = super.prepare(difficultydamagescaler, groupdataentity);
        this.setProfession(this.world.random.nextInt(5));
        this.ct();
        return groupdataentity;
    }

    public void cn() {
        this.bx = true;
    }

    public EntityVillager b(EntityAgeable entityageable) {
        EntityVillager entityvillager = new EntityVillager(this.world);

        entityvillager.prepare(this.world.E(new BlockPosition(entityvillager)), (GroupDataEntity) null);
        return entityvillager;
    }

    public boolean ca() {
        return false;
    }

    public void onLightningStrike(EntityLightning entitylightning) {
        if (!this.world.isStatic) {
            EntityWitch entitywitch = new EntityWitch(this.world);

            entitywitch.setPositionRotation(this.locX, this.locY, this.locZ, this.yaw, this.pitch);
            entitywitch.prepare(this.world.E(new BlockPosition(entitywitch)), (GroupDataEntity) null);
            this.world.addEntity(entitywitch);
            this.die();
        }
    }

    public InventorySubcontainer co() {
        return this.inventory;
    }

    protected void a(EntityItem entityitem) {
        ItemStack itemstack = entityitem.getItemStack();
        Item item = itemstack.getItem();

        if (this.a(item)) {
            ItemStack itemstack1 = this.inventory.a(itemstack);

            if (itemstack1 == null) {
                entityitem.die();
            } else {
                itemstack.count = itemstack1.count;
            }
        }

    }

    private boolean a(Item item) {
        return item == Items.BREAD || item == Items.POTATO || item == Items.CARROT || item == Items.WHEAT || item == Items.WHEAT_SEEDS;
    }

    public boolean cp() {
        return this.s(1);
    }

    public boolean cq() {
        return this.s(2);
    }

    public boolean cr() {
        boolean flag = this.getProfession() == 0;

        return flag ? !this.s(5) : !this.s(1);
    }

    private boolean s(int i) {
        boolean flag = this.getProfession() == 0;

        for (int j = 0; j < this.inventory.getSize(); ++j) {
            ItemStack itemstack = this.inventory.getItem(j);

            if (itemstack != null) {
                if (itemstack.getItem() == Items.BREAD && itemstack.count >= 3 * i || itemstack.getItem() == Items.POTATO && itemstack.count >= 12 * i || itemstack.getItem() == Items.CARROT && itemstack.count >= 12 * i) {
                    return true;
                }

                if (flag && itemstack.getItem() == Items.WHEAT && itemstack.count >= 9 * i) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean cs() {
        for (int i = 0; i < this.inventory.getSize(); ++i) {
            ItemStack itemstack = this.inventory.getItem(i);

            if (itemstack != null && (itemstack.getItem() == Items.WHEAT_SEEDS || itemstack.getItem() == Items.POTATO || itemstack.getItem() == Items.CARROT)) {
                return true;
            }
        }

        return false;
    }

    public boolean d(int i, ItemStack itemstack) {
        if (super.d(i, itemstack)) {
            return true;
        } else {
            int j = i - 300;

            if (j >= 0 && j < this.inventory.getSize()) {
                this.inventory.setItem(j, itemstack);
                return true;
            } else {
                return false;
            }
        }
    }

    public EntityAgeable createChild(EntityAgeable entityageable) {
        return this.b(entityageable);
    }
}
