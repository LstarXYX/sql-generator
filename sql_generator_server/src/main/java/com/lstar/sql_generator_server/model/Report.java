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
 * 举报
 * @TableName report
 */
@TableName(value ="report")
@Data
public class Report implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer recId;

    /**
     * 内容
     */
    private String content;

    /**
     * 举报实体类型（0-词库）
     */
    private Integer type;

    /**
     * 被举报对象 id
     */
    private Integer reportedId;

    /**
     * 被举报用户 id
     */
    private Integer reportedUserId;

    /**
     * 状态（0-未处理, 1-已处理）
     */
    private Boolean status;

    /**
     * 创建用户 id
     */
    private Long userid;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 更新时间
     */
    private Date modified;

    /**
     * 是否删除
     */
    @TableLogic(value = "is_deleted", delval = "0")
    private Boolean isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}