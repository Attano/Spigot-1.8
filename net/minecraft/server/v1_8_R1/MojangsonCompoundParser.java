package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;

class MojangsonCompoundParser extends MojangsonTypeParser {

    protected List b = Lists.newArrayList();

    public MojangsonCompoundParser(String s) {
        this.a = s;
    }

    public NBTBase a() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            MojangsonTypeParser mojangsontypeparser = (MojangsonTypeParser) iterator.next();

            nbttagcompound.set(mojangsontypeparser.a, mojangsontypeparser.a());
        }

        return nbttagcompound;
    }
}
