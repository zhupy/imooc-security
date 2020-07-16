package com.imooc.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * @author zhupeiyou
 * @since 2020-07-16 16:41
 */
@Component
public class DemoConnectionSignUp implements ConnectionSignUp {
    @Override
    public String execute(Connection<?> connection) {
        return connection.getDisplayName();
    }
}
