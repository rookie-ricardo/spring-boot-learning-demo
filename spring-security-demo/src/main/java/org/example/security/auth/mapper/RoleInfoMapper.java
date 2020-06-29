package org.example.security.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.security.auth.entity.RoleInfo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangyanqi
 * @since 2020-06-13
 */
public interface RoleInfoMapper extends BaseMapper<RoleInfo> {

    List<RoleInfo> listRoleByUserId(String userId);

}
