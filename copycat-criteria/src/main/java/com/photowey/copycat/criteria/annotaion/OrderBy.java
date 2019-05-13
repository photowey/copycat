package com.photowey.copycat.criteria.annotaion;

import com.photowey.copycat.criteria.enums.ColumnNamingStrategy;
import com.photowey.copycat.criteria.enums.OrderByEnum;

import java.lang.annotation.*;

/**
 * 排序(ORDER BY) 条件注解
 *
 * @author WcJun
 * @date 2019/05/12
 * @see {@link com.photowey.copycat.criteria.processor.OrderByProcessor}
 */
@Documented
@CriteriaQuery
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OrderBy {
    /**
     * 自定义的属性值
     *
     * @return
     */
    String alias() default "";

    /**
     * 排序
     * 说明: 由于解析器在解析注解的
     *
     * @return
     */
    OrderByEnum orderBy() default OrderByEnum.ASC;

    /**
     * 默认下划线
     *
     * @return ColumnNamingStrategy
     */
    ColumnNamingStrategy naming() default ColumnNamingStrategy.LOWER_CASE_UNDER_LINE;
}
