package com.photowey.copycat.criteria.util;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 条件查询工具类
 *
 * @author WcJun
 * @date 2019/05/12
 */
public final class CriteriaUtils {

    private static final Logger log = LoggerFactory.getLogger(CriteriaUtils.class);

    private CriteriaUtils() {
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

    /**
     * 首字母转大写
     *
     * @param str
     * @return
     */
    public static String toCapital(final String str) {
        assert str != null;
        final char[] cs = str.toCharArray();
        if (cs[0] >= 'a' && cs[0] <= 'z') {
            final char[] array = cs;
            final int n = 0;
            array[n] -= ' ';
        }

        return String.valueOf(cs);
    }

    /**
     * 首字母转小写
     *
     * @param str
     * @return
     */
    public static String toLowerCase(final String str) {
        assert str != null;
        final char[] cs = str.toCharArray();
        if (cs[0] >= 'A' && cs[0] <= 'Z') {
            final char[] array = cs;
            final int n = 0;
            array[n] += ' ';
        }

        return String.valueOf(cs);
    }
}
