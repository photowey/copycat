package com.photowey.copycat.criteria.processor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.photowey.copycat.criteria.enums.ColumnNamingStrategy;
import com.photowey.copycat.criteria.exception.CopycatException;
import com.photowey.copycat.criteria.query.AbstractQuery;
import com.photowey.copycat.criteria.util.CriteriaUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 条件注解处理器适配器
 *
 * @param <A>       Annotation
 * @param <QUERY>   查询类型
 * @param <WRAPPER> 包装类型
 * @param <ENTITY>  实体类型
 * @author WcJun
 * @date 2019/05/12
 */
public abstract class CriteriaAnnotationProcessorAdaptor<
        A extends Annotation,
        QUERY extends AbstractQuery,
        WRAPPER extends QueryWrapper<ENTITY>,
        ENTITY>
        implements CriteriaAnnotationProcessor<A, QUERY, WRAPPER, ENTITY> {

    /**
     * 判断对象或者字符串是否为空
     *
     * @param value 指定值
     * @return 布尔值
     */
    public boolean isNullOrEmpty(final Object value) {
        return value == null || (value instanceof String && StringUtils.isEmpty(String.valueOf(value)));
    }

    /**
     * 反射获取属性值
     *
     * @param field 属性对象
     * @param query 查询对象
     * @return 属性值
     */
    public Object columnValue(final Field field, final Object query) {
        field.setAccessible(true);
        try {
            return field.get(query);
        } catch (IllegalAccessException e) {
            throw new CopycatException(e);
        }
    }

    /**
     * 根据不同的命名策略构造不同的属性字段名
     *
     * @param field    查询的属性值
     * @param strategy 数据库命名策略
     * @return 用于 SQL 查询的字段名
     */
    public String columnName(final Field field, final ColumnNamingStrategy strategy) {
        String fieldName = field.getName();
        switch (strategy) {
            case FIRST_CAPITAL_CAMEL:
                return CriteriaUtils.toCapital(fieldName);
            case FIRST_LOWER_CASE_CAMEL:
                return CriteriaUtils.toLowerCase(fieldName);
            case LOWER_CASE_UNDER_LINE:
                return StringUtils.camelToUnderline(fieldName);
            case UPPER_CASE_UNDER_LINE:
                return StringUtils.camelToUnderline(fieldName).toUpperCase();
            default:
                throw new CopycatException("unknown column naming strategy:%s", strategy.name());
        }
    }
}
