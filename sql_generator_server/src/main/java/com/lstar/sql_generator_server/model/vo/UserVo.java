package com.lstar.sql_generator_server.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@ApiModel("用户视图")
@Data
public class UserVo implements Serializable
{
	private static final long serialVersionUID = 5970676356480902225L;

	@ApiModelProperty("id")
	private Integer recId;

	@ApiModelProperty("用户名")
	private String userName;

	@ApiModelProperty("角色")
	private String role;

	@ApiModelProperty("创建时间")
	private Date created;

	@ApiModelProperty("更新时间")
	private Date modified;
}
