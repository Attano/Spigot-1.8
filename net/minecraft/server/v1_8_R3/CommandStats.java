package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CommandStats extends CommandAbstract {

    public CommandStats() {}

    public String getCommand() {
        return "stats";
    }

    public int a() {
        return 2;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.stats.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) throws CommandException {
        if (astring.length < 1) {
            throw new ExceptionUsage("commands.stats.usage", new Object[0]);
        } else {
            boolean flag;

            if (astring[0].equals("entity")) {
                flag = false;
            } else {
                if (!astring[0].equals("block")) {
                    throw new ExceptionUsage("commands.stats.usage", new Object[0]);
                }

                flag = true;
            }

            byte b0;

            if (flag) {
                if (astring.length < 5) {
                    throw new ExceptionUsage("commands.stats.block.usage", new Object[0]);
                }

                b0 = 4;
            } else {
                if (astring.length < 3) {
                    throw new ExceptionUsage("commands.stats.entity.usage", new Object[0]);
                }

                b0 = 2;
            }

            int i = b0 + 1;
            String s = astring[b0];

            if ("set".equals(s)) {
                if (astring.length < i + 3) {
                    if (i == 5) {
                        throw new ExceptionUsage("commands.stats.block.set.usage", new Object[0]);
                    }

                    throw new ExceptionUsage("commands.stats.entity.set.usage", new Object[0]);
                }
            } else {
                if (!"clear".equals(s)) {
                    throw new ExceptionUsage("commands.stats.usage", new Object[0]);
                }

                if (astring.length < i + 1) {
                    if (i == 5) {
                        throw new ExceptionUsage("commands.stats.block.clear.usage", new Object[0]);
                    }

                    throw new ExceptionUsage("commands.stats.entity.clear.usage", new Object[0]);
                }
            }

            CommandObjectiveExecutor.EnumCommandResult commandobjectiveexecutor_enumcommandresult = CommandObjectiveExecutor.EnumCommandResult.a(astring[i++]);

            if (commandobjectiveexecutor_enumcommandresult == null) {
                throw new CommandException("commands.stats.failed", new Object[0]);
            } else {
                World world = icommandlistener.getWorld();
                CommandObjectiveExecutor commandobjectiveexecutor;
                BlockPosition blockposition;
                TileEntity tileentity;

                if (flag) {
                    blockposition = a(icommandlistener, astring, 1, false);
                    tileentity = world.getTileEntity(blockposition);
                    if (tileentity == null) {
                        throw new CommandException("commands.stats.noCompatibleBlock", new Object[] { Integer.valueOf(blockposition.getX()), Integer.valueOf(blockposition.getY()), Integer.valueOf(blockposition.getZ())});
                    }

                    if (tileentity instanceof TileEntityCommand) {
                        commandobjectiveexecutor = ((TileEntityCommand) tileentity).c();
                    } else {
                        if (!(tileentity instanceof TileEntitySign)) {
                            throw new CommandException("commands.stats.noCompatibleBlock", new Object[] { Integer.valueOf(blockposition.getX()), Integer.valueOf(blockposition.getY()), Integer.valueOf(blockposition.getZ())});
                        }

                        commandobjectiveexecutor = ((TileEntitySign) tileentity).d();
                    }
                } else {
                    Entity entity = b(icommandlistener, astring[1]);

                    commandobjectiveexecutor = entity.aU();
                }

                if ("set".equals(s)) {
                    String s1 = astring[i++];
                    String s2 = astring[i];

                    if (s1.length() == 0 || s2.length() == 0) {
                        throw new CommandException("commands.stats.failed", new Object[0]);
                    }

                    CommandObjectiveExecutor.a(commandobjectiveexecutor, commandobjectiveexecutor_enumcommandresult, s1, s2);
                    a(icommandlistener, this, "commands.stats.success", new Object[] { commandobjectiveexecutor_enumcommandresult.b(), s2, s1});
                } else if ("clear".equals(s)) {
                    CommandObjectiveExecutor.a(commandobjectiveexecutor, commandobjectiveexecutor_enumcommandresult, (String) null, (String) null);
                    a(icommandlistener, this, "commands.stats.cleared", new Object[] { commandobjectiveexecutor_enumcommandresult.b()});
                }

                if (flag) {
                    blockposition = a(icommandlistener, astring, 1, false);
                    tileentity = world.getTileEntity(blockposition);
                    tileentity.update();
                }

            }
        }
    }

    public List<String> tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        return astring.length == 1 ? a(astring, new String[] { "entity", "block"}) : (astring.length == 2 && astring[0].equals("entity") ? a(astring, this.d()) : (astring.length >= 2 && astring.length <= 4 && astring[0].equals("block") ? a(astring, 1, blockposition) : ((astring.length != 3 || !astring[0].equals("entity")) && (astring.length != 5 || !astring[0].equals("block")) ? ((astring.length != 4 || !astring[0].equals("entity")) && (astring.length != 6 || !astring[0].equals("block")) ? ((astring.length != 6 || !astring[0].equals("entity")) && (astring.length != 8 || !astring[0].equals("block")) ? null : a(astring, (Collection) this.e())) : a(astring, CommandObjectiveExecutor.EnumCommandResult.c())) : a(astring, new String[] { "set", "clear"}))));
    }

    protected String[] d() {
        return MinecraftServer.getServer().getPlayers();
    }

    protected List<String> e() {
        Collection collection = MinecraftServer.getServer().getWorldServer(0).getScoreboard().getObjectives();
        ArrayList arraylist = Lists.newArrayList();
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            ScoreboardObjective scoreboardobjective = (ScoreboardObjective) iterator.next();

            if (!scoreboardobjective.getCriteria().isReadOnly()) {
                arraylist.add(scoreboardobjective.getName());
            }
        }

        return arraylist;
    }

    public boolean isListStart(String[] astring, int i) {
        return astring.length > 0 && astring[0].equals("entity") && i == 1;
    }
}
