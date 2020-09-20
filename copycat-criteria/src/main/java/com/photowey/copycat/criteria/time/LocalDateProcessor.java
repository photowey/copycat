package com.photowey.copycat.criteria.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * 默认的时间处理器-处理为 {@link java.time.LocalDate} 对象
 *
 * @author WcJun
 * @date 2020/09/20
 * @since 1.2.0
 */
public class LocalDateProcessor implements TimeProcessor<LocalDate> {
    @Override
    public boolean supports(Class<?> clazz) {
        return LocalDate.class.equals(clazz);
    }

    @Override
    public LocalDate handleTime(Long timeStamp, Class<?> clazz) {
        Instant instant = Instant.ofEpochMilli(timeStamp);
        LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        return localDate;
    }
}
