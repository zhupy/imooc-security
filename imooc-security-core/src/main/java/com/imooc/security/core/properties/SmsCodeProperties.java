package com.imooc.security.core.properties;

/**
 * 验证码图片
 *
 * @author zhupeiyou
 * @since 2020-07-08 14:50
 */
public class SmsCodeProperties {
    /**
     * 验证码长度
     */
    private int length = 4;
    /**
     * 验证码过期时间，单位（秒）
     */
    private int expireIn = 60;


    /**
     * 拦截校验验证码的url，用英文,隔开的url
     */
    private String url;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }
}
