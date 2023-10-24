package com.lfg.xlapibackend.model.dto.productinfo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lfg
 * @Date: 2023/08/25 03:08:47
 * @Version: 1.0
 * @Description: 创建请求
 */
@Schema(description = ": 创建请求")
@Data
public class ProductInfoAddRequest implements Serializable {

    @Schema(hidden = true)
    private static final long serialVersionUID = 1L;

    /**
     * 产品名称
     */
    @Schema(description = "产品名称")
    private String name;

    /**
     * 产品描述
     */
    @Schema(description = "产品描述")
    private String description;

    /**
     * 金额(分)
     */
    @Schema(description = "金额(分)")
    private Integer total;

    /**
     * 增加积分个数
     */
    @Schema(description = "增加积分个数")
    private Integer addPoints;

    /**
     * 产品类型（VIP-会员 RECHARGE-充值）
     */
    @Schema(description = "产品类型（VIP-会员 RECHARGE-充值）")
    private String productType;

    /**
     * 过期时间
     */
    @Schema(description = "过期时间")
    private Date expirationTime;
}