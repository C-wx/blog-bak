package cbuc.life.common.entity.result;

import cbuc.life.common.entity.enums.ResponseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: cbuc
 * @date: 2021-04-18 16:26
 * @description: 响应结果类
 */
@Data
@ApiModel("响应结果")
public class Result {

    @ApiModelProperty("状态响应码")
    private Integer code;

    @ApiModelProperty("响应信息")
    private String message;

    @ApiModelProperty("响应数据")
    private Object data;

    /**
     * 成功响应(无内容)
     *
     * @return 响应结果
     */
    public static Result success() {
        return success(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMessage(), null);
    }

    /**
     * 成功响应(带响应数据)
     *
     * @param data 响应数据
     * @return 响应结果
     */
    public static Result success(Object data) {
        return success(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMessage(), data);
    }

    /**
     * 成功响应(带响应消息及数据)
     *
     * @param message 响应消息
     * @param data    响应数据
     * @return 响应结果
     */
    public static Result success(String message, Object data) {
        return success(ResponseEnum.SUCCESS.getCode(), message, data);
    }

    /**
     * 成功响应(自定义)
     *
     * @param responseEnum 响应结果枚举
     * @return 响应结果
     */
    public static Result set(ResponseEnum responseEnum) {
        return success(responseEnum.getCode(), responseEnum.getMessage(), null);
    }

    /**
     * 成功响应
     *
     * @param code    响应状态码
     * @param message 响应消息
     * @param data    响应实体
     * @return 响应结果
     */
    public static Result success(Integer code, String message, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    /**
     * 错误响应
     *
     * @return 响应结果
     */
    public static Result error() {
        return error(ResponseEnum.ERROR.getCode(), ResponseEnum.ERROR.getMessage(), null);
    }

    /**
     * 错误响应(带响应数据)
     *
     * @param data 响应数据
     * @return 响应结果
     */
    public static Result error(Object data) {
        return success(ResponseEnum.ERROR.getCode(), ResponseEnum.ERROR.getMessage(), data);
    }

    /**
     * 错误响应(带响应消息及数据)
     *
     * @param message 响应消息
     * @param data    响应数据
     * @return 响应结果
     */
    public static Result error(String message, Object data) {
        return success(ResponseEnum.ERROR.getCode(), message, data);
    }

    /**
     * 错误响应
     *
     * @param code    响应状态码
     * @param message 响应消息
     * @param data    响应实体
     * @return 响应结果
     */
    public static Result error(Integer code, String message, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

}
