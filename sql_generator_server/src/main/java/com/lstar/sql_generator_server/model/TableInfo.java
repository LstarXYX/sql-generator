package com.lstar.sql_generator_server.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 表信息
 * @TableName table_info
 */
@TableName(value ="table_info")
@Data
public class TableInfo implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer recId;

    /**
     * 名称
     */
    private String name;

    /**
     * 字段名称
     */
    private String fieldname;

    /**
     * 字段信息（json）
     */
    private String content;

    /**
     * 状态 0-待审核 1-通过 2-拒绝
     */
    private Byte status;

    /**
     * 审核信息
     */
    private String msg;

    /**
     * 创建的用户id
     */
    private Integer userId;

    /**
     * 是否删除
     */
    @TableLogic(value = "is_deleted", delval = "0")
    private Boolean isDeleted;

    /**
     * 
     */
    private Date modified;

    /**
     * 
     */
    private Date created;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}