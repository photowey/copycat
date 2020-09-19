package com.photowey.copycat.criteria.advisor;

/**
 * 处理器增强器 - 根接口
 *
 * @author WcJun
 * @date 2019/05/12
 */
@Deprecated
public interface ProcessorAdvisor {

    /**
     * 根据查询的 Query 自动包装查询条件
     *
     * @param query        Query
     * @param queryWrapper QueryWrapper
     * @return 增强后的 QueryWrapper
     */
    // QueryWrapper<ENTITY> advise(final QUERY query, QueryWrapper<ENTITY> queryWrapper);
}
