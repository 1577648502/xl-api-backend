package com.lfg.xlapibackend.model.dto.interfaceinfo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 接口头像
 * @author lfg
 * @Date: 2023年09月22日 17:40
 * @Version: 1.0
 * @Description:
 */
@Schema(description = "接口头像:")
@Data
public class InterfaceInfoUpdateAvatarRequest {
    @Schema(hidden = true)
    private static final long serialVersionUID = 1L;
    @Schema(hidden = true)
    private long id;
    /**
     * 接口头像
     */
    @Schema(description = "接口头像")
    private String avatarUrl;
}
