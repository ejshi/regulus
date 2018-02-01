package org.regulus.common.util;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.Maps;

import net.sf.cglib.beans.BeanCopier;

/**
 * BeanCopier的扩展
 * 构建的BeanCopier实例放进内存中管理提示性能节省开销
 */
public class BeanCopierUtil {

    private static final Map<String, BeanCopier> BEAN_COPIER_CONCURRENT_MAP = Maps.newConcurrentMap();

    private BeanCopierUtil() {
    }

    public static void copy(Object source, Object target) {
        BeanCopier copier = getBeanCopier(source.getClass(), target.getClass());
        copier.copy(source, target, null);
    }

    private static BeanCopier getBeanCopier(Class<?> sourceClass, Class<?> targetClass) {
        String beanKey = generateKey(sourceClass, targetClass);
        BeanCopier copier;
        if (!BEAN_COPIER_CONCURRENT_MAP.containsKey(beanKey)) {
            copier = BeanCopier.create(sourceClass, targetClass, Boolean.FALSE);
            BEAN_COPIER_CONCURRENT_MAP.put(beanKey, copier);
        } else {
            copier = BEAN_COPIER_CONCURRENT_MAP.get(beanKey);
        }
        return copier;
    }

    private static String generateKey(Class<?> class1, Class<?> class2) {
        return class1.toString() + class2.toString();
    }

    /**
     * 复制列表
     *
     * @param sourceList
     * @param sClass
     * @param <S>
     * @return
     */
//    public static <S> List<S> invokeList(List<?> sourceList, Class<S> sClass) {
//        if (sourceList == null || sourceList.isEmpty()) {
//            return Collections.emptyList();
//        }
//        return Lists.transform(sourceList, input -> invoke(input, sClass));
//    }
    public static <S> List<S> invokeList(List<?> sourceList, Class<S> sClass) {
        if (sourceList == null || sourceList.isEmpty()) {
            return Collections.emptyList();
        }
        return sourceList.parallelStream().map(s -> invoke(s, sClass)).collect(Collectors.toList());
    }

    /**
     * 复制对象
     *
     * @param source
     * @param sClass
     * @param <S>
     * @return
     */
    public static <S> S invoke(Object source, Class<S> sClass) {
        if (source == null) {
            return null;
        }
        S s = null;
        try {
            s = sClass.newInstance();
            copy(source, s);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return s;

    }
}
