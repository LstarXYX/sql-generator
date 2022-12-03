package com.lstar.sql_generator_server.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("user")
public class User implements Serializable
{
	private static final long serialVersionUID = 2458729421613308551L;

	/**
	 * 登录状态 key
	 */
	public static final String KEY_USER_LOGIN_STATUS = "userLoginStatus";

	public static final int SYSTEM_USER_ID = 0;

	public static final String ROLE_DEFAULT = "user";
	public static final String ROLE_ADMIN = "admin";

	@TableId(value = "rec_id", type = IdType.AUTO)
	private Integer recId;

	private String userName;
	private String password;
	private String role;

	@TableLogic(value = "is_deleted", delval = "0")
	private Boolean isDeleted;

	private Date modified;
	private Date created;
}
