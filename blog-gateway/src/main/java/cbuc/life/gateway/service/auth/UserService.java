package cbuc.life.gateway.service.auth;

import cbuc.life.common.entity.auth.Resource;
import cbuc.life.common.entity.auth.Role;
import cbuc.life.common.entity.auth.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * @author: cbuc
 * @date: 2021-04-19 20:38
 * @description: user service
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return 处理状态
     */
    Integer doRegister(User user);

    /**
     * 用户登录
     *
     * @param user 用户信息
     * @return 登录token
     */
    String doLogin(User user);

    /**
     * 根据用户名获取用户信息
     *
     * @param name 用户名
     * @return 用户信息
     */
    User getUserByName(String name);

    /**
     * 根据用户ID获取角色列表
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    List<Role> getRolesByUserId(Integer userId);

    /**
     * 根据角色ID获取菜单列表
     *
     * @param roleId 角色ID
     * @return 菜单列表
     */
    Set<Resource> getResourcesByRoleId(Integer roleId);
}
