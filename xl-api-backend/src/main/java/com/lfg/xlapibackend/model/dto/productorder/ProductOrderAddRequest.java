package com.lfg.xlapibackend.model.dto.productorder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lfg
 * @Date: 2023/08/23 03:22:17
 * @Version: 1.0
 * @Description: 创建请求
 */
@Schema(description = ": 创建请求")
@Data
public class ProductOrderAddRequest implements Serializable {

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

    /**
     * 订单号
     */
    @Schema(description = "订单号")
    private String orderNo;

}