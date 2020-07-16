package com.imooc.security.core.validate.processor.base;

import com.imooc.security.core.exception.ValidateCodeException;
import com.imooc.security.core.validate.code.ValidateCode;
import com.imooc.security.core.validate.code.ValidateCodeType;
import com.imooc.security.core.validate.generator.ValidateCodeGenerator;
import com.imooc.security.core.validate.processor.ValidateCodeProcessor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * @author zhupeiyou
 * @since 2020-07-08 23:07
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {


    /**
     * 操作session的工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;


    @Override
    public void create(ServletWebRequest servletWebRequest) throws Exception {
        C validateCode = generate(servletWebRequest);
        save(servletWebRequest, validateCode);
        send(servletWebRequest, validateCode);
    }

    /**
     * 发送验证码
     *
     * @param servletWebRequest 请求体
     * @param validateCode      验证码
     * @throws Exception 异常
     */
    protected abstract void send(ServletWebRequest servletWebRequest, C validateCode) throws Exception;

    /**
     * 保存校验码
     *
     * @param validateCode      校验码
     * @param servletWebRequest 请求体
     */
    private void save(ServletWebRequest servletWebRequest, C validateCode) {
        sessionStrategy.setAttribute(servletWebRequest, getSessionKey(), validateCode);
    }

    /**
     * 构建验证码放入session时的key
     *
     * @return {@link String}
     */
    private String getSessionKey() {
        return SESSION_KEY_PREFIX + getValidateCodeType().toString().toUpperCase();
    }

    /**
     * 生成校验码
     *
     * @param servletWebRequest 请求体
     */
    @SuppressWarnings("unchecked")
    private C generate(ServletWebRequest servletWebRequest) {
        String type = getValidateCodeType().toString().toLowerCase();
        String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(generatorName);
        if (validateCodeGenerator == null) {
            throw new ValidateCodeException("验证码生成器" + generatorName + "不存在");
        }
        return (C) validateCodeGenerator.generate(servletWebRequest);


    }

    /**
     * 根据请求的url获取校验码的类型
     */
    private ValidateCodeType getValidateCodeType() {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
        return ValidateCodeType.valueOf(type.toUpperCase());
    }

    @Override
    @SuppressWarnings("unchecked")
    public void validate(ServletWebRequest servletWebRequest) {
        ValidateCodeType processorType = getValidateCodeType();
        String sessionKey = getSessionKey();

        C codeInSession = (C) sessionStrategy.getAttribute(servletWebRequest, sessionKey);

        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(),
                    processorType.getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("获取验证码的值失败");
        }

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException(processorType + "验证码的值不能为空");
        }

        if (codeInSession == null) {
            throw new ValidateCodeException(processorType + "验证码不存在");
        }

        if (codeInSession.isExpired()) {
            sessionStrategy.removeAttribute(servletWebRequest, sessionKey);
            throw new ValidateCodeException(processorType + "验证码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException(processorType + "验证码不匹配");
        }

        sessionStrategy.removeAttribute(servletWebRequest, sessionKey);
    }
}
