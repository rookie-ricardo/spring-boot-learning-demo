package org.example.security.auth.component;


import org.example.security.auth.bo.PermissionInfoBO;
import org.example.security.auth.cache.Cache;
import org.example.security.auth.constant.CacheName;
import org.example.security.auth.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class InitProcessor {
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private Cache caffeineCache;

    @PostConstruct
    public void init() {
        List<PermissionInfoBO> permissionInfoList = permissionService.listPermissionInfoBO();
        permissionInfoList.forEach(permissionInfo -> {
            caffeineCache.put(CacheName.PERMISSION, permissionInfo.getPermissionUri() + ":" + permissionInfo.getPermissionMethod(), permissionInfo);
        });
    }
}
