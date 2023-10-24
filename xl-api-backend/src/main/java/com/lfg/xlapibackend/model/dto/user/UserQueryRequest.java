package com.lfg.xlapibackend.model.dto.user;

import com.lfg.xlapibackend.common.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author lfg
 * @Date: 2023/09/04 11:34:17
 * @Version: 1.0
 * @Description: 用户查询请求
 */
@Schema(description = ": 用户查询请求")
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryRequest extends PageRequest implements Serializable {
    @Schema(hidden = true)
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @Schema(description = "id")
    private Long id;
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
     * 性别
     */
    @Schema(description = "性别")
    private String gender;
    /**
     * 用户角色: user, admin
     */
    @Schema(description = "用户角色: user, admin")
    private String userRole;
}