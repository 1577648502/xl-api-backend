package com.lfg.xlapibackend.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lfg
 * @Date: 2023/09/04 11:34:06
 * @Version: 1.0
 * @Description: 用户登录请求体
 */
@Schema(description = ": 用户登录请求体")
@Data
public class UserEmailLoginRequest implements Serializable {

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
}
