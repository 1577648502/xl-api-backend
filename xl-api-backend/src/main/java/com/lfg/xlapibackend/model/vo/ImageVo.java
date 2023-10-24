package com.lfg.xlapibackend.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lfg
 * @Date: 2023/08/20 08:21:48
 * @Version: 1.0
 * @Description: 上传图片状态vo
 */
@Schema(description = ": 上传图片状态vo")
@Data
public class ImageVo implements Serializable {
    @Schema(hidden = true)
    private static final long serialVersionUID = -4296258656223039373L;
    /**
     * 图片uid
     */
    @Schema(description = "图片uid")
    private String uid;
    /**
     * 名称
     */
    @Schema(description = "名称")
    private String name;
    /**
     * 状态
     */
    @Schema(description = "状态")
    private String status;
    /**
     * 路径
     */
    @Schema(description = "路径")
    private String url;
}