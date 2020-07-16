package com.imooc.security.core.social.weixin.api;

/**
 * @author zhupeiyou
 * @since 2020-07-16 16:51
 */
public interface Weixin {

    WeixinUserInfo getUserInfo(String openId);
}
