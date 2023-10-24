package com.lfg.xlapibackend.model.dto.interfaceinfo;

import com.lfg.xlapibackend.common.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @author lfg
 * @Date: 2023/09/04 11:33:30
 * @Version: 1.0
 * @Description: 查询请求
 */
@Schema(description = ": 查询请求")
@EqualsAndHashCode(callSuper = true)
@Data
public class InterfaceInfoQueryRequest extends PageRequest implements Serializable {

    @Schema(hidden = true)
    private static final long serialVersionUID = 1L;
    /**
     * 接口名称
     */
    @Schema(description = "接口名称")
    private String name;
    /**
     * 返回格式
     */
    @Schema(description = "返回格式")
    private String returnFormat;
    /**
     * 接口地址
     */
    @Schema(description = "接口地址")
    private String url;
    /**
     * 接口响应参数
     */
    @Schema(description = "接口响应参数")
    private List<ResponseParamsField> responseParams;
    /**
     * 发布人
     */
    @Schema(description = "发布人")
    private Long userId;
    /**
     * 减少积分个数
     */
    @Schema(description = "减少积分个数")
    private Integer reduceScore;
    /**
     * 请求方法
     */
    @Schema(description = "请求方法")
    private String method;
    /**
     * 描述信息
     */
    @Schema(description = "描述信息")
    private String description;
    /**
     * 接口状态（0- 默认下线 1- 上线）
     */
    @Schema(description = "接口状态（0- 默认下线 1- 上线）")
    private Integer status;
}