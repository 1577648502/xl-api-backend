package com.lfg.xlapibackend.model.dto.interfaceinfo;

import com.lfg.xlapibackend.common.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author lfg
 * @Date: 2023/09/04 11:33:35
 * @Version: 1.0
 * @Description: 界面信息搜索文本请求
 */
@Schema(description = ": 界面信息搜索文本请求")
@Data
@EqualsAndHashCode(callSuper = true)
public class InterfaceInfoSearchTextRequest extends PageRequest implements Serializable {
    @Schema(hidden = true)
    private static final long serialVersionUID = -6337349622479990038L;

    @Schema(hidden = true)
    private String searchText;
}
