package com.imooc.security.core.validate.code;

import java.time.LocalDateTime;

/**
 * @author zhupeiyou
 * @since 2020-07-08 22:26
 */
public class ValidateCode {
    /**
     * 验证码
     */
    private String code;
    /**
     * 验证码过期时间
     */
    private LocalDateTime expireTime;

    /**
     * @param code     验证码
     * @param expireIn 在expireIn 秒内过期
     */
    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public boolean isExpired() {
       return LocalDateTime.now().isAfter(expireTime);
    }
}
