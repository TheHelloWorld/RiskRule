package com.riskrule.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liuzikun@lxfintech.com
 * @Title: BeanUtil
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: lxfintech.com
 * @Created on 2017/5/18 18:52
 */
public class BeanUtil {

    private BeanUtil(){

    }

    /**
     * bean 转化为 map
     * @param bean 要转成map的bean
     * @return
     */
    public static Map<String, Object> beanToMap(Object bean) throws Exception{
        HashMap<String, Object> map = new HashMap<>();
        if (null == bean) {
            return map;
        }
        Class<?> clazz = bean.getClass();
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);

        PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : descriptors) {
            String propertyName = descriptor.getName();
            if (!"class".equals(propertyName)) {
                Method method = descriptor.getReadMethod();
                Object result;
                result = method.invoke(bean);
                map.put(propertyName, result);
            }
        }
        return map;
    }

    /**
     * 将map转成bean
     * @param map 需要转成bean的map
     * @param bean 目标bean
     * @return
     */
    public static Object mapToBean(Map<String,Object> map,Object bean) throws Exception{
        Class clazz = bean.getClass();
        while(clazz.getSuperclass()!=null) {
            Field[] fields = clazz.getDeclaredFields();
            for(Field field : fields){
                if(map.get(field.getName()) != null){
                    field.setAccessible(true);
                    field.set(bean,map.get(field.getName()));
                }
            }
            clazz = clazz.getSuperclass();
        }
        return bean;
    }

    /**
     * 对列表进行去重排序
     * @param list 需要排序的list
     * @param <T> 类型
     * @return
     */
    public static<T> List<T> getSortList(List<T> list) {
        Set<T> treeSet = new TreeSet<>();
        treeSet.addAll(list);
        list.clear();
        list.addAll(treeSet);
        return list;
    }

    /**
     * 用正则替换{}中的字符串
     * @param str 要被替换的字符串
     * @return
     */
    public static String replacePlaceholder(String str) {
        Matcher m= Pattern.compile("\\{.*?\\}").matcher(str);
        while(m.find()) {
            if(str.equals(m.group().replace("{","").replace("}",""))) {
//                str=str.replace(m.group(),indicatorDynamicParam.getParamValue());
                break;
            }
        }
        return str;
    }

    /**
     * 从list中去除已存在元素
     * @param inputList
     * @param alreadyList
     * @param <T>
     * @return
     * @throws Exception
     */
    public static<T> List<T> getUnExistist(List<T> inputList,List<T> alreadyList,String field) throws Exception {
        List<T> list = new ArrayList<>();
        for(T t : inputList){
            Boolean flag = true;
            Map<String,Object> listMap = beanToMap(t);
            if(listMap.get(field) == null){
                throw new Exception("没有这个字段");
            }
            for(T at : alreadyList) {
                Map<String,Object> AlreadyListMap = beanToMap(at);
                if(listMap.get(field).equals(AlreadyListMap.get(field))){
                    flag = false;
                    AlreadyListMap.clear();
                    break;
                }
                AlreadyListMap.clear();
            }
            if(flag) {
                list.add(t);
            }
            listMap.clear();
        }
        return list;
    }

}
