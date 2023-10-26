package com.lfg.xlapibackend.service.inner.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lfg.xlapibackend.service.InterfaceInfoService;
import com.lfg.xlapicommon.model.entity.InterfaceInfo;
import com.lfg.xlapicommon.service.inner.InnerInterfaceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * @author lfg
 * @Date: 2023年09月15日 22:53
 * @Version: 1.0
 * @Description:
 */
@DubboService
@Slf4j
public class InnerInterfaceInfoServiceImpl implements InnerInterfaceInfoService {
    @Resource
    private InterfaceInfoService interfaceInfoService;

    @Override
    /**
     * 根据用户传递的链接和请求类型查询数据库查看接口是否存在
     */
    public InterfaceInfo getInterfaceInfo(String path, String method) {
        // 如果带参数，去除第一个？和之后后的参数
        if (path.contains("?")) {
            path = path.substring(0, path.indexOf("?"));
        }
        if (path.startsWith("http://")) {
            path = path.substring(7);
        }
        if (path.startsWith("https://")) {
            path = path.substring(8);
        }
        log.info("【查询地址】：" + path);
        LambdaQueryWrapper<InterfaceInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(InterfaceInfo::getMethod, method);
        lambdaQueryWrapper.like(InterfaceInfo::getUrl, path);
        return interfaceInfoService.getOne(lambdaQueryWrapper);
    }
}
