package cbuc.life.gateway.service.auth.impl;

import cbuc.life.common.entity.auth.User;
import cbuc.life.common.entity.enums.ResponseEnum;
import cbuc.life.common.exception.Assert;
import cbuc.life.gateway.mapper.auth.UserMapper;
import cbuc.life.gateway.service.auth.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

/**
 * @author: cbuc
 * @data: 2021-04-19 20:39
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

}
