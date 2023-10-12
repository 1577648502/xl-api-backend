package com.lfg.xlapibackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lfg.xlapicommon.model.entity.InterfaceInfo;


/**
 * @author lfg
 * @Date: 2023/09/15 09:32:11
 * @Version: 1.0
 * @Description: 接口信息服务
 */
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    /**
     * 校验
     *
     * @param add           是否为创建校验
     * @param interfaceInfo 接口信息
     */
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);

    /**
     * 更新总调用数
     *
     * @param interfaceId 接口id
     * @return boolean
     */
    boolean updateTotalInvokes(long interfaceId);
}
