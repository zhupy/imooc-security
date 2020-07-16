package com.imooc.security.core.validate.sms;

/**
 * 发送短信
 *
 * @author zhupeiyou
 * @since 2020-07-08 22:33
 */
public interface SmsCodeSender {

    /**
     * 发送验证码
     *
     * @param mobile 手机号
     * @param code   验证码
     */
    void send(String mobile, String code);
}
