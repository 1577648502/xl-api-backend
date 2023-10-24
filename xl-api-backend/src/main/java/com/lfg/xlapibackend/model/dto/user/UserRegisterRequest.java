package com.lfg.xlapibackend.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lfg
 * @Date: 2023/09/04 11:34:21
 * @Version: 1.0
 * @Description: 用户注册请求体
 */
@Schema(description = ": 用户注册请求体")
@Data
public class UserRegisterRequest implements Serializable {

    @Schema(hidden = true)
    private static final long serialVersionUID = 3191241716373120793L;

    /**
     * 账号
     */
    @Schema(description = "账号")
    private String userAccount;
    /**
     * 密码
     */
    @Schema(description = "密码")
    private String userPassword;
    /**
     * 用户昵称
     */
    @Schema(description = "用户昵称")
    private String userName;
    /**
     * 确认密码
     */
    @Schema(description = "确认密码")
    private String checkPassword;
    /**
     * 邀请码
     */
    @Schema(description = "邀请码")
    private String invitationCode;

    @Schema(hidden = true)
    private String agreeToAnAgreement;
}
