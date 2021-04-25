package cbuc.life.gateway.controller.auth;

import cbuc.life.common.entity.auth.User;
import cbuc.life.common.entity.result.Result;
import cbuc.life.gateway.service.auth.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


/**
 * @author: cbuc
 * @data: 2021-04-18 18:11
 * @description:
 */
@CrossOrigin
@RestController
@RequestMapping("/userApi")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    @ApiOperation("登录接口")
    public Result login(@RequestBody User user) {
        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getUserPwd())) {
            return Result.error("请输入用户名和密码");
        }
        //用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUserName(), user.getUserPwd());
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
            subject.checkRole("admin");
        } catch (UnknownAccountException e) {
            LOGGER.error("用户名不存在！", e);
            return Result.error("用户名不存在");
        } catch (AuthenticationException e) {
            LOGGER.error("账号或密码错误！", e);
            return Result.error("账号或密码错误");
        } catch (AuthorizationException e) {
            LOGGER.error("没有权限！", e);
            return Result.error("没有权限");
        }
        return Result.success(userService.getById(1));
    }

    @PostMapping("/register")
    @ApiOperation("注册接口")
    public Result register(@RequestBody User user) {
        System.out.println(user);
        return Result.success(userService.doRegister(user) == 1 ? "注册成功" : "注册失败");
    }


}
