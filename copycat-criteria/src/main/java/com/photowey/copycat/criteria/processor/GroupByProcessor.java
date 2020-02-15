package com.photowey.copycat.criteria.processor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.photowey.copycat.criteria.annotaion.ConditionProcessor;
import com.photowey.copycat.criteria.annotaion.GroupBy;
import com.photowey.copycat.criteria.query.AbstractQuery;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

/**
 * {@link GroupBy} 注解处理器
 *
 * @param <QUERY>  自定义查询 Query
 * @param <ENTITY> 查询想对应的实体类型
 * @author WcJun
 * @date 2019/05/12
 */
@ConditionProcessor(targetAnnotation = GroupBy.class)
public class GroupByProcessor<QUERY extends AbstractQuery, ENTITY>
        extends CriteriaAnnotationProcessorAdaptor<GroupBy, QUERY, QueryWrapper<ENTITY>, ENTITY> {

    @Override
    public boolean process(QueryWrapper<ENTITY> queryWrapper, Field field, QUERY query, GroupBy criteriaAnnotation) {

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
        queryWrapper.groupBy(null != value, columnName);

        return true;
    }
}
