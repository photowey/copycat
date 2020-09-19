package com.photowey.copycat.criteria.query;

/**
 * 分页参数
 *
 * @author WcJun
 * @date 2019/05/12
 */
public abstract class PaginationQuery {

    /**
     * 当前页(从自然页 1 开始)
     */
    protected Integer pageNo = 1;
    /**
     * 每页展示数量 默认 20
     */
    protected Integer pageSize = 20;

    // ================================================

    public Integer getPageNo() {

        if (null != pageNo && pageNo < 1) {
            return 1;
        }

        return null == pageNo ? 1 : pageNo;
    }

    public Integer getPageSize() {

        if (null != pageSize && pageSize < 1) {
            return 1;
        }

        return null == pageSize ? 10 : pageSize;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
