package net.minecraft.server;

import com.google.common.collect.Maps;
import java.util.HashMap;

public class EntityPositionTypes {

    private static final HashMap a = Maps.newHashMap();

    public static EnumEntityPositionType a(Class oclass) {
        return (EnumEntityPositionType) EntityPositionTypes.a.get(oclass);
    }

    static {
        EntityPositionTypes.a.put(EntityBat.class, EnumEntityPositionType.ON_GROUND);
        EntityPositionTypes.a.put(EntityChicken.class, EnumEntityPositionType.ON_GROUND);
        EntityPositionTypes.a.put(EntityCow.class, EnumEntityPositionType.ON_GROUND);
        EntityPositionTypes.a.put(EntityHorse.class, EnumEntityPositionType.ON_GROUND);
        EntityPositionTypes.a.put(EntityMushroomCow.class, EnumEntityPositionType.ON_GROUND);
        EntityPositionTypes.a.put(EntityOcelot.class, EnumEntityPositionType.ON_GROUND);
        EntityPositionTypes.a.put(EntityPig.class, EnumEntityPositionType.ON_GROUND);
        EntityPositionTypes.a.put(EntityRabbit.class, EnumEntityPositionType.ON_GROUND);
        EntityPositionTypes.a.put(EntitySheep.class, EnumEntityPositionType.ON_GROUND);
        EntityPositionTypes.a.put(EntitySnowman.class, EnumEntityPositionType.ON_GROUND);
        EntityPositionTypes.a.put(EntitySquid.class, EnumEntityPositionType.IN_WATER);
        EntityPositionTypes.a.put(EntityIronGolem.class, EnumEntityPositionType.ON_GROUND);
        EntityPositionTypes.a.put(EntityWolf.class, EnumEntityPositionType.ON_GROUND);
        EntityPositionTypes.a.put(EntityVillager.class, EnumEntityPositionType.ON_GROUND);
        EntityPositionTypes.a.put(EntityEnderDragon.class, EnumEntityPositionType.ON_GROUND);
        EntityPositionTypes.a.put(EntityWither.class, EnumEntityPositionType.ON_GROUND);
        EntityPositionTypes.a.put(EntityBlaze.class, EnumEntityPositionType.ON_GROUND);
        EntityPositionTypes.a.put(EntityCaveSpider.class, EnumEntityPositionType.ON_GROUND);
        EntityPositionTypes.a.put(EntityCreeper.class, EnumEntityPositionType.ON_GROUND);
        EntityPositionTypes.a.put(EntityEnderman.class, EnumEntityPositionType.ON_GROUND);
        EntityPositionTypes.a.put(EntityEndermite.class, EnumEntityPositionType.ON_GROUND);
        EntityPositionTypes.a.put(EntityGhast.class, EnumEntityPositionType.ON_GROUND);
        EntityPositionTypes.a.put(EntityGiantZombie.class, EnumEntityPositionType.ON_GROUND);
        EntityPositionTypes.a.put(EntityGuardian.class, EnumEntityPositionType.IN_WATER);
        EntityPositionTypes.a.put(EntityMagmaCube.class, EnumEntityPositionType.ON_GROUND);
        EntityPositionTypes.a.put(EntityPigZombie.class, EnumEntityPositionType.ON_GROUND);
        EntityPositionTypes.a.put(EntitySilverfish.class, EnumEntityPositionType.ON_GROUND);
        EntityPositionTypes.a.put(EntitySkeleton.class, EnumEntityPositionType.ON_GROUND);
        EntityPositionTypes.a.put(EntitySlime.class, EnumEntityPositionType.ON_GROUND);
        EntityPositionTypes.a.put(EntitySpider.class, EnumEntityPositionType.ON_GROUND);
        EntityPositionTypes.a.put(EntityWitch.class, EnumEntityPositionType.ON_GROUND);
        EntityPositionTypes.a.put(EntityZombie.class, EnumEntityPositionType.ON_GROUND);
    }
}
