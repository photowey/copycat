package com.photowey.copycat.criteria.annotaion;

import com.photowey.copycat.criteria.enums.ColumnNamingStrategy;

import java.lang.annotation.*;

/**
 * 不存在(NOT EXISTS)  条件注解
 *
 * @author WcJun
 * @date 2019/05/12
 * @see {@link com.photowey.copycat.criteria.processor.NotExistsProcessor}
 */
@Documented
@CriteriaQuery
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotExists {
    /**
     * 自定义的属性值
     *
     * @return
     */
    String alias() default "";

    /**
     * TODO 还未想好
     * not exists Sql
     *
     * @return
     */
    String existsSql() default "";

    /**
     * 默认下划线
     *
     * @return ColumnNamingStrategy
     */
    ColumnNamingStrategy naming() default ColumnNamingStrategy.LOWER_CASE_UNDER_LINE;
}
