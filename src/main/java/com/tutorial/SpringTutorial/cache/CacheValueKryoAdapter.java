package com.tutorial.SpringTutorial.cache;

import java.util.function.Consumer;

public class CacheValueKryoAdapter implements CacheValueAdapter {
    public CacheValueKryoAdapter(String name, Consumer<String> kryoRegistration) {
        System.out.println("CacheValueKryoAdapter constructor call ...");
        this.serialize("Serialize Execute !!");
    }

    @Override
    public String getIdentifier() {
        return null;
    }

    @Override
    public byte[] serialize(Object obj) {
        System.out.println(obj.toString());
        return new byte[0];
    }

    @Override
    public Object deserialize(byte[] data) {
        return null;
    }
}
