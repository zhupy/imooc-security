package com.imooc.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhupeiyou
 * @since 2020-07-07 23:33
 */
@ConfigurationProperties(prefix = "imooc.security")
public class SecurityProperties {
    private BrowserProperties browser = new BrowserProperties();
    private ValidateCodeProperties code = new ValidateCodeProperties();
    private SocialProperties social = new SocialProperties();

    public SocialProperties getSocial() {
        return social;
    }

    public void setSocial(SocialProperties social) {
        this.social = social;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
