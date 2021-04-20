package cbuc.life.gateway.config.shiro;

import cbuc.life.common.entity.enums.ResponseEnum;
import cbuc.life.common.entity.result.Result;
import cbuc.life.gateway.util.JwtUtil;
import cbuc.life.gateway.util.LoginUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.http.HttpMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * @Author: cwx
 * @Date: Create in 20:33 2021/4/20
 * @Description:
 */
public class AuthenticationFilter extends FormAuthenticationFilter {
    public AuthenticationFilter() {
        super();
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest servletRequest = (HttpServletRequest) request;
            if (HttpMethod.OPTIONS.matches(servletRequest.getMethod())) {
                return true;
            }
            String token = JwtUtil.getRequestToken(servletRequest);
            String userName = Optional.ofNullable(LoginUtil.getUser().getUserName()).orElseGet(() -> JwtUtil.getUsername(token));
            if (StringUtils.isNotBlank(userName)) {
                return true;
            }
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse res = (HttpServletResponse) response;
        Result result = Result.set(ResponseEnum.NOT_LOGIN);
        res.setCharacterEncoding("UTF-8");
        res.setContentType("application/json;charset=UTF-8");
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setStatus(HttpServletResponse.SC_OK);
        String resultStr = JSON.toJSONString(result);
        IOUtils.copy(new ByteArrayInputStream(resultStr.getBytes(StandardCharsets.UTF_8)), res.getOutputStream());
        return false;
    }


}
