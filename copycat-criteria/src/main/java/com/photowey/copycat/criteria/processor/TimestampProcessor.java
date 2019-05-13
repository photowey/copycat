package com.photowey.copycat.criteria.processor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.photowey.copycat.criteria.annotaion.Timestamp;
import com.photowey.copycat.criteria.enums.CompareEnum;
import com.photowey.copycat.criteria.query.AbstractQuery;
import com.photowey.copycat.criteria.util.CriteriaUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

/**
 * {@link Timestamp} 注解处理器
 *
 * @param <QUERY>  自定义查询 Query
 * @param <ENTITY> 查询想对应的实体类型
 * @author WcJun
 * @date 2019/05/12
 */
public class TimestampProcessor<QUERY extends AbstractQuery, ENTITY>
        extends CriteriaAnnotationProcessorAdaptor<Timestamp, QUERY, QueryWrapper<ENTITY>, ENTITY> {

    @Override
    public boolean process(QueryWrapper<ENTITY> queryWrapper, Field field, QUERY query, Timestamp criteriaAnnotation) {

        final Object value = this.columnValue(field, query);
        if (this.isNullOrEmpty(value)) {
            // 属性值为 Null OR Empty 不跳出 循环
            return true;
        }
        String columnName = criteriaAnnotation.alias();
        if (StringUtils.isEmpty(columnName)) {
            columnName = this.columnName(field, criteriaAnnotation.naming());
        }
        assert columnName != null;
        long timeStamp = Long.valueOf(String.valueOf(value));
        CompareEnum compare = criteriaAnnotation.compare();
        // TODO 这儿考虑到有些是 JDK 7 的场景,所以默认采用 {@link java.util.Date}
        // TODO 如果是JDK 8 及以上的话 可以考虑采用 {@link java.time.LocalDateTime}
        // Class<?> clazz = criteriaAnnotation.clazz();
        switch (compare) {
            case EQ:
                queryWrapper.eq(null != value, columnName, CriteriaUtils.toTime(timeStamp));
                break;
            case NE:
                queryWrapper.ne(null != value, columnName, CriteriaUtils.toTime(timeStamp));
                break;
            case GE:
                queryWrapper.ge(null != value, columnName, CriteriaUtils.toTime(timeStamp));
                break;
            case GT:
                queryWrapper.gt(null != value, columnName, CriteriaUtils.toTime(timeStamp));
                break;
            case LE:
                queryWrapper.le(null != value, columnName, CriteriaUtils.toTime(timeStamp));
                break;
            case LT:
                queryWrapper.lt(null != value, columnName, CriteriaUtils.toTime(timeStamp));
                break;
            default:
                queryWrapper.eq(null != value, columnName, CriteriaUtils.toTime(timeStamp));
                break;
        }

        return true;
    }

}
