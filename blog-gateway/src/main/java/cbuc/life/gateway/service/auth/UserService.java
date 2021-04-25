package cbuc.life.gateway.service.auth;

import cbuc.life.common.entity.auth.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author: cbuc
 * @data: 2021-04-19 20:38
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
}
