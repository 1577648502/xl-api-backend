package com.lfg.xlapibackend.model.dto.productorder;

import com.lfg.xlapibackend.common.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author lfg
 * @Date: 2023/08/23 03:22:17
 * @Version: 1.0
 * @Description: 创建请求
 */
@Schema(description = ": 创建请求")
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductOrderQueryRequest extends PageRequest implements Serializable {
    @Schema(hidden = true)
    private static final long serialVersionUID = 1L;
    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    private String orderName;
    /**
     * 微信订单号/支付宝订单id
     */
    @Schema(description = "微信订单号/支付宝订单id")
    private String orderNo;
    /**
     * 金额(分)
     */
    @Schema(description = "金额(分)")
    private Integer total;

    /**
     * 接口订单状态(SUCCESS：支付成功
     * REFUND：转入退款
     * NOTPAY：未支付
     * CLOSED：已关闭
     * REVOKED：已撤销（仅付款码支付会返回）
     * USERPAYING：用户支付中（仅付款码支付会返回）
     * PAYERROR：支付失败（仅付款码支付会返回）)
     */
    @Schema(description = "接口订单状态(SUCCESS：支付成功 REFUND：转入退款 NOTPAY：未支付 CLOSED：已关闭 REVOKED：已撤销（仅付款码支付会返回） USERPAYING：用户支付中（仅付款码支付会返回） PAYERROR：支付失败（仅付款码支付会返回）)")
    private String status;

    /**
     * 支付方式（默认 WX- 微信 ZFB- 支付宝）
     */
    @Schema(description = "支付方式（默认 WX- 微信 ZFB- 支付宝）")
    private String payType;
    /**
     * 商品信息
     */
    @Schema(description = "商品信息")
    private String productInfo;
    /**
     * 增加积分个数
     */
    @Schema(description = "增加积分个数")
    private Integer addPoints;
}