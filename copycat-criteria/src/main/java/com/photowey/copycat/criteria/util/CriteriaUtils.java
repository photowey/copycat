/*
 * Copyright © 2019 photowey (photowey@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.photowey.copycat.criteria.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
