package net.minecraft.server;

import java.util.Collection;
import java.util.List;

public class CommandSummon extends CommandAbstract {

    public CommandSummon() {}

    public String getCommand() {
        return "summon";
    }

    public int a() {
        return 2;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.summon.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) throws CommandException {
        if (astring.length < 1) {
            throw new ExceptionUsage("commands.summon.usage", new Object[0]);
        } else {
            String s = astring[0];
            BlockPosition blockposition = icommandlistener.getChunkCoordinates();
            Vec3D vec3d = icommandlistener.d();
            double d0 = vec3d.a;
            double d1 = vec3d.b;
            double d2 = vec3d.c;

            if (astring.length >= 4) {
                d0 = b(d0, astring[1], true);
                d1 = b(d1, astring[2], false);
                d2 = b(d2, astring[3], true);
                blockposition = new BlockPosition(d0, d1, d2);
            }

            World world = icommandlistener.getWorld();

            if (!world.isLoaded(blockposition)) {
                throw new CommandException("commands.summon.outOfWorld", new Object[0]);
            } else if ("LightningBolt".equals(s)) {
                world.strikeLightning(new EntityLightning(world, d0, d1, d2));
                a(icommandlistener, this, "commands.summon.success", new Object[0]);
            } else {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                boolean flag = false;

                if (astring.length >= 5) {
                    IChatBaseComponent ichatbasecomponent = a(icommandlistener, astring, 4);

                    try {
                        nbttagcompound = MojangsonParser.parse(ichatbasecomponent.c());
                        flag = true;
                    } catch (MojangsonParseException mojangsonparseexception) {
                        throw new CommandException("commands.summon.tagError", new Object[] { mojangsonparseexception.getMessage()});
                    }
                }

                nbttagcompound.setString("id", s);

                Entity entity;

                try {
                    entity = EntityTypes.a(nbttagcompound, world);
                } catch (RuntimeException runtimeexception) {
                    throw new CommandException("commands.summon.failed", new Object[0]);
                }

                if (entity == null) {
                    throw new CommandException("commands.summon.failed", new Object[0]);
                } else {
                    entity.setPositionRotation(d0, d1, d2, entity.yaw, entity.pitch);
                    if (!flag && entity instanceof EntityInsentient) {
                        ((EntityInsentient) entity).prepare(world.E(new BlockPosition(entity)), (GroupDataEntity) null);
                    }

                    world.addEntity(entity);
                    Entity entity1 = entity;

                    for (NBTTagCompound nbttagcompound1 = nbttagcompound; entity1 != null && nbttagcompound1.hasKeyOfType("Riding", 10); nbttagcompound1 = nbttagcompound1.getCompound("Riding")) {
                        Entity entity2 = EntityTypes.a(nbttagcompound1.getCompound("Riding"), world);

                        if (entity2 != null) {
                            entity2.setPositionRotation(d0, d1, d2, entity2.yaw, entity2.pitch);
                            world.addEntity(entity2);
                            entity1.mount(entity2);
                        }

                        entity1 = entity2;
                    }

                    a(icommandlistener, this, "commands.summon.success", new Object[0]);
                }
            }
        }
    }

    public List<String> tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        return astring.length == 1 ? a(astring, (Collection) EntityTypes.b()) : (astring.length > 1 && astring.length <= 4 ? a(astring, 1, blockposition) : null);
    }
}
