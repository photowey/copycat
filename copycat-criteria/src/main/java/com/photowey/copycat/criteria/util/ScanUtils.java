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

import com.photowey.copycat.criteria.annotaion.ConditionProcessor;
import com.photowey.copycat.criteria.constant.QueryConstant;
import com.photowey.copycat.criteria.processor.CriteriaAnnotationProcessor;
import com.photowey.copycat.criteria.time.TimeProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * 包扫描工具类
 *
 * @author WcJun
 * @date 2020/09/119
 * @since 1.1.0
 */
public final class ScanUtils {

    private static final Logger log = LoggerFactory.getLogger(ScanUtils.class);

    private ScanUtils() {
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

    /**
     * 执行包扫描
     *
     * @param basePackages 扫描的基础包列表
     * @return
     * @throws IOException
     * @since 1.1.0
     */
    public static Map<Class<? extends Annotation>, CriteriaAnnotationProcessor> doScan(String... basePackages) throws IOException {
        Map<Class<? extends Annotation>, CriteriaAnnotationProcessor> ANNOTATION_PROCESSOR_CACHE = new HashMap<>(32);
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        CachingMetadataReaderFactory cachingMetadataReaderFactory = new CachingMetadataReaderFactory();
        for (String basePackage : basePackages) {
            String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                    resolveBasePackage(basePackage) + '/' + QueryConstant.DEFAULT_RESOURCE_PATTERN;
            Resource[] resources = pathMatchingResourcePatternResolver.getResources(packageSearchPath);
            for (Resource resource : resources) {
                try {
                    MetadataReader metadataReader = cachingMetadataReaderFactory.getMetadataReader(resource);
                    ClassMetadata classMetadata = metadataReader.getClassMetadata();
                    if (classMetadata.isInterface() || classMetadata.isAbstract()) {
                        continue;
                    }
                    String className = classMetadata.getClassName();
                    Class<?> clazz = ClassUtils.forName(className, ScanUtils.class.getClassLoader());
                    if (clazz.isAnnotationPresent(ConditionProcessor.class)) {
                        ConditionProcessor annotation = clazz.getAnnotation(ConditionProcessor.class);
                        CriteriaAnnotationProcessor processor = (CriteriaAnnotationProcessor) clazz.getDeclaredConstructor().newInstance();
                        Class<? extends Annotation> condition = annotation.targetAnnotation();
                        ANNOTATION_PROCESSOR_CACHE.put(condition, processor);
                        log.info("the condition annotation:[{}],and processor:[{}]",
                                condition.getSimpleName(),
                                processor.getClass().getSimpleName()
                        );
                    }
                } catch (Exception e) {
                    // Ignore
                }
            }
        }

        return ANNOTATION_PROCESSOR_CACHE;
    }

    /**
     * 时间处理器扫描
     *
     * @param basePackages 扫描的基础包列表
     * @return
     * @throws IOException
     * @since 1.2.0
     */
    public static Map<Class<?>, TimeProcessor<?>> doTimeScan(String... basePackages) throws IOException {
        Map<Class<?>, TimeProcessor<?>> TIME_PROCESSOR_CACHE = new HashMap<>(8);
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        CachingMetadataReaderFactory cachingMetadataReaderFactory = new CachingMetadataReaderFactory();
        for (String basePackage : basePackages) {
            String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                    resolveBasePackage(basePackage) + '/' + QueryConstant.DEFAULT_RESOURCE_PATTERN;
            Resource[] resources = pathMatchingResourcePatternResolver.getResources(packageSearchPath);
            for (Resource resource : resources) {
                try {
                    MetadataReader metadataReader = cachingMetadataReaderFactory.getMetadataReader(resource);
                    ClassMetadata classMetadata = metadataReader.getClassMetadata();
                    if (classMetadata.isInterface() || classMetadata.isAbstract()) {
                        continue;
                    }
                    String className = classMetadata.getClassName();
                    Class<?> clazz = ClassUtils.forName(className, ScanUtils.class.getClassLoader());
                    if (TimeProcessor.class.isAssignableFrom(clazz)) {
                        TimeProcessor processor = (TimeProcessor) clazz.getDeclaredConstructor().newInstance();
                        TIME_PROCESSOR_CACHE.put(clazz, processor);
                        log.info("the condition time:[{}],and processor:[{}]", clazz.getSimpleName(), processor.getClass().getSimpleName()
                        );
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    // Ignore
                }
            }
        }

        return TIME_PROCESSOR_CACHE;
    }

    public static String convertClassNameToResourcePath(String className) {
        Assert.notNull(className, "Class name must not be null");
        return className.replace(QueryConstant.PACKAGE_SEPARATOR, QueryConstant.PATH_SEPARATOR);
    }

    public static String resolveBasePackage(String basePackage) {
        return convertClassNameToResourcePath(basePackage);
    }
}
