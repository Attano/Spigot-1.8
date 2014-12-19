package net.minecraft.server;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class RemoteStatusReply {

    private ByteArrayOutputStream buffer;
    private DataOutputStream stream;

    public RemoteStatusReply(int i) {
        this.buffer = new ByteArrayOutputStream(i);
        this.stream = new DataOutputStream(this.buffer);
    }

    public void a(byte[] abyte) {
        this.stream.write(abyte, 0, abyte.length);
    }

    public void a(String s) {
        this.stream.writeBytes(s);
        this.stream.write(0);
    }

    public void a(int i) {
        this.stream.write(i);
    }

    public void a(short short0) {
        this.stream.writeShort(Short.reverseBytes(short0));
    }

    public byte[] a() {
        return this.buffer.toByteArray();
    }

    public void b() {
        this.buffer.reset();
    }
}
