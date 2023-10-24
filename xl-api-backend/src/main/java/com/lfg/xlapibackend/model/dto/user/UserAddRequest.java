package com.lfg.xlapibackend.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lfg
 * @Date: 2023/09/04 11:34:01
 * @Version: 1.0
 * @Description: 用户创建请求
 */
@Schema(description = ": 用户创建请求")
@Data
public class UserAddRequest implements Serializable {

    @Schema(hidden = true)
    private static final long serialVersionUID = 1L;
    /**
     * 用户昵称
     */
    @Schema(description = "用户昵称")
    private String userName;
    /**
     * 账号
     */
    @Schema(description = "账号")
    private String userAccount;
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
     * 钱包余额（分）
     */
    @Schema(description = "钱包余额（分）")
    private Integer balance;
}