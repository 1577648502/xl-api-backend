package com.lfg.xlapibackend.model.dto.interfaceinfo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author lfg
 * @Date: 2023/09/15 03:52:36
 * @Version: 1.0
 * @Description: 请求参数字段
 */
@Schema(description = ": 请求参数字段")
@Data
public class ResponseParamsField {
    /**
     * 请求接口id
     */
    @Schema(description = "请求接口id")
    private String id;
    /**
     * 请求接口名称
     */
    @Schema(description = "请求接口名称")
    private String fieldName;
    /**
     * 请求类型
     */
    @Schema(description = "请求类型")
    private String type;
    /**
     *描述
     */
    @Schema(description = "描述")
    private String desc;
}