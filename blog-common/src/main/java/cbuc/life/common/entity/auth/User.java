package cbuc.life.common.entity.auth;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: cbuc
 * @data: 2021-04-19 20:50
 * @description:
 */
@Data
@TableName("cb_user")
@ApiModel(value = "用户对象")
public class User {

    @ApiModelProperty("主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户名")
    private Integer userName;

    @ApiModelProperty("用户密码")
    private String userPwd;

    @ApiModelProperty("用户号码")
    private String userMobile;

    @ApiModelProperty("用户邮箱")
    private String userEmail;

    @ApiModelProperty("用户头像")
    private String userPortrait;

    @ApiModelProperty("用户昵称")
    private String userNickname;

    @ApiModelProperty("用户描述")
    private String userExplain;

    @ApiModelProperty("上次登录时间")
    private LocalDateTime loginLastTime;

    @ApiModelProperty(value = "是否禁用登录", notes = "0: 禁用 1：启用")
    private String loginEnable;

}
