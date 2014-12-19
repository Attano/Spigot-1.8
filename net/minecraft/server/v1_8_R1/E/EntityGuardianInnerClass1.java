package net.minecraft.server;

import com.google.common.base.Predicate;

class EntityGuardianInnerClass1 implements Predicate {

    final EntityGuardian a;

    EntityGuardianInnerClass1(EntityGuardian entityguardian) {
        this.a = entityguardian;
    }

    public boolean a(EntityPlayer entityplayer) {
        return this.a.h(entityplayer) < 2500.0D && entityplayer.playerInteractManager.c();
    }

    public boolean apply(Object object) {
        return this.a((EntityPlayer) object);
    }
}
