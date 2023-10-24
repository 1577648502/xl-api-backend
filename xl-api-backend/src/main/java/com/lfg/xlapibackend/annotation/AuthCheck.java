package com.lfg.xlapibackend.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限校验
 *
 * @author lfg
 */
//指定该注解智能在方法上应用
@Target(ElementType.METHOD)
//注解在运行时有效
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {

    /**
     * 有任何一个角色
     *
     * @return {@link String[]}
     */
    String[] anyRole() default "";

    /**
     * 必须有某个角色
     *
     * @return {@link String}
     */
    String mustRole() default "";

}

