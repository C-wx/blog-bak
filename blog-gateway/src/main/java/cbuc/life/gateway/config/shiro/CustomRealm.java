package cbuc.life.gateway.config.shiro;

import cbuc.life.common.entity.auth.Resource;
import cbuc.life.common.entity.auth.Role;
import cbuc.life.common.entity.auth.User;
import cbuc.life.gateway.service.auth.UserService;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: cbuc
 * @date: 2021-04-19 22:53
 * @description: 自定义realm
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权方法
     *
     * @param principalCollection 登录信息
     * @return 授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户
        String name = (String) principalCollection.getPrimaryPrincipal();
        User user = userService.getUserByName(name);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : user.getRoles()) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            //添加权限
            for (Resource resource : role.getResources()) {
                simpleAuthorizationInfo.addStringPermission(resource.getResourceName());
            }
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 认证方法
     *
     * @param authenticationToken 登录信息
     * @return 认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        if (ObjectUtil.isEmpty(token)) {
            return null;
        }
        //获取用户信息
        String name = token.getUsername();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", name);
        User user = userService.getOne(queryWrapper);
        if (ObjectUtil.isEmpty(user)) {
            return null;
        } else {
            return new SimpleAuthenticationInfo(name, user.getUserPwd(), ByteSource.Util.bytes(token.getPassword()), getName());
        }
    }
}
