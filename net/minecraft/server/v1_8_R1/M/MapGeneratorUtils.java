package net.minecraft.server;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class MapGeneratorUtils {

    public static Map b(Iterable iterable, Iterable iterable1) {
        return a(iterable, iterable1, Maps.newLinkedHashMap());
    }

    public static Map a(Iterable iterable, Iterable iterable1, Map map) {
        Iterator iterator = iterable1.iterator();
        Iterator iterator1 = iterable.iterator();

        while (iterator1.hasNext()) {
            Object object = iterator1.next();

            map.put(object, iterator.next());
        }

        if (iterator.hasNext()) {
            throw new NoSuchElementException();
        } else {
            return map;
        }
    }
}
