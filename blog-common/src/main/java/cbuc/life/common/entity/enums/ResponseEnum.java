package cbuc.life.common.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author: cbuc
 * @data: 2021-04-18 17:40
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
     * 认证信息为空异常响应类
     */
    TOKEN_IS_NULL(-103, "认证信息不能为空"),

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
