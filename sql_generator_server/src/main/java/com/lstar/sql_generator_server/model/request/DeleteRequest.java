package com.lstar.sql_generator_server.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "删除请求")
public class DeleteRequest implements Serializable
{
	private static final long serialVersionUID = 5964831627256612279L;

	@ApiModelProperty(value = "id")
	private Integer recId;
}
