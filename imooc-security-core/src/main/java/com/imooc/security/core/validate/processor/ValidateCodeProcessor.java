package com.imooc.security.core.validate.processor;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码处理器,封装不同校验码的处逻辑
 *
 * @author zhupeiyou
 * @since 2020-07-08 23:01
 */
public interface ValidateCodeProcessor {

    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 创建校验码
     *
     * @param servletWebRequest 请求体
     * @throws Exception 异常
     */
    void create(ServletWebRequest servletWebRequest) throws Exception;

    /**
     * 校验校验码
     *
     * @param servletWebRequest 请求体
     */
    void validate(ServletWebRequest servletWebRequest);
}
