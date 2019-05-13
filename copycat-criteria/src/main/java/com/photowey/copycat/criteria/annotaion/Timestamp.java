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
