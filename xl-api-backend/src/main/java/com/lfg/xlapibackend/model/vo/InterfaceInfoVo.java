package com.lfg.xlapibackend.model.vo;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.lfg.xlapicommon.model.entity.InterfaceInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author lfg
 * @Date: 2023年08月21日 18:54
 * @Version: 1.0
 * @Description: 返回接口视图
 */
@Schema(description = ": 返回接口视图")
@Data
public class InterfaceInfoVo implements Serializable {
    @Schema(hidden = true)
    private static final long serialVersionUID = 5713565919036400439L;
    /**
     * 所有接口信息
     */
    @Schema(description = "所有接口信息")
    private List<InterfaceInfo> records;
    /**
     * 总数
     */
    @Schema(description = "总数")
    private long total;
    /**
     * 每页多少个
     */
    @Schema(description = "每页多少个")
    private long size;
    /**
     * 第几页
     */
    @Schema(description = "第几页")
    private long current;
    /**
     * 所有订单
     */
    @Schema(description = "所有订单")
    private List<OrderItem> orders;
    @Schema(hidden = true)
    private boolean optimizeCountSql;
    @Schema(hidden = true)
    private boolean searchCount;
    /**
     * 优化计数
     */
    @Schema(description = "优化计数")
    private boolean optimizeJoinOfCountSql;
    @Schema(hidden = true)
    private String countId;
    @Schema(hidden = true)
    private Long maxLimit;
}
