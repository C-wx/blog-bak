package cbuc.life.gateway.service.auth.impl;

import cbuc.life.common.entity.auth.Resource;
import cbuc.life.common.entity.auth.Role;
import cbuc.life.common.entity.auth.User;
import cbuc.life.common.entity.enums.ResponseEnum;
import cbuc.life.common.exception.Assert;
import cbuc.life.common.exception.BusinessServerException;
import cbuc.life.gateway.mapper.auth.UserMapper;
import cbuc.life.gateway.service.auth.UserService;
import cbuc.life.gateway.util.JwtUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author: cbuc
 * @date: 2021-04-19 20:39
 * @description: user service 实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return 处理状态
     */
    @Override
    public Integer doRegister(User user) {
        String cipherText = getCipherText(user);
        user.setUserPwd(cipherText);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", user.getUserName());
        Assert.isNull(baseMapper.selectOne(queryWrapper), ResponseEnum.USER_EXISTS);
        return baseMapper.insert(user);
    }

    /**
     * 用户登录
     *
     * @param user 用户信息
     * @return 登录token
     */
    @Override
    public String doLogin(User user) {
        //用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUserName(), user.getUserPwd());
        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e) {
            throw new BusinessServerException(ResponseEnum.USER_NOT_EXISTS);
        } catch (AuthenticationException e) {
            throw new BusinessServerException(ResponseEnum.LOGIN_INFO_ERROR);
        } catch (AuthorizationException e) {
            throw new BusinessServerException(ResponseEnum.PERMISSION_ERROR);
        }
        return JwtUtil.createToken(user.getUserName());
    }

    /**
     * 根据用户名获取用户信息
     *
     * @param name 用户名
     * @return 用户信息
     */
    @Override
    public User getUserByName(String name) {
        //查询用户名称
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", name);
        User user = baseMapper.selectOne(queryWrapper);
        user.setRoles(getRolesByUserId(user.getId()));
        return user;
    }

    /**
     * 获取加密密文
     *
     * @param user 用户信息
     * @return 密文
     */
    public static String getCipherText(User user) {
        // 生成盐值
        ByteSource salt = ByteSource.Util.bytes(user.getUserPwd());
        //生成的密文
        return new SimpleHash("MD5", user.getUserPwd(), salt, 10).toHex();
    }

    /**
     * 根据用户ID获取角色列表
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    @Override
    public List<Role> getRolesByUserId(Integer userId) {
        List<Role> roles = baseMapper.getRolesByUserId(userId);
        roles.forEach(r -> r.setResources(getResourcesByRoleId(r.getId())));
        return roles;
    }

    /**
     * 根据角色ID获取菜单列表
     *
     * @param roleId 角色ID
     * @return 菜单列表
     */
    @Override
    public Set<Resource> getResourcesByRoleId(Integer roleId) {
        return baseMapper.getResourcesByRoleId(roleId);
    }

}
