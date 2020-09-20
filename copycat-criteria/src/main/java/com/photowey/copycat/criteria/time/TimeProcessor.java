package com.photowey.copycat.criteria.time;

/**
 * 时间处理根接口
 *
 * @author WcJun
 * @date 2020/09/20
 * @since 1.2.0
 */
public interface TimeProcessor<T> {

    /**
     * 是否支持处理改该 类型(T) 的时间
     *
     * @param clazz 时间类型
     * @return 布尔值
     */
    boolean supports(Class<?> clazz);

    /**
     * 处理时间
     *
     * @param timeStamp 时间戳
     * @param clazz     时间类型
     * @return T 类型
     */
    T handleTime(Long timeStamp, Class<?> clazz);
}
