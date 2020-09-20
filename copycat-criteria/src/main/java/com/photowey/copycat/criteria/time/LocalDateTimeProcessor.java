package com.photowey.copycat.criteria.time;

import com.photowey.copycat.criteria.util.TimeUtils;

import java.time.LocalDateTime;

/**
 * 默认的时间处理器-处理为 {@link LocalDateTime} 对象
 *
 * @author WcJun
 * @date 2020/09/20
 * @since 1.2.0
 */
public class LocalDateTimeProcessor implements TimeProcessor<LocalDateTime> {
    @Override
    public boolean supports(Class<?> clazz) {
        return LocalDateTime.class.equals(clazz);
    }

    @Override
    public LocalDateTime handleTime(Long timeStamp, Class<?> clazz) {
        LocalDateTime localDateTime = TimeUtils.timestampToLocalDateTime(timeStamp);
        return localDateTime;
    }
}
