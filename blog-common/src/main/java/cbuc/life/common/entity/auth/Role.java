package cbuc.life.common.entity.auth;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author: cbuc
 * @date: 2021-04-19 23:04
 * @description: 用户角色类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cb_user")
@ApiModel("用户角色类")
public class Role {

    @ApiModelProperty("主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("角色编码")
    private String roleNo;

    @ApiModelProperty("角色描述")
    private String desc;

    @ApiModelProperty("排序权重")
    private Integer order;

    @ApiModelProperty(value = "是否禁用", notes = "0: 禁用 1：启用")
    private Integer status;

    @TableField(exist = false)
    @ApiModelProperty("角色对应权限集合")
    private Set<Resource> resources;

}
