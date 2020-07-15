package com.hope.saas.common.constants;

/**
 * @author Maduo
 * @date 2020/4/15 18:02
 */
public class Constant {

    /**
     * 线程变量，本地请求的数据
     */
    public static InheritableThreadLocal<HeaderDto> HEADER = new InheritableThreadLocal<>();

    /**
     * 添加请求信息
     *
     * @param headerDto
     */
    public static void set(HeaderDto headerDto) {
        HEADER.set(headerDto);
    }

    /**
     * 删除请求头信息
     */
    public static void remove() {
        HEADER.remove();
    }

    /**
     * 获取
     *
     * @return
     */
    public static HeaderDto get() {
        return HEADER.get();
    }

}
