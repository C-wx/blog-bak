package cbuc.life.common.entity.auth;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: cbuc
 * @date: 2021-04-26 21:02
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cb_resource")
@ApiModel("菜单类")
public class Resource {

    @ApiModelProperty("主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("菜单名称")
    private String resourceName;

    @ApiModelProperty("菜单源")
    private String resourceSource;

    @ApiModelProperty("菜单地址")
    private String resourceUrl;

    @ApiModelProperty("菜单图标")
    private String resourceIcon;

    @ApiModelProperty("排序权重")
    private Integer order;

}
