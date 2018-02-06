package com.rain.utils.sesstion;

import java.util.Set;

/**
 * Created by Administrator on 2018\2\6 0006.
 */
public interface SesstionUtil {
    /**
     * 存放key-value值 永久
     * @param key
     * @param value
     */
    void set(String key, String value);

    /**
     * 存放key-value值 期限time(ms)
     * @param key
     * @param value
     * @param time
     */
    void set(String key, String value, long time);

    /**
     * 获取String值
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 删除某个key数据
     * @param key
     */
    void delete(String key);


    Set keys(String patternKey);
}
