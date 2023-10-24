package com.lfg.xlapibackend.model.dto.pay;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lfg
 * @Date: 2023/08/25 05:00:08
 * @Version: 1.0
 * @Description: 付款创建请求
 */
@Schema(description = ": 付款创建请求")
@Data
public class PayCreateRequest implements Serializable {

    @Schema(hidden = true)
    private static final long serialVersionUID = 1L;

    /**
     * 接口id
     */
    @Schema(description = "接口id")
    private String productId;

    /**
     * 支付类型
     */
    @Schema(description = "支付类型")
    private String payType;

}