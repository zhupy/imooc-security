package com.imooc.security.core.validate;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.generator.ImageCodeGenerator;
import com.imooc.security.core.validate.generator.ValidateCodeGenerator;
import com.imooc.security.core.validate.sms.DefaultSmsCodeSender;
import com.imooc.security.core.validate.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhupeiyou
 * @since 2020-07-08 18:20
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeGenerator() {

        return new DefaultSmsCodeSender();
    }
}
