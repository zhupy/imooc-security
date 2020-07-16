package com.imooc.security.core.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 图片验证码
 *
 * @author zhupeiyou
 * @since 2020-07-08 09:24
 */
public class ImageCode extends ValidateCode {

    /**
     * 验证码图片
     */
    private BufferedImage image;


    /**
     * @param code     验证码
     * @param expireIn 在expireIn 秒内过期
     */
    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.image = image;
    }


    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
