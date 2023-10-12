package com.lfg.xlapibackend.service.inner.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lfg.xlapibackend.exception.BusinessException;
import com.lfg.xlapibackend.common.ErrorCode;
import com.lfg.xlapibackend.model.entity.User;
import com.lfg.xlapibackend.service.UserService;
import com.lfg.xlapicommon.model.vo.UserVO;
import com.lfg.xlapicommon.service.inner.InnerUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;

/**
 * @author lfg
 * @Date: 2023年09月15日 22:54
 * @Version: 1.0
 * @Description:
 */
@DubboService
public class InnerUserServiceImpl implements InnerUserService {
    @Resource
    private UserService userService;

    @Override
    public UserVO getInvokeUserByAccessKey(String accessKey) {
        if (StringUtils.isAnyBlank(accessKey)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getAccessKey, accessKey);
        User user = userService.getOne(userLambdaQueryWrapper);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }
}
