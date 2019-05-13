package com.photowey.copycat.criteria.advisor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.photowey.copycat.criteria.annotaion.*;
import com.photowey.copycat.criteria.exception.CopycatException;
import com.photowey.copycat.criteria.parser.CriteriaFieldParser;
import com.photowey.copycat.criteria.processor.*;
import com.photowey.copycat.criteria.query.AbstractQuery;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * 注解处理器增强
 *
 * @author WcJun
 * @date 2019/05/12
 */
public class CriteriaAnnotationProcessorAdvisor implements ProcessorAdvisor {

    /**
     * 条件注解处理器缓存
     */
    protected static Map<Class<? extends Annotation>, CriteriaAnnotationProcessor> ANNOTATION_PROCESSOR_CACHE = null;

    /**
     * TODO 待优化
     */
    static {
        // TODO 根据 实际情况来分配容量
        ANNOTATION_PROCESSOR_CACHE = new HashMap<>(1);

        // EQ
        ANNOTATION_PROCESSOR_CACHE.put(Eq.class, new EqProcessor<>());
        // NE
        ANNOTATION_PROCESSOR_CACHE.put(Ne.class, new NeProcessor());

        // GE
        ANNOTATION_PROCESSOR_CACHE.put(Ge.class, new GeProcessor());
        // GT
        ANNOTATION_PROCESSOR_CACHE.put(Gt.class, new GtProcessor());

        // IN
        ANNOTATION_PROCESSOR_CACHE.put(In.class, new InProcessor());
        // NOT IN
        ANNOTATION_PROCESSOR_CACHE.put(NotIn.class, new NotInProcessor());

        // IS NULL
        ANNOTATION_PROCESSOR_CACHE.put(IsNull.class, new IsNullProcessor());
        // IS NOT NULL
        ANNOTATION_PROCESSOR_CACHE.put(IsNotNull.class, new IsNotNullProcessor());

        // LE
        ANNOTATION_PROCESSOR_CACHE.put(Le.class, new LeProcessor());
        // LT
        ANNOTATION_PROCESSOR_CACHE.put(Lt.class, new LtProcessor());

        // LIKE
        ANNOTATION_PROCESSOR_CACHE.put(Like.class, new LikeProcessor());
        // NOT LIKE
        ANNOTATION_PROCESSOR_CACHE.put(NotLike.class, new NotLikeProcessor());

        // EXISTS
        ANNOTATION_PROCESSOR_CACHE.put(Exists.class, new ExistsProcessor());
        // NOT EXISTS
        ANNOTATION_PROCESSOR_CACHE.put(NotExists.class, new NotExistsProcessor());

        // GROUP BY
        ANNOTATION_PROCESSOR_CACHE.put(GroupBy.class, new GroupByProcessor());
        // HAVING
        ANNOTATION_PROCESSOR_CACHE.put(Having.class, new HavingProcessor());

        // ORDER BY
        ANNOTATION_PROCESSOR_CACHE.put(OrderBy.class, new OrderByProcessor());
        // TIMESTAMP
        ANNOTATION_PROCESSOR_CACHE.put(Timestamp.class, new TimestampProcessor());

    }

    /**
     * 从缓存中查询制定的处理器
     *
     * @param processorClazz 处理器 Class
     * @return 指定的条件注解处理器
     */
    protected static CriteriaAnnotationProcessor findProcessor(final Class<?> processorClazz) {
        final CriteriaAnnotationProcessor processor = ANNOTATION_PROCESSOR_CACHE.get(processorClazz);
        if (null == processor) {
            throw new CopycatException("No processor found:%s", processorClazz);
        }

        return processor;
    }

    /**
     * 通过条件注解完成自动包装
     *
     * @param query        自定义的查询对象
     * @param queryWrapper 查询包装器
     * @param <QUERY>      自定义的查询类型
     * @param <ENTITY>     实体类型
     * @return QueryWrapper
     * @see {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     */
    public static <QUERY extends AbstractQuery, ENTITY> QueryWrapper<ENTITY> advise(final QUERY query, QueryWrapper<ENTITY> queryWrapper) {
        CriteriaFieldParser.foreachCriteriaField(query, (field, criteriaAnnotation) -> {
            final CriteriaAnnotationProcessor processorCached = findProcessor(criteriaAnnotation.annotationType());
            assert processorCached != null;
            return processorCached.process(queryWrapper, field, query, criteriaAnnotation);
        });

        return queryWrapper;
    }

}
