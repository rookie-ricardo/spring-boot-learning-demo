package org.example.security.auth.constant;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public enum CacheName {

    USER("USER"),
    PERMISSION("PERMISSION");

    private final String cacheName;

    public static List<String> getCacheNames() {
        List<String> cacheNameList = new ArrayList<>(CacheName.values().length);
        CacheName[] values = CacheName.values();
        for (int i = 0; i < CacheName.values().length; i++) {
            cacheNameList.add(values[i].cacheName);
        }
        return cacheNameList;
    }

    public String getCacheName() {
        return cacheName;
    }
}
