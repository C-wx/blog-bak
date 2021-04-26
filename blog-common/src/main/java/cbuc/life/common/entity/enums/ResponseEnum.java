package cbuc.life.common.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author: cbuc
 * @date: 2021-04-18 17:40
 * @description: 响应信息枚举类
 */
@Getter
@ToString
@AllArgsConstructor
public enum ResponseEnum {

    /**
     * 成功响应
     */
    SUCCESS(200, "操作成功"),

    /**
     * 错误响应
     */
    ERROR(-101, "操作失败"),

    /**
     * 用户未登录异常响应
     */
    NOT_LOGIN(-102, "用户未登录"),

    /**
     * 认证信息为空异常响应
     */
    TOKEN_IS_NULL(-103, "认证信息不能为空"),

    /**
     * 用户名已被注册异常响应
     */
    USER_EXISTS(-104, "用户名已被注册"),

    /**
     * 用户名不能为空异常响应
     */
    USERNAME_NOT_BLANK(-105, "用户名不能为空"),

    /**
     * 登录密码不能为空异常响应
     */
    PASSWORD_NOT_BLANK(-106, "登录密码不能为空"),

    /**
     * 用户名不存在异常响应
     */
    USER_NOT_EXISTS(-107, "用户名不存在"),

    /**
     * 账号或密码错误异常响应
     */
    LOGIN_INFO_ERROR(-108, "账号或密码错误"),

    /**
     * 权限不足异常响应
     */
    PERMISSION_ERROR(-109, "登录用户权限不足"),

    ;

    /**
     * 响应状态码
     */
    private final Integer code;

    /**
     * 响应信息
     */
    private final String message;

}
