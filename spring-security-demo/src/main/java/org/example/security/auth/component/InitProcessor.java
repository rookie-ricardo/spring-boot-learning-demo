package org.example.security.auth.component;


import org.example.security.auth.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitProcessor {
    @Autowired
    private Cache caffeineCache;

    @PostConstruct
    public void init(){


    }
}
