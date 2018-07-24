package io.github.weechang.jutil.common.util;

import com.alibaba.fastjson.JSON;

import java.util.Map;


/**
 * java bean 常用util
 *
 * @author zhangwei
 * date 2018/7/24
 * time 15:35
 */
public class BeanUtil {

    /**
     * java bean 转 json字符串
     *
     * @param origin 源对象
     * @param <O> 源类
     * @return json 字符串
     */
    public static <O> String beanToJsonString(O origin){
        return JSON.toJSONString(origin);
    }

    /**
     * java bean 之间相互转换
     *
     * @param origin 源对象
     * @param <O> 源类
     * @param <T> 目标类
     * @param targetClazz 目标类
     */
    public static <O,T> T beanToBean(O origin, Class<T> targetClazz){
        String tempStr = BeanUtil.beanToJsonString(origin);
        return JSON.parseObject(tempStr, targetClazz);
    }

    /**
     * java map 转 类对象
     *
     * @param origin 源对象
     * @param <O> 源类
     * @param <T> 目标类
     * @param targetClazz 目标类
     */
    public static <O,T> T mapToBean(O origin, Class<T> targetClazz){
       return BeanUtil.beanToBean(origin, targetClazz);
    }

    /**
     * java map 转 类对象
     *
     * @param origin 源对象
     * @param <O> 源类
     */
    public static <O> Map beanToMap(O origin){
        return BeanUtil.beanToBean(origin, Map.class);
    }
}
