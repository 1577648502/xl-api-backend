package com.lfg.xlapibackend.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lfg
 * @Date: 2023/09/11 11:26:50
 * @Version: 1.0
 * @Description: 充值活动表
 */
@Schema(description = ": 充值活动表")
@TableName(value = "recharge_activity")
@Data
public class RechargeActivity implements Serializable {
    @Schema(hidden = true)
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @Schema(description = "id")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 微信订单号/支付宝订单id
     */
    @Schema(description = "微信订单号/支付宝订单id")
    private String orderNo;
    /**
     * 用户id
     */
    @Schema(description = "用户id")
    private Long userId;
    /**
     * 商品id
     */
    @Schema(description = "商品id")
    private Long productId;
    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private Date createTime;
    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private Date updateTime;
    /**
     * 是否删除
     */
    @Schema(description = "是否删除")
    @TableLogic
    private Integer isDelete;
}