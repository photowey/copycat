package com.photowey.copycat.criteria.annotaion;

import java.lang.annotation.*;

/**
 * 条件处理器
 *
 * @author WcJun
 * @date 2020/02/15
 */
@Inherited
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConditionProcessor {

    // 表征这是一个条件处理器

    /**
     * 目标注解
     *
     * @return 目标注解 Class
     */
    Class<? extends Annotation> targetAnnotation();
}
