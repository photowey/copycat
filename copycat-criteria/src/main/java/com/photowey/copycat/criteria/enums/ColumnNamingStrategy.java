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
