package com.photowey.copycat.criteria.util;

import com.photowey.copycat.criteria.exception.CopycatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * 时间处理工具类
 *
 * @author WcJun
 * @date 2020/09/119
 * @since 1.1.0
 */
public final class TimeUtils {

    private static final Logger log = LoggerFactory.getLogger(TimeUtils.class);

    private TimeUtils() {
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

    // ======================================================================

    /**
     * 将时间戳对象 转换为 Date 类型时间
     *
     * @param time 时间戳
     * @return 转换后的时间
     */
    public static Date toTime(Timestamp time) {
        if (null == time) {
            return null;
        }
        return new Date(time.getTime());
    }

    /**
     * 将长整型-时间戳 转换为 Date 类型时间
     *
     * @param timeStamp 时间戳
     * @return 转换后的时间
     */
    public static Date toTime(Long timeStamp) {
        if (null == timeStamp) {
            return null;
        }

        return new Date(timeStamp);
    }

    public static <T> T toTime(Long timeStamp, Class<T> clazz) {
        if (null == timeStamp) {
            return null;
        }

        if (clazz.equals(LocalDateTime.class)) {
            return (T) timestampToLocalDateTime(timeStamp);
        }

        return (T) new Date(timeStamp);
    }

    // ===========================================

    /**
     * LocalDateTime -> Timestamp(Long)
     *
     * @param localDateTime
     * @return
     */
    public static Long localDateTimeToTimestamp(LocalDateTime localDateTime) {
        try {
            ZoneId zoneId = ZoneId.systemDefault();
            Instant instant = localDateTime.atZone(zoneId).toInstant();
            return instant.toEpochMilli();
        } catch (Exception e) {
            throw new CopycatException("convert the LocalDateTime to EpochMilli exception", e);
        }
    }

    /**
     * Timestamp(Long) -> LocalDateTime
     *
     * @param timestamp
     * @return
     */
    public static LocalDateTime timestampToLocalDateTime(Long timestamp) {
        try {
            Instant instant = Instant.ofEpochMilli(timestamp);
            ZoneId zone = ZoneId.systemDefault();
            return LocalDateTime.ofInstant(instant, zone);
        } catch (Exception e) {
            throw new CopycatException("convert the EpochMilli to  LocalDateTime exception", e);
        }
    }

    /**
     * Date -> LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        try {
            Instant instant = date.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            return instant.atZone(zoneId).toLocalDateTime();
        } catch (Exception e) {
            throw new CopycatException("convert the Date to  LocalDateTime exception", e);
        }
    }

    /**
     * LocalDateTime -> Date
     *
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        try {
            ZoneId zoneId = ZoneId.systemDefault();
            ZonedDateTime zdt = localDateTime.atZone(zoneId);
            return Date.from(zdt.toInstant());
        } catch (Exception e) {
            throw new CopycatException("convert the LocalDateTime to  Date exception", e);
        }
    }

    // ======================================================================

}
