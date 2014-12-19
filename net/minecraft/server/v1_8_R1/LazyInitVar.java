package net.minecraft.server;

public abstract class LazyInitVar {

    private Object a;
    private boolean b = false;

    public LazyInitVar() {}

    public Object c() {
        if (!this.b) {
            this.b = true;
            this.a = this.init();
        }

        return this.a;
    }

    protected abstract Object init();
}
