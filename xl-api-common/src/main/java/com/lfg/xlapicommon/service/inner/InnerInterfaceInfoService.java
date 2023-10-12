package com.lfg.xlapicommon.service.inner;


import com.lfg.xlapicommon.model.entity.InterfaceInfo;

/**
 * @author lfg
 * @Date: 2023/09/15 09:32:11
 * @Version: 1.0
 * @Description: 接口信息服务
 */
public interface InnerInterfaceInfoService {
    /**
     * 获取接口信息
     *
     * @param path   路径
     * @param method 方法
     * @return {@link InterfaceInfo}
     */
    InterfaceInfo getInterfaceInfo(String path, String method);
}
