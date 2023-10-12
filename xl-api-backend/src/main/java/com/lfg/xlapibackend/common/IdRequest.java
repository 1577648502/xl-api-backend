package com.lfg.xlapibackend.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lfg
 * @Date: 2023/09/15 09:31:24
 * @Version: 1.0
 * @Description: id请求
 */
@Data
public class IdRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Long id;
}