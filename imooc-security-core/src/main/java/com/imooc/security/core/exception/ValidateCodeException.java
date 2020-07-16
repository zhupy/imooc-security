package com.imooc.security.core.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author zhupeiyou
 * @since 2020-07-08 10:27
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
