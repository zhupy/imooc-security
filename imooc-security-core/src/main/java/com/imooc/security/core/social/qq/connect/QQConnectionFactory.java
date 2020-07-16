package com.imooc.security.core.social.qq.connect;

import com.imooc.security.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @author zhupeiyou
 * @since 2020-07-10 10:40
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdatper());
    }
}
