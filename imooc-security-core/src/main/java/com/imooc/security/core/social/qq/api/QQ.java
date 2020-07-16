package com.imooc.security.core.social.qq.api;

/**
 * @author zhupeiyou
 * @since 2020-07-09 18:04
 */
public interface QQ {
    /**
     * 获取用户信息
     *
     * @return {@link QQUserInfo}
     */
    QQUserInfo getUserInfo();
}
