package net.minecraft.server;

import com.google.common.base.Predicate;

final class PlayerSelectorInnerClass3 implements Predicate {

    final int a;

    PlayerSelectorInnerClass3(int i) {
        this.a = i;
    }

    public boolean a(Entity entity) {
        if (!(entity instanceof EntityPlayer)) {
            return false;
        } else {
            EntityPlayer entityplayer = (EntityPlayer) entity;

            return entityplayer.playerInteractManager.getGameMode().getId() == this.a;
        }
    }

    public boolean apply(Object object) {
        return this.a((Entity) object);
    }
}
