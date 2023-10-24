package com.lfg.xlapibackend.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lfg
 * @Date: 2023/09/04 11:31:52
 * @Version: 1.0
 * @Description: 用户
 */
@Schema(description = ": 用户")
@TableName(value = "user")
@Data
public class User implements Serializable {
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
     * 用户昵称
     */
    @Schema(description = "用户昵称")
    private String userName;
    /**
     * 用户昵称
     */
    @Schema(description = "用户昵称")
    private String email;
    /**
     * 账号
     */
    @Schema(description = "账号")
    private String userAccount;

    /**
     * 账号状态（0- 正常 1- 封号）
     */
    @Schema(description = "账号状态（0- 正常 1- 封号）")
    private Integer status;

    /**
     * 邀请码
     */
    @Schema(description = "邀请码")
    private String invitationCode;
    /**
     * 访问密钥
     */
    @Schema(description = "访问密钥")
    private String accessKey;
    /**
     * 钱包余额（分）
     */
    @Schema(description = "钱包余额（分）")
    private Integer balance;
    /**
     * 秘密密钥
     */
    @Schema(description = "秘密密钥")
    private String secretKey;
    /**
     * 用户头像
     */
    @Schema(description = "用户头像")
    private String userAvatar;
    /**
     * 性别
     */
    @Schema(description = "性别")
    private String gender;
    /**
     * 用户角色: user, admin
     */
    @Schema(description = "用户角色: user, admin")
    private String userRole;
    /**
     * 密码
     */
    @Schema(description = "密码")
    private String userPassword;
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