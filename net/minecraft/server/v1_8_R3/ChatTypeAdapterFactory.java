package net.minecraft.server;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

public class ChatTypeAdapterFactory implements TypeAdapterFactory {

    public ChatTypeAdapterFactory() {}

    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typetoken) {
        Class oclass = typetoken.getRawType();

        if (!oclass.isEnum()) {
            return null;
        } else {
            final HashMap hashmap = Maps.newHashMap();
            Object[] aobject = oclass.getEnumConstants();
            int i = aobject.length;

            for (int j = 0; j < i; ++j) {
                Object object = aobject[j];

                hashmap.put(this.a(object), object);
            }

            return new TypeAdapter() {
                public void write(JsonWriter jsonwriter, T t0) throws IOException {
                    if (t0 == null) {
                        jsonwriter.nullValue();
                    } else {
                        jsonwriter.value(ChatTypeAdapterFactory.this.a(t0));
                    }

                }

                public T read(JsonReader jsonreader) throws IOException {
                    if (jsonreader.peek() == JsonToken.NULL) {
                        jsonreader.nextNull();
                        return null;
                    } else {
                        return hashmap.get(jsonreader.nextString());
                    }
                }
            };
        }
    }

    private String a(Object object) {
        return object instanceof Enum ? ((Enum) object).name().toLowerCase(Locale.US) : object.toString().toLowerCase(Locale.US);
    }
}
