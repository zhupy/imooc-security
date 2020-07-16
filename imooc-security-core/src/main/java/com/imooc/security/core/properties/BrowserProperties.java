package com.imooc.security.core.properties;

import com.imooc.security.core.constant.SecurityConstants;

/**
 * @author zhupeiyou
 * @since 2020-07-07 23:33
 */
public class BrowserProperties {

    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;
    private String signUpUrl = "/imooc-signUp.html";

    private LoginResponseType loginType = LoginResponseType.JSON;
    /**
     * 记住我失效时间，单位（秒）
     */
    private int rememberMeSeconds = 3600;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginResponseType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginResponseType loginType) {
        this.loginType = loginType;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {

        this.rememberMeSeconds = rememberMeSeconds;
    }

    public String getSignUpUrl() {
        return signUpUrl;
    }

    public void setSignUpUrl(String signUpUrl) {
        this.signUpUrl = signUpUrl;
    }
}
