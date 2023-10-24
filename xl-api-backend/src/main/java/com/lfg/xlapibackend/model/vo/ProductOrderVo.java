package com.lfg.xlapibackend.model.vo;

import com.lfg.xlapibackend.model.entity.ProductInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lfg
 * @Date: 2023年08月23日 18:02
 * @Version: 1.0
 * @Description: 项目订单视图
 */
@Schema(description = ": 项目订单视图")
@Data
public class ProductOrderVo implements Serializable {

    @Schema(hidden = true)
    private static final long serialVersionUID = 1L;
    @Schema(hidden = true)
    private Long id;

    /**
     * 订单编号
     */
    @Schema(description = "订单编号")
    private String orderNo;

    /**
     * 支付类型
     */
    @Schema(description = "支付类型")
    private String payType;
    /**
     * 过期时间
     */
    @Schema(description = "过期时间")
    private Date expirationTime;

    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    private String orderName;
    /**
     * 支付二维码地址
     */
    @Schema(description = "支付二维码地址")
    private String codeUrl;

    /**
     * 商品id
     */
    @Schema(description = "商品id")
    private Long productId;
    /**
     * 金额(分)
     */
    @Schema(description = "金额(分)")
    private String total;
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
     * 产品信息
     */
    @Schema(description = "产品信息")
    private ProductInfo productInfo;

    /**
     * 增加积分个数
     */
    @Schema(description = "增加积分个数")
    private Integer addPoints;

    /**
     * 支付宝订单体
     */
    @Schema(description = "支付宝订单体")
    private String formData;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private Date createTime;

    /**
     * 产品描述
     */
    @Schema(description = "产品描述")
    private String description;

    /**
     * 产品类型（VIP-会员 RECHARGE-充值）
     */
    @Schema(description = "产品类型（VIP-会员 RECHARGE-充值）")
    private String productType;
}
