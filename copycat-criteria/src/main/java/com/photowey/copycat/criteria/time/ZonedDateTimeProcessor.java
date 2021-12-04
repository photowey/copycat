/*
 * Copyright © 2019 photowey (photowey@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
