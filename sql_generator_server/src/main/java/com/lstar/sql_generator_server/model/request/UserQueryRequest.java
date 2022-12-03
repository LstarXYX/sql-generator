package com.lstar.sql_generator_server.model.request;

import com.lstar.sql_generator_server.model.Pager;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户查询请求体
 */
@Data
@ApiModel(value = "用户查询请求实体", parent = Pager.class)
@EqualsAndHashCode(callSuper = true)
public class UserQueryRequest extends Pager implements Serializable
{
	private static final long serialVersionUID = 5763477073791467228L;

	/**
	 * id
	 */
	@ApiModelProperty(value = "id")
	private Integer recId;

	/**
	 * 用户账号
	 */
	@ApiModelProperty(value = "账号")
	private String userName;

	/**
	 * 用户角色: user, admin
	 */
	@ApiModelProperty(value = "用户角色")
	private String role;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private Date created;

	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "更新时间")
	private Date modified;

}
