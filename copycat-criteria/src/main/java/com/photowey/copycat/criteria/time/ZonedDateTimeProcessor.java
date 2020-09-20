package com.photowey.copycat.criteria.time;

import com.photowey.copycat.criteria.util.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * 默认的时间处理器-处理为 {@link LocalDateTime} 对象
 *
 * @author WcJun
 * @date 2020/09/20
 * @since 1.2.0
 */
public class ZonedDateTimeProcessor implements TimeProcessor<ZonedDateTime> {

    private static final Logger log = LoggerFactory.getLogger(DefaultTimeProcessor.class);

    @Override
    public boolean supports(Class<?> clazz) {
        return ZonedDateTime.class.equals(clazz);
    }

    @Override
    public ZonedDateTime handleTime(Long timeStamp, Class<?> clazz) {
        log.info("---------------------------------------------- handle the time by ZonedDateTimeProcessor ----------------------------------------------");
        LocalDateTime localDateTime = TimeUtils.timestampToLocalDateTime(timeStamp);
        return ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
    }
}
