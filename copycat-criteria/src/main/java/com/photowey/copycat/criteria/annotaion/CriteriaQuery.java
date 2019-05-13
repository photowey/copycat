package com.photowey.copycat.criteria.annotaion;

import java.lang.annotation.*;

/**
 * 用于条件注解上面
 *
 * @author WcJun
 * @date 2019/05/12
 */
@Inherited
@Documented
@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CriteriaQuery {
}
