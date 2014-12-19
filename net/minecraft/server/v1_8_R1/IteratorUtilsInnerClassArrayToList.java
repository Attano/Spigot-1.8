package net.minecraft.server;

import com.google.common.base.Function;
import java.util.Arrays;
import java.util.List;

class IteratorUtilsInnerClassArrayToList implements Function {

    private IteratorUtilsInnerClassArrayToList() {}

    public List a(Object[] aobject) {
        return Arrays.asList((Object[]) aobject);
    }

    public Object apply(Object object) {
        return this.a((Object[]) object);
    }

    IteratorUtilsInnerClassArrayToList(EmptyClass3 emptyclass3) {
        this();
    }
}
