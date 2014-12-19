package net.minecraft.server;

public class PacketPlayInChat implements Packet {

    private String a;

    public PacketPlayInChat() {}

    public PacketPlayInChat(String s) {
        if (s.length() > 100) {
            s = s.substring(0, 100);
        }

        this.a = s;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.c(100);
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.a(this.a);
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public String a() {
        return this.a;
    }

    // Spigot Start
    private static final java.util.concurrent.ExecutorService executors = java.util.concurrent.Executors.newCachedThreadPool(
            new com.google.common.util.concurrent.ThreadFactoryBuilder().setDaemon( true ).setNameFormat( "Async Chat Thread - #%d" ).build() );
    @Override
    public void a(final PacketListener packetlistener)
    {
        if ( !a.startsWith("/") )
        {
            executors.submit( new Runnable()
            {

                @Override
                public void run()
                {
                    PacketPlayInChat.this.a( (PacketListenerPlayIn) packetlistener );
                }
            } );
            return;
        }
        // Spigot End
        this.a((PacketListenerPlayIn) packetlistener);
    }
}
