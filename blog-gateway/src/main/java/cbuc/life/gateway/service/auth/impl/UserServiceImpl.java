package cbuc.life.gateway.service.auth.impl;

import cbuc.life.common.entity.auth.User;
import cbuc.life.gateway.mapper.auth.UserMapper;
import cbuc.life.gateway.service.auth.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author: cbuc
 * @data: 2021-04-19 20:39
 * @description: user service 实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
