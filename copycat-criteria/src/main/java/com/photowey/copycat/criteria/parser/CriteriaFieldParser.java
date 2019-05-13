package com.photowey.copycat.criteria.parser;

import com.photowey.copycat.criteria.annotaion.CriteriaQuery;
import com.photowey.copycat.criteria.callback.CriteriaFieldCallback;
import com.photowey.copycat.criteria.query.AbstractQuery;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 属性解析器
 *
 * @author WcJun
 * @date 2019/05/12
 */
public final class CriteriaFieldParser {

    private CriteriaFieldParser() {
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

    /**
     * 遍历字段
     *
     * @param query    查询对象
     * @param callback 回调函数
     * @param <QUERY>  查询类型
     */
    public static <QUERY extends AbstractQuery> void foreachCriteriaField(final QUERY query, final CriteriaFieldCallback callback) {
        for (Class<?> clazz = query.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            CriteriaFieldParser.foreachCriteriaField(clazz, callback);
        }
    }

    /**
     * 遍历字段
     *
     * @param clazz    查询对象 Class
     * @param callback 回调函数
     */
    protected static void foreachCriteriaField(final Class<?> clazz, final CriteriaFieldCallback callback) {
        final Field[] fields = clazz.getDeclaredFields();
        for (final Field field : fields) {
            final Annotation[] annotations = field.getDeclaredAnnotations();
            for (final Annotation annotation : annotations) {
                if (annotation.annotationType().isAnnotationPresent(CriteriaQuery.class)) {
                    if (!callback.invoke(field, annotation)) {
                        break;
                    }
                }
            }
        }
    }
}
