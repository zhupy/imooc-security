package com.imooc.security.core.validate.processor;

import com.imooc.security.core.constant.SecurityConstants;
import com.imooc.security.core.exception.ValidateCodeException;
import com.imooc.security.core.validate.code.ValidateCode;
import com.imooc.security.core.validate.processor.base.AbstractValidateCodeProcessor;
import com.imooc.security.core.validate.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 短信验证码处理器
 *
 * @author zhupeiyou
 * @since 2020-07-09 09:10
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    /**
     * 短信验证码发送器
     */
    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest servletWebRequest, ValidateCode validateCode) throws Exception {
        String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
        String mobile = ServletRequestUtils.getRequiredStringParameter(servletWebRequest.getRequest(),paramName);
        smsCodeSender.send(mobile,validateCode.getCode());
    }
}
