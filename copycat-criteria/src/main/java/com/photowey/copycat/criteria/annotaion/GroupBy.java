package com.photowey.copycat.criteria.annotaion;

import com.photowey.copycat.criteria.enums.ColumnNamingStrategy;

import java.lang.annotation.*;

/**
 * 分组统计(GROUP BY) 条件注解
 *
 * @author WcJun
 * @date 2019/05/12
 * @see {@link com.photowey.copycat.criteria.processor.GroupByProcessor}
 */
@Documented
@CriteriaQuery
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GroupBy {
    /**
     * 自定义的属性值
     *
     * @return
     */
    String alias() default "";

    /**
     * 默认下划线
     *
     * @return ColumnNamingStrategy
     */
    ColumnNamingStrategy naming() default ColumnNamingStrategy.LOWER_CASE_UNDER_LINE;
}
