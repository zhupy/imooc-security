package com.imooc.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @author zhupeiyou
 * @since 2020-07-16 16:46
 */
public class WeixinProperties extends SocialProperties {
    /**
     * 第三方id,用户决定发起第三方登陆的url，默认是微信
     */
    private String providerId = "weixin";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
