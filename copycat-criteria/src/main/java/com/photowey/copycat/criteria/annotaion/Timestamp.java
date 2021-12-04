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
package com.photowey.copycat.criteria.annotaion;

import com.photowey.copycat.criteria.enums.ColumnNamingStrategy;
import com.photowey.copycat.criteria.enums.CompareEnum;

import java.lang.annotation.*;
import java.util.Date;

/**
 * 时间戳 条件注解
 * 使用场景:
 * 有些时候前端是将时间戳传入后台 这个时候需要自动将时间戳转换为时间对象
 * Long -> Date
 *
 * @author WcJun
 * @date 2019/05/12
 * @see {@link Long}
 * @see {@link Date}
 * @see {@link com.photowey.copycat.criteria.processor.TimestampProcessor}
 */
@Documented
@CriteriaQuery
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Timestamp {

    /**
     * 自定义的属性值
     *
     * @return
     */
    String alias() default "";

    /**
     * 比较运行符
     *
     * @return CompareEnum
     * @see {@link CompareEnum}
     */
    CompareEnum compare() default CompareEnum.EQ;

    /**
     * 转换的时间对象
     * 默认采用 {@link Date}
     *
     * @return
     * @see {@link Date}
     */
    Class<?> clazz() default Date.class;

    /**
     * 默认下划线
     *
     * @return ColumnNamingStrategy
     */
    ColumnNamingStrategy naming() default ColumnNamingStrategy.LOWER_CASE_UNDER_LINE;
}
