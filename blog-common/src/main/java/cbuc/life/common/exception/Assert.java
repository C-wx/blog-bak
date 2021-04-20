package cbuc.life.common.exception;

import cbuc.life.common.entity.enums.ResponseEnum;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @Author: cwx
 * @Date: Create in 19:20 2021/4/20
 * @Description: 异常断言
 */
public class Assert {

    private static final Logger LOGGER = LoggerFactory.getLogger(Assert.class);

    /**
     * 断言集合不能为空
     *
     * @param list         需断言集合
     * @param responseEnum 异常响应枚举
     */
    public static void isNotEmpty(List list, ResponseEnum responseEnum) {
        if (CollectionUtils.isEmpty(list)) {
            LOGGER.error("集合为空 | 原因: {}", responseEnum.getMessage());
            throw new BusinessServerException(responseEnum);
        }
    }

    /**
     * 断言集合为空
     *
     * @param list         需断言集合
     * @param responseEnum 异常响应枚举
     */
    public static void isEmpty(List list, ResponseEnum responseEnum) {
        if (CollectionUtils.isNotEmpty(list)) {
            LOGGER.error("集合不为空 | 原因: {}, 数据: {}", responseEnum.getMessage(), JSON.toJSONString(list));
            throw new BusinessServerException(responseEnum);
        }
    }

    /**
     * 断言字符串不能为空
     *
     * @param str          需断言字符串
     * @param responseEnum 异常响应枚举
     */
    public static void isNotBlank(String str, ResponseEnum responseEnum) {
        if (StringUtils.isBlank(str)) {
            LOGGER.error("字符串为空 | 原因: {}", responseEnum.getMessage());
            throw new BusinessServerException(responseEnum);
        }
    }

    /**
     * 断言字符串为空
     *
     * @param str          需断言字符串
     * @param responseEnum 异常响应枚举
     */
    public static void isBlank(String str, ResponseEnum responseEnum) {
        if (StringUtils.isNotBlank(str)) {
            LOGGER.error("字符串不为空 | 原因: {}, 数据: {}", responseEnum.getMessage(), str);
            throw new BusinessServerException(responseEnum);
        }
    }


    /**
     * 断言表达式是否为真
     *
     * @param expression   是否成功
     * @param responseEnum 异常响应枚举
     */
    public static void isTrue(boolean expression, ResponseEnum responseEnum) {
        if (!expression) {
            LOGGER.error("表达式不为真 | 原因: {}", responseEnum.getMessage());
            throw new BusinessServerException(responseEnum);
        }
    }

    /**
     * 断言对象不为空
     *
     * @param obj          需断言对象
     * @param responseEnum 异常响应枚举
     */
    public static void notNull(Object obj, ResponseEnum responseEnum) {
        if (ObjectUtil.isNull(obj)) {
            LOGGER.error("对象为空 | 原因: {}", responseEnum.getMessage());
            throw new BusinessServerException(responseEnum);
        }
    }

    /**
     * 断言对象为空
     *
     * @param obj          需断言对象
     * @param responseEnum 异常响应枚举
     */
    public static void isNull(Object obj, ResponseEnum responseEnum) {
        if (ObjectUtil.isNotNull(obj)) {
            LOGGER.error("对象不为空 | 原因: {}, 数据: {}", responseEnum.getMessage(), JSON.toJSONString(obj));
            throw new BusinessServerException(responseEnum);
        }
    }


    /**
     * 断言两个对象相等
     * 如果不相等，则抛出异常
     *
     * @param o1           对象1
     * @param o2           对象2
     * @param responseEnum 异常响应枚举
     */
    public static void equals(Object o1, Object o2, ResponseEnum responseEnum) {
        if (!ObjectUtil.equal(o1, o2)) {
            LOGGER.error("对象不相等 | 原因: {}, 数据: {} equals {}", responseEnum.getMessage(), JSON.toJSONString(o1), JSON.toJSONString(o2));
            throw new BusinessServerException(responseEnum);
        }
    }

    /**
     * 断言两个对象不相等
     * 如果不相等，则抛出异常
     *
     * @param o1           对象1
     * @param o2           对象2
     * @param responseEnum 异常响应枚举
     */
    public static void notEquals(Object o1, Object o2, ResponseEnum responseEnum) {
        if (ObjectUtil.equal(o1, o2)) {
            LOGGER.error("对象相等 | 原因: {}, 数据: {} equals {}", responseEnum.getMessage(), JSON.toJSONString(o1), JSON.toJSONString(o2));
            throw new BusinessServerException(responseEnum);
        }
    }

}
