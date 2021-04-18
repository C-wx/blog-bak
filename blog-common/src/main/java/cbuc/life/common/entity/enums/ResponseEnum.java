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

    ;

    /**
     * 响应状态码
     */
    private Integer code;

    /**
     * 响应信息
     */
    private String message;

}
