package com.lstar.sql_generator_server.model.request;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户更新实体
 */
@Data
@ApiModel(value = "用户更新实体")
public class UserUpdateRequest implements Serializable
{
	@TableField(exist = false)
	private static final long serialVersionUID = 969251559628739432L;

	@ApiModelProperty(value = "id")
	private Integer recId;

	@ApiModelProperty(value = "用户名")
	private String userName;

	@ApiModelProperty(value = "角色")
	private String role;

	@ApiModelProperty(value = "密码")
	private String password;


}
