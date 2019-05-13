package com.photowey.copycat.criteria.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.photowey.copycat.criteria.advisor.CriteriaAnnotationProcessorAdvisor;

/**
 * 抽象查询对象
 *
 * @author WcJun
 * @date 2019/05/12
 */
public abstract class AbstractQuery<T> extends PaginationQuery {

    // WRAPPER

    /**
     * 通过注解自动包装查询 Wrapper
     *
     * @return
     * @see {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     */
    public QueryWrapper<T> autoWrapper() {
        return CriteriaAnnotationProcessorAdvisor.advise(this, new QueryWrapper<T>());
    }

    // PAGE

    /**
     * 如果需要分页的话
     * 获取分页对象
     *
     * @return IPage
     * @see {@link com.baomidou.mybatisplus.core.metadata.IPage}
     */
    public IPage populatePage() {
        return new Page(this.pageNo, this.pageSize);
    }
}