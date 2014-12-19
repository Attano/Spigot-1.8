package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;

class MojangsonListParser extends MojangsonTypeParser {

    protected List b = Lists.newArrayList();

    public MojangsonListParser(String s) {
        this.a = s;
    }

    public NBTBase a() {
        NBTTagList nbttaglist = new NBTTagList();
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            MojangsonTypeParser mojangsontypeparser = (MojangsonTypeParser) iterator.next();

            nbttaglist.add(mojangsontypeparser.a());
        }

        return nbttaglist;
    }
}
