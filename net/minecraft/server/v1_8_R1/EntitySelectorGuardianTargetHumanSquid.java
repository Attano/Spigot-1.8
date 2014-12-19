package net.minecraft.server;

import com.google.common.base.Predicate;

class EntitySelectorGuardianTargetHumanSquid implements Predicate {

    private EntityGuardian a;

    public EntitySelectorGuardianTargetHumanSquid(EntityGuardian entityguardian) {
        this.a = entityguardian;
    }

    public boolean a(EntityLiving entityliving) {
        return (entityliving instanceof EntityHuman || entityliving instanceof EntitySquid) && entityliving.h(this.a) > 9.0D;
    }

    public boolean apply(Object object) {
        return this.a((EntityLiving) object);
    }
}
