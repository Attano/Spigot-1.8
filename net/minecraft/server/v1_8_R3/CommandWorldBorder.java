package net.minecraft.server;

import java.util.List;

public class CommandWorldBorder extends CommandAbstract {

    public CommandWorldBorder() {}

    public String getCommand() {
        return "worldborder";
    }

    public int a() {
        return 2;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.worldborder.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) throws CommandException {
        if (astring.length < 1) {
            throw new ExceptionUsage("commands.worldborder.usage", new Object[0]);
        } else {
            WorldBorder worldborder = this.d();
            double d0;
            double d1;
            long i;

            if (astring[0].equals("set")) {
                if (astring.length != 2 && astring.length != 3) {
                    throw new ExceptionUsage("commands.worldborder.set.usage", new Object[0]);
                }

                d0 = worldborder.j();
                d1 = a(astring[1], 1.0D, 6.0E7D);
                i = astring.length > 2 ? a(astring[2], 0L, 9223372036854775L) * 1000L : 0L;
                if (i > 0L) {
                    worldborder.transitionSizeBetween(d0, d1, i);
                    if (d0 > d1) {
                        a(icommandlistener, this, "commands.worldborder.setSlowly.shrink.success", new Object[] { String.format("%.1f", new Object[] { Double.valueOf(d1)}), String.format("%.1f", new Object[] { Double.valueOf(d0)}), Long.toString(i / 1000L)});
                    } else {
                        a(icommandlistener, this, "commands.worldborder.setSlowly.grow.success", new Object[] { String.format("%.1f", new Object[] { Double.valueOf(d1)}), String.format("%.1f", new Object[] { Double.valueOf(d0)}), Long.toString(i / 1000L)});
                    }
                } else {
                    worldborder.setSize(d1);
                    a(icommandlistener, this, "commands.worldborder.set.success", new Object[] { String.format("%.1f", new Object[] { Double.valueOf(d1)}), String.format("%.1f", new Object[] { Double.valueOf(d0)})});
                }
            } else if (astring[0].equals("add")) {
                if (astring.length != 2 && astring.length != 3) {
                    throw new ExceptionUsage("commands.worldborder.add.usage", new Object[0]);
                }

                d0 = worldborder.getSize();
                d1 = d0 + a(astring[1], -d0, 6.0E7D - d0);
                i = worldborder.i() + (astring.length > 2 ? a(astring[2], 0L, 9223372036854775L) * 1000L : 0L);
                if (i > 0L) {
                    worldborder.transitionSizeBetween(d0, d1, i);
                    if (d0 > d1) {
                        a(icommandlistener, this, "commands.worldborder.setSlowly.shrink.success", new Object[] { String.format("%.1f", new Object[] { Double.valueOf(d1)}), String.format("%.1f", new Object[] { Double.valueOf(d0)}), Long.toString(i / 1000L)});
                    } else {
                        a(icommandlistener, this, "commands.worldborder.setSlowly.grow.success", new Object[] { String.format("%.1f", new Object[] { Double.valueOf(d1)}), String.format("%.1f", new Object[] { Double.valueOf(d0)}), Long.toString(i / 1000L)});
                    }
                } else {
                    worldborder.setSize(d1);
                    a(icommandlistener, this, "commands.worldborder.set.success", new Object[] { String.format("%.1f", new Object[] { Double.valueOf(d1)}), String.format("%.1f", new Object[] { Double.valueOf(d0)})});
                }
            } else if (astring[0].equals("center")) {
                if (astring.length != 3) {
                    throw new ExceptionUsage("commands.worldborder.center.usage", new Object[0]);
                }

                BlockPosition blockposition = icommandlistener.getChunkCoordinates();
                double d2 = b((double) blockposition.getX() + 0.5D, astring[1], true);
                double d3 = b((double) blockposition.getZ() + 0.5D, astring[2], true);

                worldborder.setCenter(d2, d3);
                a(icommandlistener, this, "commands.worldborder.center.success", new Object[] { Double.valueOf(d2), Double.valueOf(d3)});
            } else if (astring[0].equals("damage")) {
                if (astring.length < 2) {
                    throw new ExceptionUsage("commands.worldborder.damage.usage", new Object[0]);
                }

                if (astring[1].equals("buffer")) {
                    if (astring.length != 3) {
                        throw new ExceptionUsage("commands.worldborder.damage.buffer.usage", new Object[0]);
                    }

                    d0 = a(astring[2], 0.0D);
                    d1 = worldborder.getDamageBuffer();
                    worldborder.setDamageBuffer(d0);
                    a(icommandlistener, this, "commands.worldborder.damage.buffer.success", new Object[] { String.format("%.1f", new Object[] { Double.valueOf(d0)}), String.format("%.1f", new Object[] { Double.valueOf(d1)})});
                } else if (astring[1].equals("amount")) {
                    if (astring.length != 3) {
                        throw new ExceptionUsage("commands.worldborder.damage.amount.usage", new Object[0]);
                    }

                    d0 = a(astring[2], 0.0D);
                    d1 = worldborder.getDamageAmount();
                    worldborder.setDamageAmount(d0);
                    a(icommandlistener, this, "commands.worldborder.damage.amount.success", new Object[] { String.format("%.2f", new Object[] { Double.valueOf(d0)}), String.format("%.2f", new Object[] { Double.valueOf(d1)})});
                }
            } else if (astring[0].equals("warning")) {
                if (astring.length < 2) {
                    throw new ExceptionUsage("commands.worldborder.warning.usage", new Object[0]);
                }

                int j = a(astring[2], 0);
                int k;

                if (astring[1].equals("time")) {
                    if (astring.length != 3) {
                        throw new ExceptionUsage("commands.worldborder.warning.time.usage", new Object[0]);
                    }

                    k = worldborder.getWarningTime();
                    worldborder.setWarningTime(j);
                    a(icommandlistener, this, "commands.worldborder.warning.time.success", new Object[] { Integer.valueOf(j), Integer.valueOf(k)});
                } else if (astring[1].equals("distance")) {
                    if (astring.length != 3) {
                        throw new ExceptionUsage("commands.worldborder.warning.distance.usage", new Object[0]);
                    }

                    k = worldborder.getWarningDistance();
                    worldborder.setWarningDistance(j);
                    a(icommandlistener, this, "commands.worldborder.warning.distance.success", new Object[] { Integer.valueOf(j), Integer.valueOf(k)});
                }
            } else {
                if (!astring[0].equals("get")) {
                    throw new ExceptionUsage("commands.worldborder.usage", new Object[0]);
                }

                d0 = worldborder.getSize();
                icommandlistener.a(CommandObjectiveExecutor.EnumCommandResult.QUERY_RESULT, MathHelper.floor(d0 + 0.5D));
                icommandlistener.sendMessage(new ChatMessage("commands.worldborder.get.success", new Object[] { String.format("%.0f", new Object[] { Double.valueOf(d0)})}));
            }

        }
    }

    protected WorldBorder d() {
        return MinecraftServer.getServer().worldServer[0].getWorldBorder();
    }

    public List<String> tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        return astring.length == 1 ? a(astring, new String[] { "set", "center", "damage", "warning", "add", "get"}) : (astring.length == 2 && astring[0].equals("damage") ? a(astring, new String[] { "buffer", "amount"}) : (astring.length >= 2 && astring.length <= 3 && astring[0].equals("center") ? b(astring, 1, blockposition) : (astring.length == 2 && astring[0].equals("warning") ? a(astring, new String[] { "time", "distance"}) : null)));
    }
}
