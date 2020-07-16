package com.imooc.security.core.social.weixin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author zhupeiyou
 * @since 2020-07-16 17:07
 */
public class WeixinImpl extends AbstractOAuth2ApiBinding implements Weixin {

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 获取用户信息的url
     */
    private static final String URL_GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?openid=";

    public WeixinImpl(String accessToken) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    }

    /**
     * 默认注册的StringHttpMessageConverter
     */
    @Override
    protected List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> converters = super.getMessageConverters();
        converters.remove(0);
        converters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return converters;
    }

    /**
     * 获取用户信息
     */
    @Override
    public WeixinUserInfo getUserInfo(String openId) {
        String url = URL_GET_USER_INFO + openId;
        String response = getRestTemplate().getForObject(url, String.class);
        if (StringUtils.contains(response, "errcode")) {
            return null;
        }
        WeixinUserInfo userInfo = null;
        try {
            userInfo = objectMapper.readValue(response, WeixinUserInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userInfo;
    }
}
