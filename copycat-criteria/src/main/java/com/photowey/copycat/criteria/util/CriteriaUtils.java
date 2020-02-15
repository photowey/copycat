package com.photowey.copycat.criteria.util;

import com.photowey.copycat.criteria.annotaion.ConditionProcessor;
import com.photowey.copycat.criteria.processor.CriteriaAnnotationProcessor;
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
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    public static Map<Class<? extends Annotation>, CriteriaAnnotationProcessor> doScan(String... basePackages) throws IOException {
        Map<Class<? extends Annotation>, CriteriaAnnotationProcessor> ANNOTATION_PROCESSOR_CACHE = new HashMap<>(32);
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        CachingMetadataReaderFactory cachingMetadataReaderFactory = new CachingMetadataReaderFactory();
        for (String basePackage : basePackages) {
            String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                    resolveBasePackage(basePackage) + '/' + DEFAULT_RESOURCE_PATTERN;
            Resource[] resources = pathMatchingResourcePatternResolver.getResources(packageSearchPath);
            for (Resource resource : resources) {
                try {
                    MetadataReader metadataReader = cachingMetadataReaderFactory.getMetadataReader(resource);
                    ClassMetadata classMetadata = metadataReader.getClassMetadata();
                    if (classMetadata.isInterface() || classMetadata.isAbstract()) {
                        continue;
                    }
                    String className = classMetadata.getClassName();
                    Class<?> clazz = ClassUtils.forName(className, CriteriaUtils.class.getClassLoader());
                    if (clazz.isAnnotationPresent(ConditionProcessor.class)) {
                        System.out.println(String.format("add the class name is:[%s]", className));
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

    public static String convertClassNameToResourcePath(String className) {
        Assert.notNull(className, "Class name must not be null");
        return className.replace(PACKAGE_SEPARATOR, PATH_SEPARATOR);
    }

    public static String resolveBasePackage(String basePackage) {
        return convertClassNameToResourcePath(basePackage);
    }

    private static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";
    /**
     * The package separator character: {@code '.'}.
     */
    private static final char PACKAGE_SEPARATOR = '.';

    /**
     * The path separator character: {@code '/'}.
     */
    private static final char PATH_SEPARATOR = '/';
}
