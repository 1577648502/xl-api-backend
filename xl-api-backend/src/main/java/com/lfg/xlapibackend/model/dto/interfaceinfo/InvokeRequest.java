package com.lfg.xlapibackend.model.dto.interfaceinfo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author lfg
 * @Date: 2023/08/31 07:59:34
 * @Version: 1.0
 * @Description: 调用请求
 */
@Schema(description = ": 调用请求")
@Data
public class InvokeRequest implements Serializable {

    @Schema(hidden = true)
    private static final long serialVersionUID = 1L;
    /**
     * 接口id
     */
    @Schema(description = "接口id")
    private Long id;
    /**
     * 接口参数
     */
    @Schema(description = "接口参数")
    private List<Field> requestParams;
    /**
     * 用户传递参数
     */
    @Schema(description = "用户传递参数")
    private String userRequestParams;

    @Schema
    @Data
    public static class Field {
        @Schema(hidden = true)
        private String fieldName;
        @Schema(hidden = true)
        private String value;
    }
}

