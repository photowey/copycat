package com.photowey.copycat.criteria.time;

import com.photowey.copycat.criteria.util.TimeUtils;

import java.util.Date;

/**
 * 默认的时间处理器-处理为 {@link java.util.Date} 对象
 *
 * @author WcJun
 * @date 2020/09/20
 * @since 1.2.0
 */
public class DefaultTimeProcessor implements TimeProcessor<Date> {

    @Override
    public boolean supports(Class<?> clazz) {
        return Date.class.equals(clazz);
    }

    @Override
    public Date handleTime(Long timeStamp, Class<?> clazz) {

        Date date = TimeUtils.toTime(timeStamp, clazz);
        return date;
    }
}
