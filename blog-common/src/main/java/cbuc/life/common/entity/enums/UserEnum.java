package cbuc.life.common.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @Author: cwx
 * @Date: Create in 21:05 2021/4/20
 * @Description: 用户枚举类
 */
@Getter
@ToString
@AllArgsConstructor
public enum UserEnum {

    /**
     * 用户信息
     */
    USER_INFO("USER");

    private final String value;


}
