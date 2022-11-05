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
package com.photowey.copycat.criteria.annotaion;

import java.lang.annotation.*;

/**
 * {@code DynamicSelect}
 * <p>
 * 通过取该注解修饰的属性的值来填充
 * -{@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper} 的 {@code select}
 * 属性
 *
 * <p>
 * For example:
 * <pre>
 *     public class Hello {
 *         {@literal @}DynamicSelect
 *         private Set<String> fields = new HashSet<>();
 *     }
 * </pre>
 *
 * @author photowey
 * @date 2022/11/05
 * @since 1.0.0
 */
@Documented
@CriteriaQuery
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DynamicSelect {

    // 慎重使用 - 该写法可能导致 SQL 注入问题
}
