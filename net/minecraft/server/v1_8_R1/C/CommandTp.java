package net.minecraft.server;

import java.util.EnumSet;
import java.util.List;

public class CommandTp extends CommandAbstract {

    public CommandTp() {}

    public String getCommand() {
        return "tp";
    }

    public int a() {
        return 2;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.tp.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) {
        if (astring.length < 1) {
            throw new ExceptionUsage("commands.tp.usage", new Object[0]);
        } else {
            byte b0 = 0;
            Object object;

            if (astring.length != 2 && astring.length != 4 && astring.length != 6) {
                object = b(icommandlistener);
            } else {
                object = b(icommandlistener, astring[0]);
                b0 = 1;
            }

            if (astring.length != 1 && astring.length != 2) {
                if (astring.length < b0 + 3) {
                    throw new ExceptionUsage("commands.tp.usage", new Object[0]);
                } else if (((Entity) object).world != null) {
                    int i = b0 + 1;
                    CommandNumber commandnumber = a(((Entity) object).locX, astring[b0], true);
                    CommandNumber commandnumber1 = a(((Entity) object).locY, astring[i++], 0, 0, false);
                    CommandNumber commandnumber2 = a(((Entity) object).locZ, astring[i++], true);
                    CommandNumber commandnumber3 = a((double) ((Entity) object).yaw, astring.length > i ? astring[i++] : "~", false);
                    CommandNumber commandnumber4 = a((double) ((Entity) object).pitch, astring.length > i ? astring[i] : "~", false);
                    float f;

                    if (object instanceof EntityPlayer) {
                        EnumSet enumset = EnumSet.noneOf(EnumPlayerTeleportFlags.class);

                        if (commandnumber.c()) {
                            enumset.add(EnumPlayerTeleportFlags.X);
                        }

                        if (commandnumber1.c()) {
                            enumset.add(EnumPlayerTeleportFlags.Y);
                        }

                        if (commandnumber2.c()) {
                            enumset.add(EnumPlayerTeleportFlags.Z);
                        }

                        if (commandnumber4.c()) {
                            enumset.add(EnumPlayerTeleportFlags.X_ROT);
                        }

                        if (commandnumber3.c()) {
                            enumset.add(EnumPlayerTeleportFlags.Y_ROT);
                        }

                        f = (float) commandnumber3.b();
                        if (!commandnumber3.c()) {
                            f = MathHelper.g(f);
                        }

                        float f1 = (float) commandnumber4.b();

                        if (!commandnumber4.c()) {
                            f1 = MathHelper.g(f1);
                        }

                        if (f1 > 90.0F || f1 < -90.0F) {
                            f1 = MathHelper.g(180.0F - f1);
                            f = MathHelper.g(f + 180.0F);
                        }

                        ((Entity) object).mount((Entity) null);
                        ((EntityPlayer) object).playerConnection.a(commandnumber.b(), commandnumber1.b(), commandnumber2.b(), f, f1, enumset);
                        ((Entity) object).f(f);
                    } else {
                        float f2 = (float) MathHelper.g(commandnumber3.a());

                        f = (float) MathHelper.g(commandnumber4.a());
                        if (f > 90.0F || f < -90.0F) {
                            f = MathHelper.g(180.0F - f);
                            f2 = MathHelper.g(f2 + 180.0F);
                        }

                        ((Entity) object).setPositionRotation(commandnumber.a(), commandnumber1.a(), commandnumber2.a(), f2, f);
                        ((Entity) object).f(f2);
                    }

                    a(icommandlistener, this, "commands.tp.success.coordinates", new Object[] { ((Entity) object).getName(), Double.valueOf(commandnumber.a()), Double.valueOf(commandnumber1.a()), Double.valueOf(commandnumber2.a())});
                }
            } else {
                Entity entity = b(icommandlistener, astring[astring.length - 1]);

                if (entity.world != ((Entity) object).world) {
                    throw new CommandException("commands.tp.notSameDimension", new Object[0]);
                } else {
                    ((Entity) object).mount((Entity) null);
                    if (object instanceof EntityPlayer) {
                        ((EntityPlayer) object).playerConnection.a(entity.locX, entity.locY, entity.locZ, entity.yaw, entity.pitch);
                    } else {
                        ((Entity) object).setPositionRotation(entity.locX, entity.locY, entity.locZ, entity.yaw, entity.pitch);
                    }

                    a(icommandlistener, this, "commands.tp.success", new Object[] { ((Entity) object).getName(), entity.getName()});
                }
            }
        }
    }

    public List tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        return astring.length != 1 && astring.length != 2 ? null : a(astring, MinecraftServer.getServer().getPlayers());
    }

    public boolean isListStart(String[] astring, int i) {
        return i == 0;
    }
}
