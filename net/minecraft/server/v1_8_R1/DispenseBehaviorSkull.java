package net.minecraft.server;

import com.mojang.authlib.GameProfile;
import java.util.UUID;

final class DispenseBehaviorSkull extends DispenseBehaviorItem {

    private boolean b = true;

    DispenseBehaviorSkull() {}

    protected ItemStack b(ISourceBlock isourceblock, ItemStack itemstack) {
        World world = isourceblock.i();
        EnumDirection enumdirection = BlockDispenser.b(isourceblock.f());
        BlockPosition blockposition = isourceblock.getBlockPosition().shift(enumdirection);
        BlockSkull blockskull = Blocks.SKULL;

        if (world.isEmpty(blockposition) && blockskull.b(world, blockposition, itemstack)) {
            if (!world.isStatic) {
                world.setTypeAndData(blockposition, blockskull.getBlockData().set(BlockSkull.FACING, EnumDirection.UP), 3);
                TileEntity tileentity = world.getTileEntity(blockposition);

                if (tileentity instanceof TileEntitySkull) {
                    if (itemstack.getData() == 3) {
                        GameProfile gameprofile = null;

                        if (itemstack.hasTag()) {
                            NBTTagCompound nbttagcompound = itemstack.getTag();

                            if (nbttagcompound.hasKeyOfType("SkullOwner", 10)) {
                                gameprofile = GameProfileSerializer.deserialize(nbttagcompound.getCompound("SkullOwner"));
                            } else if (nbttagcompound.hasKeyOfType("SkullOwner", 8)) {
                                gameprofile = new GameProfile((UUID) null, nbttagcompound.getString("SkullOwner"));
                            }
                        }

                        ((TileEntitySkull) tileentity).setGameProfile(gameprofile);
                    } else {
                        ((TileEntitySkull) tileentity).setSkullType(itemstack.getData());
                    }

                    ((TileEntitySkull) tileentity).setRotation(enumdirection.opposite().b() * 4);
                    Blocks.SKULL.a(world, blockposition, (TileEntitySkull) tileentity);
                }

                --itemstack.count;
            }
        } else {
            this.b = false;
        }

        return itemstack;
    }

    protected void a(ISourceBlock isourceblock) {
        if (this.b) {
            isourceblock.i().triggerEffect(1000, isourceblock.getBlockPosition(), 0);
        } else {
            isourceblock.i().triggerEffect(1001, isourceblock.getBlockPosition(), 0);
        }

    }
}
