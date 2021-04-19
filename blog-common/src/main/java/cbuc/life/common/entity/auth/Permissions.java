package cbuc.life.common.entity.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: cbuc
 * @data: 2021-04-19 23:05
 * @description: 用户权限类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户权限类")
public class Permissions {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("权限名称")
    private String permissionsName;
}
