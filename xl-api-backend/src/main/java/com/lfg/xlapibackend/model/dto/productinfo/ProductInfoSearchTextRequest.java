package com.lfg.xlapibackend.model.dto.productinfo;

import com.lfg.xlapibackend.common.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author lfg
 * @Date: 2023/09/04 11:33:48
 * @Version: 1.0
 * @Description: 产品信息搜索文本请求
 */
@Schema(description = ": 产品信息搜索文本请求")
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductInfoSearchTextRequest extends PageRequest implements Serializable {
    @Schema(hidden = true)
    private static final long serialVersionUID = -6337349622479990038L;

    @Schema(hidden = true)
    private String searchText;
}
