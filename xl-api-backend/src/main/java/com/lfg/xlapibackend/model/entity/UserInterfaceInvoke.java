package com.lfg.xlapibackend.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lfg
 * @Date: 2023/09/04 11:30:31
 * @Version: 1.0
 * @Description: 用户接口调用表
 */
@Schema(description = ": 用户接口调用表")
@TableName(value = "user_interface_invoke")
@Data
public class UserInterfaceInvoke implements Serializable {
    @Schema(hidden = true)
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @Schema(description = "id")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 调用人id
     */
    @Schema(description = "调用人id")
    private Long userId;
    /**
     * 接口id
     */
    @Schema(description = "接口id")
    private Long interfaceId;
    /**
     * 总调用次数
     */
    @Schema(description = "总调用次数")
    private Long totalInvokes;
    /**
     * 调用状态（0- 正常 1- 封号）
     */
    @Schema(description = "调用状态（0- 正常 1- 封号）")
    private Integer status;
    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private Date createTime;
    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private Date updateTime;
    /**
     * 是否删除
     */
    @Schema(description = "是否删除")
    @TableLogic
    private Integer isDelete;
}