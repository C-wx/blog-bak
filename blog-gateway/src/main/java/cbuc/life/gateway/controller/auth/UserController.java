package cbuc.life.gateway.controller.auth;

import cbuc.life.common.entity.auth.User;
import cbuc.life.common.entity.enums.ResponseEnum;
import cbuc.life.common.entity.result.Result;
import cbuc.life.common.exception.Assert;
import cbuc.life.common.exception.BusinessServerException;
import cbuc.life.gateway.service.auth.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


/**
 * @author: cbuc
 * @date: 2021-04-18 18:11
 * @description: 用户控制器
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
        try {
            Assert.isNotBlank(user.getUserName(), ResponseEnum.USERNAME_NOT_BLANK);
            Assert.isNotBlank(user.getUserPwd(), ResponseEnum.PASSWORD_NOT_BLANK);
            return Result.success(userService.doLogin(user));
        } catch (BusinessServerException e) {
            throw new BusinessServerException(e.getMessage(), e.getCode());
        }
    }

    @PostMapping("/register")
    @ApiOperation("注册接口")
    public Result register(@RequestBody User user) {
        try {
            return Result.success(userService.doRegister(user) == 1 ? "注册成功" : "注册失败");
        } catch (BusinessServerException e) {
            throw new BusinessServerException(e.getMessage(), e.getCode());
        }
    }

    @GetMapping("/logout")
    @ApiOperation(("退出登录"))
    public Result logout() {

        return Result.success();
    }

}
