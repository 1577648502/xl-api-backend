package com.lfg.xlapibackend.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lfg
 * @Date: 2023/09/04 11:34:12
 * @Version: 1.0
 * @Description: 用户登录请求体
 */
@Schema(description = ": 用户登录请求体")
@Data
public class UserLoginRequest implements Serializable {

    @Schema(hidden = true)
    private static final long serialVersionUID = 3191241716373120793L;
    /**
     * 用户名
     */

    @Schema(description = "用户名")
    private String userAccount;
    /**
     * 密码
     */

    @Schema(description = "密码")
    private String userPassword;
}
