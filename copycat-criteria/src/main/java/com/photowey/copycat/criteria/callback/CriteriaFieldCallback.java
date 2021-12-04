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
package com.photowey.copycat.criteria.callback;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 回调函数 - 查找属性对应的注解处理器进行处理
 *
 * @author WcJun
 * @date 2019/05/12
 */
@FunctionalInterface
public interface CriteriaFieldCallback {

    /**
     * 调用注解处理器 - 处理字段
     *
     * @param field              Query对象的属性
     * @param criteriaAnnotation 条件注解
     * @return 布尔值
     */
    boolean invoke(final Field field, final Annotation criteriaAnnotation);
}
