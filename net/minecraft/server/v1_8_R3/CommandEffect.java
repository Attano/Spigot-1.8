package net.minecraft.server;

import java.util.Collection;
import java.util.List;

public class CommandEffect extends CommandAbstract {

    public CommandEffect() {}

    public String getCommand() {
        return "effect";
    }

    public int a() {
        return 2;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.effect.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) throws CommandException {
        if (astring.length < 2) {
            throw new ExceptionUsage("commands.effect.usage", new Object[0]);
        } else {
            EntityLiving entityliving = (EntityLiving) a(icommandlistener, astring[0], EntityLiving.class);

            if (astring[1].equals("clear")) {
                if (entityliving.getEffects().isEmpty()) {
                    throw new CommandException("commands.effect.failure.notActive.all", new Object[] { entityliving.getName()});
                } else {
                    entityliving.removeAllEffects();
                    a(icommandlistener, this, "commands.effect.success.removed.all", new Object[] { entityliving.getName()});
                }
            } else {
                int i;

                try {
                    i = a(astring[1], 1);
                } catch (ExceptionInvalidNumber exceptioninvalidnumber) {
                    MobEffectList mobeffectlist = MobEffectList.b(astring[1]);

                    if (mobeffectlist == null) {
                        throw exceptioninvalidnumber;
                    }

                    i = mobeffectlist.id;
                }

                int j = 600;
                int k = 30;
                int l = 0;

                if (i >= 0 && i < MobEffectList.byId.length && MobEffectList.byId[i] != null) {
                    MobEffectList mobeffectlist1 = MobEffectList.byId[i];

                    if (astring.length >= 3) {
                        k = a(astring[2], 0, 1000000);
                        if (mobeffectlist1.isInstant()) {
                            j = k;
                        } else {
                            j = k * 20;
                        }
                    } else if (mobeffectlist1.isInstant()) {
                        j = 1;
                    }

                    if (astring.length >= 4) {
                        l = a(astring[3], 0, 255);
                    }

                    boolean flag = true;

                    if (astring.length >= 5 && "true".equalsIgnoreCase(astring[4])) {
                        flag = false;
                    }

                    if (k > 0) {
                        MobEffect mobeffect = new MobEffect(i, j, l, false, flag);

                        entityliving.addEffect(mobeffect);
                        a(icommandlistener, this, "commands.effect.success", new Object[] { new ChatMessage(mobeffect.g(), new Object[0]), Integer.valueOf(i), Integer.valueOf(l), entityliving.getName(), Integer.valueOf(k)});
                    } else if (entityliving.hasEffect(i)) {
                        entityliving.removeEffect(i);
                        a(icommandlistener, this, "commands.effect.success.removed", new Object[] { new ChatMessage(mobeffectlist1.a(), new Object[0]), entityliving.getName()});
                    } else {
                        throw new CommandException("commands.effect.failure.notActive", new Object[] { new ChatMessage(mobeffectlist1.a(), new Object[0]), entityliving.getName()});
                    }
                } else {
                    throw new ExceptionInvalidNumber("commands.effect.notFound", new Object[] { Integer.valueOf(i)});
                }
            }
        }
    }

    public List<String> tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        return astring.length == 1 ? a(astring, this.d()) : (astring.length == 2 ? a(astring, (Collection) MobEffectList.c()) : (astring.length == 5 ? a(astring, new String[] { "true", "false"}) : null));
    }

    protected String[] d() {
        return MinecraftServer.getServer().getPlayers();
    }

    public boolean isListStart(String[] astring, int i) {
        return i == 0;
    }
}
