package org.example.security.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.security.auth.entity.UserInfo;
import org.example.security.auth.mapper.UserMapper;
import org.example.security.auth.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * User服务实现类
 * </p>
 *
 * @author 和耳朵
 * @since 2020-06-30
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserInfo> implements UserService {

}
