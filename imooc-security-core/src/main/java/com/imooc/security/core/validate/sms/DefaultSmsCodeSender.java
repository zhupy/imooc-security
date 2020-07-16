package com.imooc.security.core.validate.sms;

/**
 * @author zhupeiyou
 * @since 2020-07-08 22:35
 */
public class DefaultSmsCodeSender implements SmsCodeSender {


    @Override
    public void send(String mobile, String code) {
        String sendInfoTemplate = "向手机:%s,发送验证码:%s";
        System.out.println(String.format(sendInfoTemplate, mobile, code));
    }
}
