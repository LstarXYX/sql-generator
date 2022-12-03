package com.lstar.sql_generator_server.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页请求
 */
@Data
@ApiModel(value = "分页请求")
public class Pager
{
	/**
	 * 升序
	 */
	public static final String SORT_ORDER_ASC = "ASC";

	/**
	 * 降序
	 */
	public static final String SORT_ORDER_DESC = "DESC";

	/**
	 * 当前页号
	 */
	@ApiModelProperty(value = "当前页号")
	private long current = 1;

	/**
	 * 页面大小
	 */
	@ApiModelProperty(value = "页面大小")
	private long pageSize = 10;

	@ApiModelProperty(value = "排序字段")
	private String sortField;

	@ApiModelProperty(value = "排序顺序（默认升序）")
	private String sortOrder = SORT_ORDER_ASC;
}
