package org.example.security.auth.cache;

import org.example.security.auth.constant.CacheName;

/**
 * <p>
 *
 * </p>
 *
 * @author 张笑生
 * @since 2020-06-20 22:55
 */
public interface Cache {

    <T> T get(CacheName cacheName, String key, Class<T> clazz);

    void put(CacheName cacheName, String key, Object value);

    void remove(CacheName cacheName, String key);
}
