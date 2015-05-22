package net.minecraft.server;

import java.util.List;

public class CommandParticle extends CommandAbstract {

    public CommandParticle() {}

    public String getCommand() {
        return "particle";
    }

    public int a() {
        return 2;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.particle.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) throws CommandException {
        if (astring.length < 8) {
            throw new ExceptionUsage("commands.particle.usage", new Object[0]);
        } else {
            boolean flag = false;
            EnumParticle enumparticle = null;
            EnumParticle[] aenumparticle = EnumParticle.values();
            int i = aenumparticle.length;

            for (int j = 0; j < i; ++j) {
                EnumParticle enumparticle1 = aenumparticle[j];

                if (enumparticle1.f()) {
                    if (astring[0].startsWith(enumparticle1.b())) {
                        flag = true;
                        enumparticle = enumparticle1;
                        break;
                    }
                } else if (astring[0].equals(enumparticle1.b())) {
                    flag = true;
                    enumparticle = enumparticle1;
                    break;
                }
            }

            if (!flag) {
                throw new CommandException("commands.particle.notFound", new Object[] { astring[0]});
            } else {
                String s = astring[0];
                Vec3D vec3d = icommandlistener.d();
                double d0 = (double) ((float) b(vec3d.a, astring[1], true));
                double d1 = (double) ((float) b(vec3d.b, astring[2], true));
                double d2 = (double) ((float) b(vec3d.c, astring[3], true));
                double d3 = (double) ((float) c(astring[4]));
                double d4 = (double) ((float) c(astring[5]));
                double d5 = (double) ((float) c(astring[6]));
                double d6 = (double) ((float) c(astring[7]));
                int k = 0;

                if (astring.length > 8) {
                    k = a(astring[8], 0);
                }

                boolean flag1 = false;

                if (astring.length > 9 && "force".equals(astring[9])) {
                    flag1 = true;
                }

                World world = icommandlistener.getWorld();

                if (world instanceof WorldServer) {
                    WorldServer worldserver = (WorldServer) world;
                    int[] aint = new int[enumparticle.d()];

                    if (enumparticle.f()) {
                        String[] astring1 = astring[0].split("_", 3);

                        for (int l = 1; l < astring1.length; ++l) {
                            try {
                                aint[l - 1] = Integer.parseInt(astring1[l]);
                            } catch (NumberFormatException numberformatexception) {
                                throw new CommandException("commands.particle.notFound", new Object[] { astring[0]});
                            }
                        }
                    }

                    worldserver.a(enumparticle, flag1, d0, d1, d2, k, d3, d4, d5, d6, aint);
                    a(icommandlistener, this, "commands.particle.success", new Object[] { s, Integer.valueOf(Math.max(k, 1))});
                }

            }
        }
    }

    public List<String> tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        return astring.length == 1 ? a(astring, EnumParticle.a()) : (astring.length > 1 && astring.length <= 4 ? a(astring, 1, blockposition) : (astring.length == 10 ? a(astring, new String[] { "normal", "force"}) : null));
    }
}
