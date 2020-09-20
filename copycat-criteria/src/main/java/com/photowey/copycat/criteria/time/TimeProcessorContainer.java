package com.photowey.copycat.criteria.time;

import com.photowey.copycat.criteria.constant.QueryConstant;
import com.photowey.copycat.criteria.util.ScanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 时间处理器容器
 *
 * @author WcJun
 * @date 2020/09/20
 * @since 1.2.0
 */
public class TimeProcessorContainer {

    private static final Logger log = LoggerFactory.getLogger(TimeProcessorContainer.class);

    private static Map<Class<?>, TimeProcessor<?>> TIME_PROCESSOR_CACHE = null;

    static {
        handleTimeProcessorCacheByPackageScan();
    }

    // --------------------------------------------------------------------------------------- HANDLE PROCESSOR

    private static void handleTimeProcessorCacheByPackageScan() {
        try {
            String basePackage = QueryConstant.TIME_PROCESSOR_SCAN_BASE_PACKAGE;
            TIME_PROCESSOR_CACHE = ScanUtils.doTimeScan(basePackage);
        } catch (Exception e) {
            log.error("do time processor scan exception", e);
            handleTimeProcessorCacheByNew();
        }
    }

    private static void handleTimeProcessorCacheByNew() {
        TIME_PROCESSOR_CACHE = new HashMap<>(8);

        // Date
        TIME_PROCESSOR_CACHE.put(Date.class, new DefaultTimeProcessor());

        // LocalDateTime
        TIME_PROCESSOR_CACHE.put(LocalDateTime.class, new LocalDateTimeProcessor());

        // LocalDate
        TIME_PROCESSOR_CACHE.put(LocalDate.class, new LocalDateProcessor());
        // LocalTime
        TIME_PROCESSOR_CACHE.put(LocalTime.class, new LocalTimeProcessor());

        // ZonedDateTime
        TIME_PROCESSOR_CACHE.put(ZonedDateTime.class, new ZonedDateTimeProcessor());
    }

    public static Collection<TimeProcessor<?>> timeProcessors() {
        return TIME_PROCESSOR_CACHE.values();
    }
}
