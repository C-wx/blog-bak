package cbuc.life.gateway.util;

import cbuc.life.common.entity.auth.User;
import cbuc.life.common.entity.enums.UserEnum;
import cn.hutool.core.util.ObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author: cwx
 * @date: Create in 21:00 2021/4/20
 * @description: 登录工具类
 */
public class LoginUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginUtil.class);

    /**
     * 获得用户登录信息
     *
     * @return 用户信息
     */
    public static User getUser() {
        LOGGER.info("CURRENT SESSION ID : {}", getSession().getId());
        User user = null;
        if (ObjectUtil.isNotNull(getSession())) {
            user = (User) getSession().getAttribute(UserEnum.USER_INFO.getValue());
        }
        if (ObjectUtil.isNull(user)) {
            String token = JwtUtil.getRequestToken(getRequest());
            String username = JwtUtil.getUsername(token);
            //TODO 从redis中获取
            //user = (User) redisTemplateBean.opsForValue().get(username);

        }
        return user;
    }


    /**
     * 获取当前用户session
     *
     * @return session
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取当前请求request
     *
     * @return request
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
}
