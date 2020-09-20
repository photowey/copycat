package com.photowey.copycat.criteria.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

/**
 * 默认的时间处理器-处理为 {@link LocalTime} 对象
 *
 * @author WcJun
 * @date 2020/09/20
 * @since 1.2.0
 */
public class LocalTimeProcessor implements TimeProcessor<LocalTime> {
    @Override
    public boolean supports(Class<?> clazz) {
        return LocalTime.class.equals(clazz);
    }

    @Override
    public LocalTime handleTime(Long timeStamp, Class<?> clazz) {
        Instant instant = Instant.ofEpochMilli(timeStamp);
        LocalTime localTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalTime();
        return localTime;
    }
}
