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
