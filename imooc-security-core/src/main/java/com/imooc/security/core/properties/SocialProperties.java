package com.imooc.security.core.properties;

/**
 * @author zhupeiyou
 * @since 2020-07-10 11:22
 */
public class SocialProperties {
    private QQProperties qq = new QQProperties();
    private String filterProcessesUrl = "/auth";

    public String getFilterProcessesUrl() {
        return filterProcessesUrl;
    }

    public void setFilterProcessesUrl(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    public QQProperties getQq() {
        return qq;
    }

    public void setQq(QQProperties qq) {
        this.qq = qq;
    }
}
