package com.imooc.security.core.validate.processor;

import com.imooc.security.core.validate.code.ImageCode;
import com.imooc.security.core.validate.processor.base.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * 图片验证码处理器
 *
 * @author zhupeiyou
 * @since 2020-07-09 09:23
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {
    /**
     * 发送图形验证码，将其写到响应中
     */
    @Override
    protected void send(ServletWebRequest servletWebRequest, ImageCode imageCode) throws Exception {
        ImageIO.write(imageCode.getImage(), "JPEG", servletWebRequest.getResponse().getOutputStream());
    }
}
