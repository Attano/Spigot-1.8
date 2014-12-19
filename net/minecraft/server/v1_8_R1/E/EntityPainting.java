package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.ArrayList;

public class EntityPainting extends EntityHanging {

    public EnumArt art;

    public EntityPainting(World world) {
        super(world);
        this.art = EnumArt.values()[this.random.nextInt(EnumArt.values().length)]; // CraftBukkit - generate a non-null painting
    }

    public EntityPainting(World world, BlockPosition blockposition, EnumDirection enumdirection) {
        super(world, blockposition);
        ArrayList arraylist = Lists.newArrayList();
        EnumArt[] aenumart = EnumArt.values();
        int i = aenumart.length;

        for (int j = 0; j < i; ++j) {
            EnumArt enumart = aenumart[j];

            this.art = enumart;
            this.setDirection(enumdirection);
            if (this.survives()) {
                arraylist.add(enumart);
            }
        }

        if (!arraylist.isEmpty()) {
            this.art = (EnumArt) arraylist.get(this.random.nextInt(arraylist.size()));
        }

        this.setDirection(enumdirection);
    }

    public void b(NBTTagCompound nbttagcompound) {
        nbttagcompound.setString("Motive", this.art.B);
        super.b(nbttagcompound);
    }

    public void a(NBTTagCompound nbttagcompound) {
        String s = nbttagcompound.getString("Motive");
        EnumArt[] aenumart = EnumArt.values();
        int i = aenumart.length;

        for (int j = 0; j < i; ++j) {
            EnumArt enumart = aenumart[j];

            if (enumart.B.equals(s)) {
                this.art = enumart;
            }
        }

        if (this.art == null) {
            this.art = EnumArt.KEBAB;
        }

        super.a(nbttagcompound);
    }

    public int l() {
        return this.art.C;
    }

    public int m() {
        return this.art.D;
    }

    public void b(Entity entity) {
        if (this.world.getGameRules().getBoolean("doTileDrops")) {
            if (entity instanceof EntityHuman) {
                EntityHuman entityhuman = (EntityHuman) entity;

                if (entityhuman.abilities.canInstantlyBuild) {
                    return;
                }
            }

            this.a(new ItemStack(Items.PAINTING), 0.0F);
        }
    }

    public void setPositionRotation(double d0, double d1, double d2, float f, float f1) {
        BlockPosition blockposition = new BlockPosition(d0 - this.locX, d1 - this.locY, d2 - this.locZ);
        BlockPosition blockposition1 = this.blockPosition.a(blockposition);

        this.setPosition((double) blockposition1.getX(), (double) blockposition1.getY(), (double) blockposition1.getZ());
    }
}
