package com.hope.saas.api.util.util;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Maduo
 * @date 2020/3/20 14:36
 */
public class BeanUtil {

    /**
     * 一个bean对象转成另外一个bean对象 属性一直才赋值，不一致则忽略
     *
     * @param entity
     * @param dto
     * @param <T>
     * @return
     */
    public static <T> T beanToBean(Object entity, Class<T> dto) {
        if (entity == null) {
            return null;
        }
        if (entity instanceof String) {
            return JSON.parseObject((String) entity, dto);
        }
        return JSON.parseObject(JSON.toJSONString(entity), dto);
    }

    /**
     * 一个bean转成多个bean
     *
     * @param entity
     * @param dtos
     * @return
     */
    public static Object[] beanToBeanArray(Object entity, Class<?>... dtos) {
        if (dtos == null) {
            return null;
        }
        if (entity == null) {
            return null;
        }
        String ste = "";
        if (entity instanceof String) {
            ste = (String) entity;
        } else {
            ste = JSON.toJSONString(entity);
        }
        Object[] pos = new Object[dtos.length];
        for (int i = 0; i < dtos.length; i++) {
            pos[i] = JSON.parseObject(ste, dtos[i]);
        }
        return pos;
    }

    /**
     * list bean 转换为另外一个list bean
     *
     * @param objs
     * @param dto
     * @param <T>
     * @return
     */
    public static <T> List<T> beanToBeanList(List objs, Class<T> dto) {
        if (objs == null) {
            return null;
        }
        List<T> list = JSON.parseArray(JSON.toJSONString(objs), dto);
        return list;
    }

    /**
     * 拷贝时忽略空属性
     *
     * @param src
     * @param target
     */
    public static void copyPropertiesIgnoreNull(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    /**
     * 拷贝时忽略空属性和空串
     *
     * @param src
     * @param target
     */
    public static void copyPropertiesIgnoreNullAndEmptyStr(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullAndEmptyStrPropertyNames(src));
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static String[] getNullAndEmptyStrPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null || "".equals(srcValue)) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
