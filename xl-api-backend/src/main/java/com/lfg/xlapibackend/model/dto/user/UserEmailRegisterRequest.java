package com.lfg.xlapibackend.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lfg
 * @Date: 2023/09/04 11:34:09
 * @Version: 1.0
 * @Description: 用户注册请求体
 */
@Schema(description = ": 用户注册请求体")
@Data
public class UserEmailRegisterRequest implements Serializable {

    @Schema(hidden = true)
    private static final long serialVersionUID = 3191241716373120793L;

    /**
     * 邮箱账户
     */
    @Schema(description = "邮箱账户")
    private String emailAccount;
    /**
     * 验证码
     */

    @Schema(description = "验证码")
    private String captcha;
    /**
     * 用户名
     */

    @Schema(description = "用户名")
    private String userName;
    /**
     * 邀请码
     */

    @Schema(description = "邀请码")
    private String invitationCode;


    @Schema(hidden = true)
    private String agreeToAnAgreement;
}
