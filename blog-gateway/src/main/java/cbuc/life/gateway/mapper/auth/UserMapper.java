package cbuc.life.gateway.mapper.auth;

import cbuc.life.common.entity.auth.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author: cbuc
 * @data: 2021-04-19 21:58
 * @description: user mapper
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
