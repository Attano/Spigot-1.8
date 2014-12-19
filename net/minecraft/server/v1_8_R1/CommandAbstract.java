package net.minecraft.server;

import com.google.common.base.Functions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.primitives.Doubles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public abstract class CommandAbstract implements ICommand {

    private static ICommandDispatcher a;

    public CommandAbstract() {}

    public int a() {
        return 4;
    }

    public List b() {
        return Collections.emptyList();
    }

    public boolean canUse(ICommandListener icommandlistener) {
        return icommandlistener.a(this.a(), this.getCommand());
    }

    public List tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        return null;
    }

    public static int a(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException numberformatexception) {
            throw new ExceptionInvalidNumber("commands.generic.num.invalid", new Object[] { s});
        }
    }

    public static int a(String s, int i) {
        return a(s, i, Integer.MAX_VALUE);
    }

    public static int a(String s, int i, int j) {
        int k = a(s);

        if (k < i) {
            throw new ExceptionInvalidNumber("commands.generic.num.tooSmall", new Object[] { Integer.valueOf(k), Integer.valueOf(i)});
        } else if (k > j) {
            throw new ExceptionInvalidNumber("commands.generic.num.tooBig", new Object[] { Integer.valueOf(k), Integer.valueOf(j)});
        } else {
            return k;
        }
    }

    public static long b(String s) {
        try {
            return Long.parseLong(s);
        } catch (NumberFormatException numberformatexception) {
            throw new ExceptionInvalidNumber("commands.generic.num.invalid", new Object[] { s});
        }
    }

    public static long a(String s, long i, long j) {
        long k = b(s);

        if (k < i) {
            throw new ExceptionInvalidNumber("commands.generic.num.tooSmall", new Object[] { Long.valueOf(k), Long.valueOf(i)});
        } else if (k > j) {
            throw new ExceptionInvalidNumber("commands.generic.num.tooBig", new Object[] { Long.valueOf(k), Long.valueOf(j)});
        } else {
            return k;
        }
    }

    public static BlockPosition a(ICommandListener icommandlistener, String[] astring, int i, boolean flag) {
        BlockPosition blockposition = icommandlistener.getChunkCoordinates();

        return new BlockPosition(b((double) blockposition.getX(), astring[i], -30000000, 30000000, flag), b((double) blockposition.getY(), astring[i + 1], 0, 256, false), b((double) blockposition.getZ(), astring[i + 2], -30000000, 30000000, flag));
    }

    public static double c(String s) {
        try {
            double d0 = Double.parseDouble(s);

            if (!Doubles.isFinite(d0)) {
                throw new ExceptionInvalidNumber("commands.generic.num.invalid", new Object[] { s});
            } else {
                return d0;
            }
        } catch (NumberFormatException numberformatexception) {
            throw new ExceptionInvalidNumber("commands.generic.num.invalid", new Object[] { s});
        }
    }

    public static double a(String s, double d0) {
        return a(s, d0, Double.MAX_VALUE);
    }

    public static double a(String s, double d0, double d1) {
        double d2 = c(s);

        if (d2 < d0) {
            throw new ExceptionInvalidNumber("commands.generic.double.tooSmall", new Object[] { Double.valueOf(d2), Double.valueOf(d0)});
        } else if (d2 > d1) {
            throw new ExceptionInvalidNumber("commands.generic.double.tooBig", new Object[] { Double.valueOf(d2), Double.valueOf(d1)});
        } else {
            return d2;
        }
    }

    public static boolean d(String s) {
        if (!s.equals("true") && !s.equals("1")) {
            if (!s.equals("false") && !s.equals("0")) {
                throw new CommandException("commands.generic.boolean.invalid", new Object[] { s});
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public static EntityPlayer b(ICommandListener icommandlistener) {
        if (icommandlistener instanceof EntityPlayer) {
            return (EntityPlayer) icommandlistener;
        } else {
            throw new ExceptionPlayerNotFound("You must specify which player you wish to perform this action on.", new Object[0]);
        }
    }

    public static EntityPlayer a(ICommandListener icommandlistener, String s) {
        EntityPlayer entityplayer = PlayerSelector.getPlayer(icommandlistener, s);

        if (entityplayer == null) {
            try {
                entityplayer = MinecraftServer.getServer().getPlayerList().a(UUID.fromString(s));
            } catch (IllegalArgumentException illegalargumentexception) {
                ;
            }
        }

        if (entityplayer == null) {
            entityplayer = MinecraftServer.getServer().getPlayerList().getPlayer(s);
        }

        if (entityplayer == null) {
            throw new ExceptionPlayerNotFound();
        } else {
            return entityplayer;
        }
    }

    public static Entity b(ICommandListener icommandlistener, String s) {
        return a(icommandlistener, s, Entity.class);
    }

    public static Entity a(ICommandListener icommandlistener, String s, Class oclass) {
        Object object = PlayerSelector.getEntity(icommandlistener, s, oclass);
        MinecraftServer minecraftserver = MinecraftServer.getServer();

        if (object == null) {
            object = minecraftserver.getPlayerList().getPlayer(s);
        }

        if (object == null) {
            try {
                UUID uuid = UUID.fromString(s);

                object = minecraftserver.a(uuid);
                if (object == null) {
                    object = minecraftserver.getPlayerList().a(uuid);
                }
            } catch (IllegalArgumentException illegalargumentexception) {
                throw new ExceptionEntityNotFound("commands.generic.entity.invalidUuid", new Object[0]);
            }
        }

        if (object != null && oclass.isAssignableFrom(object.getClass())) {
            return (Entity) object;
        } else {
            throw new ExceptionEntityNotFound();
        }
    }

    public static List c(ICommandListener icommandlistener, String s) {
        return (List) (PlayerSelector.isPattern(s) ? PlayerSelector.getPlayers(icommandlistener, s, Entity.class) : Lists.newArrayList(new Entity[] { b(icommandlistener, s)}));
    }

    public static String d(ICommandListener icommandlistener, String s) {
        try {
            return a(icommandlistener, s).getName();
        } catch (ExceptionPlayerNotFound exceptionplayernotfound) {
            if (PlayerSelector.isPattern(s)) {
                throw exceptionplayernotfound;
            } else {
                return s;
            }
        }
    }

    public static String e(ICommandListener icommandlistener, String s) {
        try {
            return a(icommandlistener, s).getName();
        } catch (ExceptionPlayerNotFound exceptionplayernotfound) {
            try {
                return b(icommandlistener, s).getUniqueID().toString();
            } catch (ExceptionEntityNotFound exceptionentitynotfound) {
                if (PlayerSelector.isPattern(s)) {
                    throw exceptionentitynotfound;
                } else {
                    return s;
                }
            }
        }
    }

    public static IChatBaseComponent a(ICommandListener icommandlistener, String[] astring, int i) {
        return b(icommandlistener, astring, i, false);
    }

    public static IChatBaseComponent b(ICommandListener icommandlistener, String[] astring, int i, boolean flag) {
        ChatComponentText chatcomponenttext = new ChatComponentText("");

        for (int j = i; j < astring.length; ++j) {
            if (j > i) {
                chatcomponenttext.a(" ");
            }

            Object object = new ChatComponentText(astring[j]);

            if (flag) {
                IChatBaseComponent ichatbasecomponent = PlayerSelector.getPlayerNames(icommandlistener, astring[j]);

                if (ichatbasecomponent == null) {
                    if (PlayerSelector.isPattern(astring[j])) {
                        throw new ExceptionPlayerNotFound();
                    }
                } else {
                    object = ichatbasecomponent;
                }
            }

            chatcomponenttext.addSibling((IChatBaseComponent) object);
        }

        return chatcomponenttext;
    }

    public static String a(String[] astring, int i) {
        StringBuilder stringbuilder = new StringBuilder();

        for (int j = i; j < astring.length; ++j) {
            if (j > i) {
                stringbuilder.append(" ");
            }

            String s = astring[j];

            stringbuilder.append(s);
        }

        return stringbuilder.toString();
    }

    public static CommandNumber a(double d0, String s, boolean flag) {
        return a(d0, s, -30000000, 30000000, flag);
    }

    public static CommandNumber a(double d0, String s, int i, int j, boolean flag) {
        boolean flag1 = s.startsWith("~");

        if (flag1 && Double.isNaN(d0)) {
            throw new ExceptionInvalidNumber("commands.generic.num.invalid", new Object[] { Double.valueOf(d0)});
        } else {
            double d1 = 0.0D;

            if (!flag1 || s.length() > 1) {
                boolean flag2 = s.contains(".");

                if (flag1) {
                    s = s.substring(1);
                }

                d1 += c(s);
                if (!flag2 && !flag1 && flag) {
                    d1 += 0.5D;
                }
            }

            if (i != 0 || j != 0) {
                if (d1 < (double) i) {
                    throw new ExceptionInvalidNumber("commands.generic.double.tooSmall", new Object[] { Double.valueOf(d1), Integer.valueOf(i)});
                }

                if (d1 > (double) j) {
                    throw new ExceptionInvalidNumber("commands.generic.double.tooBig", new Object[] { Double.valueOf(d1), Integer.valueOf(j)});
                }
            }

            return new CommandNumber(d1 + (flag1 ? d0 : 0.0D), d1, flag1);
        }
    }

    public static double b(double d0, String s, boolean flag) {
        return b(d0, s, -30000000, 30000000, flag);
    }

    public static double b(double d0, String s, int i, int j, boolean flag) {
        boolean flag1 = s.startsWith("~");

        if (flag1 && Double.isNaN(d0)) {
            throw new ExceptionInvalidNumber("commands.generic.num.invalid", new Object[] { Double.valueOf(d0)});
        } else {
            double d1 = flag1 ? d0 : 0.0D;

            if (!flag1 || s.length() > 1) {
                boolean flag2 = s.contains(".");

                if (flag1) {
                    s = s.substring(1);
                }

                d1 += c(s);
                if (!flag2 && !flag1 && flag) {
                    d1 += 0.5D;
                }
            }

            if (i != 0 || j != 0) {
                if (d1 < (double) i) {
                    throw new ExceptionInvalidNumber("commands.generic.double.tooSmall", new Object[] { Double.valueOf(d1), Integer.valueOf(i)});
                }

                if (d1 > (double) j) {
                    throw new ExceptionInvalidNumber("commands.generic.double.tooBig", new Object[] { Double.valueOf(d1), Integer.valueOf(j)});
                }
            }

            return d1;
        }
    }

    public static Item f(ICommandListener icommandlistener, String s) {
        MinecraftKey minecraftkey = new MinecraftKey(s);
        Item item = (Item) Item.REGISTRY.get(minecraftkey);

        if (item == null) {
            throw new ExceptionInvalidNumber("commands.give.notFound", new Object[] { minecraftkey});
        } else {
            return item;
        }
    }

    public static Block g(ICommandListener icommandlistener, String s) {
        MinecraftKey minecraftkey = new MinecraftKey(s);

        if (!Block.REGISTRY.d(minecraftkey)) {
            throw new ExceptionInvalidNumber("commands.give.notFound", new Object[] { minecraftkey});
        } else {
            Block block = (Block) Block.REGISTRY.get(minecraftkey);

            if (block == null) {
                throw new ExceptionInvalidNumber("commands.give.notFound", new Object[] { minecraftkey});
            } else {
                return block;
            }
        }
    }

    public static String a(Object[] aobject) {
        StringBuilder stringbuilder = new StringBuilder();

        for (int i = 0; i < aobject.length; ++i) {
            String s = aobject[i].toString();

            if (i > 0) {
                if (i == aobject.length - 1) {
                    stringbuilder.append(" and ");
                } else {
                    stringbuilder.append(", ");
                }
            }

            stringbuilder.append(s);
        }

        return stringbuilder.toString();
    }

    public static IChatBaseComponent a(List list) {
        ChatComponentText chatcomponenttext = new ChatComponentText("");

        for (int i = 0; i < list.size(); ++i) {
            if (i > 0) {
                if (i == list.size() - 1) {
                    chatcomponenttext.a(" and ");
                } else if (i > 0) {
                    chatcomponenttext.a(", ");
                }
            }

            chatcomponenttext.addSibling((IChatBaseComponent) list.get(i));
        }

        return chatcomponenttext;
    }

    public static String a(Collection collection) {
        return a(collection.toArray(new String[collection.size()]));
    }

    public static List a(String[] astring, int i, BlockPosition blockposition) {
        if (blockposition == null) {
            return null;
        } else {
            String s;

            if (astring.length - 1 == i) {
                s = Integer.toString(blockposition.getX());
            } else if (astring.length - 1 == i + 1) {
                s = Integer.toString(blockposition.getY());
            } else {
                if (astring.length - 1 != i + 2) {
                    return null;
                }

                s = Integer.toString(blockposition.getZ());
            }

            return Lists.newArrayList(new String[] { s});
        }
    }

    public static boolean a(String s, String s1) {
        return s1.regionMatches(true, 0, s, 0, s.length());
    }

    public static List a(String[] astring, String... astring1) {
        return a(astring, (Collection) Arrays.asList(astring1));
    }

    public static List a(String[] astring, Collection collection) {
        String s = astring[astring.length - 1];
        ArrayList arraylist = Lists.newArrayList();

        if (!collection.isEmpty()) {
            Iterator iterator = Iterables.transform(collection, Functions.toStringFunction()).iterator();

            while (iterator.hasNext()) {
                String s1 = (String) iterator.next();

                if (a(s, s1)) {
                    arraylist.add(s1);
                }
            }

            if (arraylist.isEmpty()) {
                iterator = collection.iterator();

                while (iterator.hasNext()) {
                    Object object = iterator.next();

                    if (object instanceof MinecraftKey && a(s, ((MinecraftKey) object).a())) {
                        arraylist.add(String.valueOf(object));
                    }
                }
            }
        }

        return arraylist;
    }

    public boolean isListStart(String[] astring, int i) {
        return false;
    }

    public static void a(ICommandListener icommandlistener, ICommand icommand, String s, Object... aobject) {
        a(icommandlistener, icommand, 0, s, aobject);
    }

    public static void a(ICommandListener icommandlistener, ICommand icommand, int i, String s, Object... aobject) {
        if (CommandAbstract.a != null) {
            CommandAbstract.a.a(icommandlistener, icommand, i, s, aobject);
        }

    }

    public static void a(ICommandDispatcher icommanddispatcher) {
        CommandAbstract.a = icommanddispatcher;
    }

    public int a(ICommand icommand) {
        return this.getCommand().compareTo(icommand.getCommand());
    }

    public int compareTo(Object object) {
        return this.a((ICommand) object);
    }
}
