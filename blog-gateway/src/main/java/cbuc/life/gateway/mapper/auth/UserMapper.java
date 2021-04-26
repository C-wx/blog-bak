package cbuc.life.gateway.mapper.auth;

import cbuc.life.common.entity.auth.Resource;
import cbuc.life.common.entity.auth.Role;
import cbuc.life.common.entity.auth.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author: cbuc
 * @date: 2021-04-19 21:58
 * @description: user mapper
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户ID获取角色列表
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    @Select("SELECT role_name FROM cb_role r INNER JOIN cb_user_role ur ON r.id = ur.role_id WHERE ur.user_id = #{userId}")
    List<Role> getRolesByUserId(Integer userId);

    /**
     * 根据角色ID获取菜单列表
     *
     * @param roleId 角色ID
     * @return 菜单列表
     */
    @Select("SELECT resource_name FROM cb_resource r INNER JOIN cb_role_resource rr ON r.id = rr.resource_id WHERE rr.role_id = #{roleId}")
    Set<Resource> getResourcesByRoleId(Integer roleId);


}
