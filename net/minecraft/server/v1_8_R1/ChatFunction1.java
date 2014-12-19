package net.minecraft.server;

import com.google.common.base.Function;
import java.util.Iterator;

final class ChatFunction1 implements Function {

    ChatFunction1() {}

    public Iterator a(IChatBaseComponent ichatbasecomponent) {
        return ichatbasecomponent.iterator();
    }

    public Object apply(Object object) {
        return this.a((IChatBaseComponent) object);
    }
}
