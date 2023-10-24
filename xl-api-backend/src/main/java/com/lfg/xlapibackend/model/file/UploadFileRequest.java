package com.lfg.xlapibackend.model.file;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 文件上传请求
 *
 * @author lfg
 */
@Schema(description = "文件上传请求")
@Data
public class UploadFileRequest implements Serializable {

    @Schema(hidden = true)
    private static final long serialVersionUID = 1L;
    /**
     * 业务
     */
    @Schema(description = "业务")
    private String biz;
}