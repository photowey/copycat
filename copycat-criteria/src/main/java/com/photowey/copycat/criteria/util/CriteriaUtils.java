package com.photowey.copycat.criteria.util;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 条件查询工具类
 *
 * @author WcJun
 * @date 2019/05/12
 */
public final class CriteriaUtils {

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

    /**
     * 将长整型-时间戳 转换为 Date 类型时间
     *
     * @param time 时间戳
     * @return 转换后的时间
     */
    public static Date toTime(Long time) {
        if (null == time) {
            return null;
        }
        return new Date(time);
    }

    /**
     * 将时间戳对象 转换为 Date 类型时间
     *
     * @param time 时间戳
     * @return 转换后的时间
     */
    public static Date toTime(Timestamp time) {
        if (null == time) {
            return null;
        }
        return new Date(time.getTime());
    }
}
