package com.imooc.security.core.validate.generator;

import com.imooc.security.core.validate.code.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author zhupeiyou
 * @since 2020-07-08 18:13
 */
public interface ValidateCodeGenerator {
    /**
     * 生成码
     *
     * @param request 请求体
     * @return ImageCode
     */
    ValidateCode generate(ServletWebRequest request);
}
