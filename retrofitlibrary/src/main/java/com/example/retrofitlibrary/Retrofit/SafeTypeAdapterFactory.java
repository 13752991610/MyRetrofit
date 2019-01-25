package com.example.retrofitlibrary.Retrofit;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * author  Jin
 * Date:  2017/2/7.
 * version:  V1.0
 * Description:
 */
public class SafeTypeAdapterFactory implements TypeAdapterFactory {
    @Override
    public TypeAdapter create(Gson gson, final TypeToken type) {
        final TypeAdapter delegate = gson.getDelegateAdapter(this, type);
        return new TypeAdapter() {

            @Override
            public void write(JsonWriter out, Object value) throws IOException {
                try {
                    delegate.write(out, value);
                } catch (IOException e) {
                    delegate.write(out, null);
                }
            }

            @Override
            public Object read(JsonReader in) throws IOException {
                try {
                    return delegate.read(in);
                } catch (IOException e) {
                    in.skipValue();
                    return null;
                } catch (IllegalStateException e) {
                    in.skipValue();
                    return null;
                } catch (JsonSyntaxException e) {
                    in.skipValue();
                    if (type.getType() instanceof Class) {
                        try {
                            return (Object) ((Class) type.getType()).newInstance();
                        } catch (Exception e1) {

                        }
                    }
                    return null;
                }
            }
        };
    }



}
