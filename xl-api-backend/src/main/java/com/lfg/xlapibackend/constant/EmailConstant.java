package com.lfg.xlapibackend.constant;

/**
 * @author lfg
 * @Date: 2023/09/03 11:24:40
 * @Version: 1.0
 * @Description: 电子邮件常量
 */
public interface EmailConstant {

    /**
     * 电子邮件html内容路径 resources目录下
     */
    String EMAIL_HTML_CONTENT_PATH = "email.html";

    /**
     * 电子邮件html支付成功路径
     */
    String EMAIL_HTML_PAY_SUCCESS_PATH = "pay.html";

    /**
     * captcha缓存键
     */
    String CAPTCHA_CACHE_KEY = "api:captcha:";

    /**
     * 电子邮件主题
     */
    String EMAIL_SUBJECT = "验证码邮件";

    /**
     * 电子邮件标题
     */
    String EMAIL_TITLE = "Xl-API 接口开放平台";
}
