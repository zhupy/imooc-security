package com.imooc.security.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * @author zhupeiyou
 * @since 2020-07-09 18:06
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    /**
     * 后去openid 接口
     */
    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
    /**
     * 获取用户信息接口
     */
    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";
    private ObjectMapper objectMapper = new ObjectMapper();

    private String openid;
    private String appId;

    public QQImpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;

        String url = String.format(URL_GET_OPENID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);
        this.openid = StringUtils.substringBetween(result, "\"openid\":\"", "}");
    }

    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(URL_GET_USERINFO, appId, openid);
        String result = getRestTemplate().getForObject(url, String.class);
        try {
            QQUserInfo qqUserInfo = objectMapper.readValue(result, QQUserInfo.class);
            qqUserInfo.setOpenId(openid);
            return qqUserInfo;
        } catch (IOException e) {
            throw new RuntimeException("获取用户信息失败",e);
        }
    }

}

