package com.rain.utils.common;

import java.util.Map;

/**
 * Created by Administrator on 2018-6-28 0028.
 */
public class TypeChangeUtil {

    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null)
            return null;

        Object obj = beanClass.newInstance();

        org.apache.commons.beanutils.BeanUtils.populate(obj, map);

        return obj;
    }

    public static Map<?, ?> objectToMap(Object obj) {
        if(obj == null)
            return null;
        return new org.apache.commons.beanutils.BeanMap(obj);
    }

    public static int[] objectArrToIntArr(Object[] objects) {
        int[] ints = new int[objects.length];
        for (int i = 0; i < objects.length; i++) {
            ints[i] = Integer.parseInt(objects[i].toString());
        }
        return ints;
    }

    public static <K, V> K getMapFirstKey(Map<K, V> map) {
        K obj = null;
        for (Map.Entry<K, V> entry : map.entrySet()) {
            obj = entry.getKey();
            if (obj != null) {
                break;
            }
        }
        return obj;
    }
}
