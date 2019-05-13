package com.photowey.copycat.criteria.enums;

/**
 * 数据库字段的命名策略
 *
 * @author WcJun
 * @date 2019/05/12
 */
public enum ColumnNamingStrategy {
    /**
     * 小写字母 && 下划线 a_b
     */
    LOWER_CASE_UNDER_LINE,
    /**
     * 大写字母 && 下划线 A_B
     */
    UPPER_CASE_UNDER_LINE,
    /**
     * 首字母小写 && 驼峰
     */
    FIRST_LOWER_CASE_CAMEL,
    /**
     * 首字母大写 && 驼峰
     */
    FIRST_CAPITAL_CAMEL;
}
