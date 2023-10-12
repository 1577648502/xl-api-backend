package com.lfg.xlapibackend.model.vo;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.lfg.xlapicommon.model.entity.InterfaceInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author lfg
 * @Date: 2023年08月21日 18:54
 * @Version: 1.0
 * @Description: 返回接口视图
 */
@Data
public class InterfaceInfoVo implements Serializable {
    private static final long serialVersionUID = 5713565919036400439L;
    private List<InterfaceInfo> records;
    private long total;
    private long size;
    private long current;
    private List<OrderItem> orders;
    private boolean optimizeCountSql;
    private boolean searchCount;
    private boolean optimizeJoinOfCountSql;
    private String countId;
    private Long maxLimit;
}
