package com.tutorial.SpringTutorial.cache;

public interface CacheValueAdapter {
    public String getIdentifier();

    public byte[] serialize(Object obj);

    public Object deserialize(byte[] data);
}
