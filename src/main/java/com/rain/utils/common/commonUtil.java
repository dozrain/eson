package com.rain.utils.common;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by Administrator on 2018-6-28 0028.
 */
public class commonUtil {
    /**
     * 获取数组中最大连续数字
     * */
    public static int maxContinuousNum(int[] ints) {
        int max = 1; // 最大连续的数字个数
        int count = 1;
        for (int i = 0; i < ints.length - 1; ++i) {
            if (ints[i] + 1 == ints[i+1]) {
                count++;
                max = max > count ? max : count;
            } else {
                count = 1;
            }
        }
        return max;
    }

    public static List continuousNum(int[] ints, Map map) {
        List continuousNumSum = new ArrayList();
        LinkedList<Integer> continuousNum = new LinkedList(); // 连续的子数组
        continuousNum.add(ints[0]);
        for (int i = 0; i < ints.length - 1; ++i) {
            if (ints[i] + 1 == ints[i+1]) {
                continuousNum.add(ints[i+1]);
            } else {
                if (continuousNum.size() > 1) {
                    continuousNumSum.add(new ArrayList(continuousNum));
                }
                continuousNum.clear();
                continuousNum.add(ints[i+1]);
            }
        }
        if(continuousNum.size()>1){
            continuousNumSum.add(new ArrayList(continuousNum));
        }
        return continuousNumSum;
    }

    public static String getMaxString(String[] strArray){
        if(strArray.length == 0){return null;}
        String prefix = strArray[0];
        Arrays.sort(strArray);
        for (int i = 0; i < strArray.length; i++)
            while (strArray[i].indexOf(prefix) != 0) {//返回一个整数值，指出 String 对象内子字符串的开始位置。如果没有找到子字符串，则返回-1。
                prefix = prefix.substring(0, prefix.length() - 1);//子字符串开始位置不为0则缩小子字符串，再进行对比
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }

    public static void fileOutPut(String path,HSSFWorkbook wb){
        //响应到客户端
        try {
            File file = new File(path);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            wb.write(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取对象的所有属性值，返回一个对象数组
     * */
    public static Object[] getFiledValues(Object o){
        String[] fieldNames=commonUtil.getFiledName(o);
        Object[] value=new Object[fieldNames.length];
        for(int i=0;i<fieldNames.length;i++){
            value[i]=commonUtil.getFieldValueByName(fieldNames[i], o);
        }
        return value;
    }
    /**
     * 获取属性名数组
     * */
    public static String[] getFiledName(Object o){
        Field[] fields=o.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for(int i=0;i<fields.length;i++){
            System.out.println(fields[i].getType());
            fieldNames[i]=fields[i].getName();
        }
        return fieldNames;
    }
    /**
     * 根据属性名获取属性值
     * */
    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {
            return null;
        }
    }
}
