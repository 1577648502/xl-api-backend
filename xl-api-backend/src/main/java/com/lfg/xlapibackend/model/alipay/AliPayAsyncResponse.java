package com.lfg.xlapibackend.model.alipay;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lfg
 * @Date: 2023/08/26 01:49:51
 * @Version: 1.0
 * @Description: ali支付异步响应
 */
@Schema(description = ": ali支付异步响应")
@Data
public class AliPayAsyncResponse implements Serializable {

    @Schema(hidden = true)
    private static final long serialVersionUID = 1061553753198699097L;
    /**
     * 通知时间
     */
    @Schema(description = "通知时间")
    private String notifyTime;

    /**
     * 通知的类型
     */
    @Schema(description = "通知的类型")
    private String notifyType;

    /**
     * 通知校验ID
     */
    @Schema(description = "通知校验ID")
    private String notifyId;

    /**
     * 卖家id
     */
    @Schema(description = "卖家id")
    private String sellerId;

    /**
     * 买方id
     */
    @Schema(description = "买方id")
    private String buyerId;

    /**
     * 编码格式
     */
    @Schema(description = "编码格式")
    private String charset;

    /**
     * 接口版本
     */
    @Schema(description = "接口版本")
    private String version;

    /**
     * 授权方的app_id
     */
    @Schema(description = "授权方的app_id")
    private String authAppId;

    /**
     * 支付宝交易号
     */
    @Schema(description = "支付宝交易号")
    private String tradeNo;

    /**
     * APP_ID
     */
    @Schema(description = "APP_ID")
    private String appId;

    /**
     * 商户订单号
     */
    @Schema(description = "商户订单号")
    private String outTradeNo;

    /**
     * 交易状态
     */
    @Schema(description = "交易状态")
    private String tradeStatus;

    /**
     * 订单金额
     */
    @Schema(description = "订单金额")
    private String totalAmount;

    /**
     * 实收金额
     */
    @Schema(description = "实收金额")
    private String receiptAmount;

    /**
     * 付款金额
     */
    @Schema(description = "付款金额")
    private String buyerPayAmount;

    /**
     * 订单标题
     */
    @Schema(description = "订单标题")
    private String subject;

    /**
     * 商品描述
     */
    @Schema(description = "商品描述")
    private String body;

    /**
     * 交易创建时间
     */
    @Schema(description = "交易创建时间")
    private String gmtCreate;
}
