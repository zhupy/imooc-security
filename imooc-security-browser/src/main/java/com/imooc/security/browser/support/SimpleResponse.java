package com.imooc.security.browser.support;

/**
 * @author zhupeiyou
 * @since 2020-07-07 23:24
 */
public class SimpleResponse<T> {

    public SimpleResponse(T content) {
        this.content = content;
    }

    private T content;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
