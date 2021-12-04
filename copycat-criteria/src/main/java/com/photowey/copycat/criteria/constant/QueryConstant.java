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
package com.photowey.copycat.criteria.constant;

/**
 * QueryBootConstant
 *
 * @author WcJun
 * @date 2020/09/19
 * @since 1.0.1
 */
public interface QueryConstant {

    /**
     * 注解处理器的包路径
     *
     * @since 1.0.0
     */
    String ANNOTATION_PROCESSOR_SCAN_BASE_PACKAGE = "com.photowey.copycat.criteria.processor";
    /**
     * 时间处理器的包路径
     */
    String TIME_PROCESSOR_SCAN_BASE_PACKAGE = "com.photowey.copycat.criteria.time";

    /**
     * 默认扫描的问题件类型
     */
    String DEFAULT_RESOURCE_PATTERN = "**/*.class";
    /**
     * 包分隔符: {@code '.'}.
     */
    char PACKAGE_SEPARATOR = '.';

    /**
     * 路径分隔符: {@code '/'}.
     */
    char PATH_SEPARATOR = '/';
}
