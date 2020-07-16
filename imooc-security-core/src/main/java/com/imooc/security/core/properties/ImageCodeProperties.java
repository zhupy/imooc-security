package com.imooc.security.core.properties;

/**
 * 验证码图片
 *
 * @author zhupeiyou
 * @since 2020-07-08 14:50
 */
public class ImageCodeProperties extends SmsCodeProperties {
    /**
     * 验证码图片宽
     */
    private int width = 67;
    /**
     * 验证码图片高
     */
    private int height = 23;

    public ImageCodeProperties(){
        setLength(4);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


}
