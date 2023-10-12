package com.lfg.xlapibackend.aop;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.lfg.xlapibackend.annotation.AuthCheck;
import com.lfg.xlapibackend.common.ErrorCode;
import com.lfg.xlapibackend.exception.BusinessException;
import com.lfg.xlapibackend.model.vo.UserVO;
import com.lfg.xlapibackend.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 权限校验 AOP
 *
 * @author lfg
 *
 */
//aop注解
@Aspect
//组件
@Component
public class AuthInterceptor {

    @Resource
    private UserService userService;

    /**
     * 执行拦截
     *
     * @param joinPoint 连接点
     * @param authCheck 身份验证检查
     * @return {@link Object}
     * @throws Throwable 可丢弃
     */
//    注解表示这个方法会在被标记了 @authCheck 注解的方法执行前后进行拦截。 这是Spring AOP的一种方式，允许您在方法执行前后添加额外的逻辑。
    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        List<String> anyRole = Arrays.stream(authCheck.anyRole()).filter(StringUtils::isNotBlank).collect(Collectors.toList());
        String mustRole = authCheck.mustRole();
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        // 当前登录用户
        UserVO user = userService.getLoginUser(request);
        // 拥有任意权限即通过
        if (CollectionUtils.isNotEmpty(anyRole)) {
            String userRole = user.getUserRole();
            if (!anyRole.contains(userRole)) {
                throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
            }
        }
        // 必须有所有权限才通过
        if (StringUtils.isNotBlank(mustRole)) {
            String userRole = user.getUserRole();
            if (!mustRole.equals(userRole)) {
                throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
            }
        }
        // 通过权限校验，放行
        return joinPoint.proceed();
    }
}

