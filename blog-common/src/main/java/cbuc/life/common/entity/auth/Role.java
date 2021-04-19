package cbuc.life.common.entity.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author: cbuc
 * @data: 2021-04-19 23:04
 * @description: 用户角色类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户角色类")
public class Role {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("角色对应权限集合")
    private Set<Permissions> permissions;

}
