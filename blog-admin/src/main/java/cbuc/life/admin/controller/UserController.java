package cbuc.life.admin.controller;

import cbuc.life.common.entity.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: cbuc
 * @data: 2021-04-18 18:11
 * @description:
 */
@RestController
@RequestMapping("/userApi")
public class UserController {


    @GetMapping("/login")
    @ApiOperation(value = "登录接口")
    public Result login() {
        return Result.success();
    }


}
