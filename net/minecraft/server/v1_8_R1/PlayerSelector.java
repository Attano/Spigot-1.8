package net.minecraft.server;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerSelector {

    private static final Pattern a = Pattern.compile("^@([pare])(?:\\[([\\w=,!-]*)\\])?$");
    private static final Pattern b = Pattern.compile("\\G([-!]?[\\w-]*)(?:$|,)");
    private static final Pattern c = Pattern.compile("\\G(\\w+)=([-!]?[\\w-]*)(?:$|,)");
    private static final Set d = Sets.newHashSet(new String[] { "x", "y", "z", "dx", "dy", "dz", "rm", "r"});

    public static EntityPlayer getPlayer(ICommandListener icommandlistener, String s) {
        return (EntityPlayer) getEntity(icommandlistener, s, EntityPlayer.class);
    }

    public static Entity getEntity(ICommandListener icommandlistener, String s, Class oclass) {
        List list = getPlayers(icommandlistener, s, oclass);

        return list.size() == 1 ? (Entity) list.get(0) : null;
    }

    public static IChatBaseComponent getPlayerNames(ICommandListener icommandlistener, String s) {
        List list = getPlayers(icommandlistener, s, Entity.class);

        if (list.isEmpty()) {
            return null;
        } else {
            ArrayList arraylist = Lists.newArrayList();
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                Entity entity = (Entity) iterator.next();

                arraylist.add(entity.getScoreboardDisplayName());
            }

            return CommandAbstract.a((List) arraylist);
        }
    }

    public static List getPlayers(ICommandListener icommandlistener, String s, Class oclass) {
        // CraftBukkit start - disable playerselections for ICommandListeners other than command blocks
        if (!(icommandlistener instanceof CommandBlockListenerAbstract)) {
            return com.google.common.collect.ImmutableList.of();
        }
        // CraftBukkit end
        Matcher matcher = PlayerSelector.a.matcher(s);

        if (matcher.matches() && icommandlistener.a(1, "@")) {
            Map map = c(matcher.group(2));

            if (!b(icommandlistener, map)) {
                return Collections.emptyList();
            } else {
                String s1 = matcher.group(1);
                BlockPosition blockposition = b(map, icommandlistener.getChunkCoordinates());
                List list = a(icommandlistener, map);
                ArrayList arraylist = Lists.newArrayList();
                Iterator iterator = list.iterator();

                while (iterator.hasNext()) {
                    World world = (World) iterator.next();

                    if (world != null) {
                        ArrayList arraylist1 = Lists.newArrayList();

                        arraylist1.addAll(a(map, s1));
                        arraylist1.addAll(b(map));
                        arraylist1.addAll(c(map));
                        arraylist1.addAll(d(map));
                        arraylist1.addAll(e(map));
                        arraylist1.addAll(f(map));
                        arraylist1.addAll(a(map, blockposition));
                        arraylist1.addAll(g(map));
                        arraylist.addAll(a(map, oclass, (List) arraylist1, s1, world, blockposition));
                    }
                }

                return a((List) arraylist, map, icommandlistener, oclass, s1, blockposition);
            }
        } else {
            return Collections.emptyList();
        }
    }

    private static List a(ICommandListener icommandlistener, Map map) {
        ArrayList arraylist = Lists.newArrayList();

        if (h(map)) {
            arraylist.add(icommandlistener.getWorld());
        } else {
            Collections.addAll(arraylist, MinecraftServer.getServer().worldServer);
        }

        return arraylist;
    }

    private static boolean b(ICommandListener icommandlistener, Map map) {
        String s = b(map, "type");

        s = s != null && s.startsWith("!") ? s.substring(1) : s;
        if (s != null && !EntityTypes.b(s)) {
            ChatMessage chatmessage = new ChatMessage("commands.generic.entity.invalidType", new Object[] { s});

            chatmessage.getChatModifier().setColor(EnumChatFormat.RED);
            icommandlistener.sendMessage(chatmessage);
            return false;
        } else {
            return true;
        }
    }

    private static List a(Map map, String s) {
        ArrayList arraylist = Lists.newArrayList();
        String s1 = b(map, "type");
        boolean flag = s1 != null && s1.startsWith("!");

        if (flag) {
            s1 = s1.substring(1);
        }

        boolean flag1 = !s.equals("e");
        boolean flag2 = s.equals("r") && s1 != null;

        if ((s1 == null || !s.equals("e")) && !flag2) {
            if (flag1) {
                arraylist.add(new EntitySelectorHuman());
            }
        } else {
            arraylist.add(new PlayerSelectorInnerClass1(s1, flag));
        }

        return arraylist;
    }

    private static List b(Map map) {
        ArrayList arraylist = Lists.newArrayList();
        int i = a(map, "lm", -1);
        int j = a(map, "l", -1);

        if (i > -1 || j > -1) {
            arraylist.add(new PlayerSelectorInnerClass2(i, j));
        }

        return arraylist;
    }

    private static List c(Map map) {
        ArrayList arraylist = Lists.newArrayList();
        int i = a(map, "m", EnumGamemode.NOT_SET.getId());

        if (i != EnumGamemode.NOT_SET.getId()) {
            arraylist.add(new PlayerSelectorInnerClass3(i));
        }

        return arraylist;
    }

    private static List d(Map map) {
        ArrayList arraylist = Lists.newArrayList();
        String s = b(map, "team");
        boolean flag = s != null && s.startsWith("!");

        if (flag) {
            s = s.substring(1);
        }

        if (s != null) {
            arraylist.add(new PlayerSelectorInnerClass4(s, flag));
        }

        return arraylist;
    }

    private static List e(Map map) {
        ArrayList arraylist = Lists.newArrayList();
        Map map1 = a(map);

        if (map1 != null && map1.size() > 0) {
            arraylist.add(new PlayerSelectorInnerClass5(map1));
        }

        return arraylist;
    }

    private static List f(Map map) {
        ArrayList arraylist = Lists.newArrayList();
        String s = b(map, "name");
        boolean flag = s != null && s.startsWith("!");

        if (flag) {
            s = s.substring(1);
        }

        if (s != null) {
            arraylist.add(new PlayerSelectorInnerClass6(s, flag));
        }

        return arraylist;
    }

    private static List a(Map map, BlockPosition blockposition) {
        ArrayList arraylist = Lists.newArrayList();
        int i = a(map, "rm", -1);
        int j = a(map, "r", -1);

        if (blockposition != null && (i >= 0 || j >= 0)) {
            int k = i * i;
            int l = j * j;

            arraylist.add(new PlayerSelectorInnerClass7(blockposition, i, k, j, l));
        }

        return arraylist;
    }

    private static List g(Map map) {
        ArrayList arraylist = Lists.newArrayList();
        int i;
        int j;

        if (map.containsKey("rym") || map.containsKey("ry")) {
            i = a(a(map, "rym", 0));
            j = a(a(map, "ry", 359));
            arraylist.add(new PlayerSelectorInnerClass8(i, j));
        }

        if (map.containsKey("rxm") || map.containsKey("rx")) {
            i = a(a(map, "rxm", 0));
            j = a(a(map, "rx", 359));
            arraylist.add(new PlayerSelectorInnerClass9(i, j));
        }

        return arraylist;
    }

    private static List a(Map map, Class oclass, List list, String s, World world, BlockPosition blockposition) {
        ArrayList arraylist = Lists.newArrayList();
        String s1 = b(map, "type");

        s1 = s1 != null && s1.startsWith("!") ? s1.substring(1) : s1;
        boolean flag = !s.equals("e");
        boolean flag1 = s.equals("r") && s1 != null;
        int i = a(map, "dx", 0);
        int j = a(map, "dy", 0);
        int k = a(map, "dz", 0);
        int l = a(map, "r", -1);
        Predicate predicate = Predicates.and(list);
        Predicate predicate1 = Predicates.and(IEntitySelector.a, predicate);

        if (blockposition != null) {
            int i1 = world.players.size();
            int j1 = world.entityList.size();
            boolean flag2 = i1 < j1 / 16;
            AxisAlignedBB axisalignedbb;

            if (!map.containsKey("dx") && !map.containsKey("dy") && !map.containsKey("dz")) {
                if (l >= 0) {
                    axisalignedbb = new AxisAlignedBB((double) (blockposition.getX() - l), (double) (blockposition.getY() - l), (double) (blockposition.getZ() - l), (double) (blockposition.getX() + l + 1), (double) (blockposition.getY() + l + 1), (double) (blockposition.getZ() + l + 1));
                    if (flag && flag2 && !flag1) {
                        arraylist.addAll(world.b(oclass, predicate1));
                    } else {
                        arraylist.addAll(world.a(oclass, axisalignedbb, predicate1));
                    }
                } else if (s.equals("a")) {
                    arraylist.addAll(world.b(oclass, predicate));
                } else if (!s.equals("p") && (!s.equals("r") || flag1)) {
                    arraylist.addAll(world.a(oclass, predicate1));
                } else {
                    arraylist.addAll(world.b(oclass, predicate1));
                }
            } else {
                axisalignedbb = a(blockposition, i, j, k);
                if (flag && flag2 && !flag1) {
                    PlayerSelectorInnerClassBoundsChecker playerselectorinnerclassboundschecker = new PlayerSelectorInnerClassBoundsChecker(axisalignedbb);

                    arraylist.addAll(world.b(oclass, Predicates.and(predicate1, playerselectorinnerclassboundschecker)));
                } else {
                    arraylist.addAll(world.a(oclass, axisalignedbb, predicate1));
                }
            }
        } else if (s.equals("a")) {
            arraylist.addAll(world.b(oclass, predicate));
        } else if (!s.equals("p") && (!s.equals("r") || flag1)) {
            arraylist.addAll(world.a(oclass, predicate1));
        } else {
            arraylist.addAll(world.b(oclass, predicate1));
        }

        return arraylist;
    }

    private static List a(List list, Map map, ICommandListener icommandlistener, Class oclass, String s, BlockPosition blockposition) {
        int i = a(map, "c", !s.equals("a") && !s.equals("e") ? 1 : 0);

        if (!s.equals("p") && !s.equals("a") && !s.equals("e")) {
            if (s.equals("r")) {
                Collections.shuffle((List) list);
            }
        } else if (blockposition != null) {
            Collections.sort((List) list, new PlayerSelectorInnerClass10(blockposition));
        }

        Entity entity = icommandlistener.f();

        if (entity != null && oclass.isAssignableFrom(entity.getClass()) && i == 1 && ((List) list).contains(entity) && !"r".equals(s)) {
            list = Lists.newArrayList(new Entity[] { entity});
        }

        if (i != 0) {
            if (i < 0) {
                Collections.reverse((List) list);
            }

            list = ((List) list).subList(0, Math.min(Math.abs(i), ((List) list).size()));
        }

        return (List) list;
    }

    private static AxisAlignedBB a(BlockPosition blockposition, int i, int j, int k) {
        boolean flag = i < 0;
        boolean flag1 = j < 0;
        boolean flag2 = k < 0;
        int l = blockposition.getX() + (flag ? i : 0);
        int i1 = blockposition.getY() + (flag1 ? j : 0);
        int j1 = blockposition.getZ() + (flag2 ? k : 0);
        int k1 = blockposition.getX() + (flag ? 0 : i) + 1;
        int l1 = blockposition.getY() + (flag1 ? 0 : j) + 1;
        int i2 = blockposition.getZ() + (flag2 ? 0 : k) + 1;

        return new AxisAlignedBB((double) l, (double) i1, (double) j1, (double) k1, (double) l1, (double) i2);
    }

    public static int a(int i) {
        i %= 360;
        if (i >= 160) {
            i -= 360;
        }

        if (i < 0) {
            i += 360;
        }

        return i;
    }

    private static BlockPosition b(Map map, BlockPosition blockposition) {
        return new BlockPosition(a(map, "x", blockposition.getX()), a(map, "y", blockposition.getY()), a(map, "z", blockposition.getZ()));
    }

    private static boolean h(Map map) {
        Iterator iterator = PlayerSelector.d.iterator();

        String s;

        do {
            if (!iterator.hasNext()) {
                return false;
            }

            s = (String) iterator.next();
        } while (!map.containsKey(s));

        return true;
    }

    private static int a(Map map, String s, int i) {
        return map.containsKey(s) ? MathHelper.a((String) map.get(s), i) : i;
    }

    private static String b(Map map, String s) {
        return (String) map.get(s);
    }

    public static Map a(Map map) {
        HashMap hashmap = Maps.newHashMap();
        Iterator iterator = map.keySet().iterator();

        while (iterator.hasNext()) {
            String s = (String) iterator.next();

            if (s.startsWith("score_") && s.length() > "score_".length()) {
                hashmap.put(s.substring("score_".length()), Integer.valueOf(MathHelper.a((String) map.get(s), 1)));
            }
        }

        return hashmap;
    }

    public static boolean isList(String s) {
        Matcher matcher = PlayerSelector.a.matcher(s);

        if (!matcher.matches()) {
            return false;
        } else {
            Map map = c(matcher.group(2));
            String s1 = matcher.group(1);
            int i = !"a".equals(s1) && !"e".equals(s1) ? 1 : 0;

            return a(map, "c", i) != 1;
        }
    }

    public static boolean isPattern(String s) {
        return PlayerSelector.a.matcher(s).matches();
    }

    private static Map c(String s) {
        HashMap hashmap = Maps.newHashMap();

        if (s == null) {
            return hashmap;
        } else {
            int i = 0;
            int j = -1;

            for (Matcher matcher = PlayerSelector.b.matcher(s); matcher.find(); j = matcher.end()) {
                String s1 = null;

                switch (i++) {
                case 0:
                    s1 = "x";
                    break;

                case 1:
                    s1 = "y";
                    break;

                case 2:
                    s1 = "z";
                    break;

                case 3:
                    s1 = "r";
                }

                if (s1 != null && matcher.group(1).length() > 0) {
                    hashmap.put(s1, matcher.group(1));
                }
            }

            if (j < s.length()) {
                Matcher matcher1 = PlayerSelector.c.matcher(j == -1 ? s : s.substring(j));

                while (matcher1.find()) {
                    hashmap.put(matcher1.group(1), matcher1.group(2));
                }
            }

            return hashmap;
        }
    }
}
