package com.rain.utils.validate;
import com.rain.utils.encrypt.Digests;
import com.rain.utils.encrypt.Encodes;
import org.springframework.util.Base64Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类
 * Created by fred on 15/4/3.
 */
public class Validate{

    public static boolean checkChineseName(String name) {
        if (!name.matches("[\\u4e00-\\u9fa5]{2,10}")) {
            return false;
        } else{
            return true;
        }
    }

    /**
     * 验证Email
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        if (email == null) {
            return false;
        }
        String regex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
        return Pattern.matches(regex, email);
    }

    /**
     * 验证身份证号码
     * 居民身份证号码15位或18位，最后一位可能是数字或字母
     * @param identifyNumber
     * @return
     */
    public static boolean checkIdentifyNumber(String identifyNumber) {
        if (identifyNumber == null) {
            return false;
        }
        ICN icn = new ICN(identifyNumber);
        return icn.validate();
    }

    /**
     * 验证用户昵称，11位数字
     * @param mobile
     * @return
     */
    public static boolean checkMobile(String mobile) {
        if (mobile == null) {
            return false;
        }
        String regex = "^[1][3,4,5,8,7][0-9]{9}$";
        return Pattern.matches(regex, mobile);
    }

    //4-12位字符，汉字为2个字符
    public static boolean checkName(String name){
        if (name == null) {
            return false;
        }
        int len = getLength(name);
        if(len<4 || len>12){
            return false;
        }
        return true;
    }

    /**
     * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
     * @param  s 需要得到长度的字符串
     * @return int 得到的字符串长度
     */
    public static int getLength(String s) {
        int valueLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
        for (int i = 0; i < s.length(); i++) {
            // 获取一个字符
            String temp = s.substring(i, i + 1);
            // 判断是否为中文字符
            if (temp.matches(chinese)) {
                // 中文字符长度为2
                valueLength += 2;
            } else {
                // 其他字符长度为1
                valueLength ++;
            }
        }
        return  valueLength;
    }


    /**
     * 验证密码
     * @param password
     * @return
     */
    public static boolean checkPassword(String password) {
        if (password == null) {
            return false;
        }
//        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";//数字、字母组合
        String regex = "^[0-9A-Za-z~!@#$%^&*()_+:\"|<>?`=;',./-]{6,18}$";//字母、数字、特殊字符
        return password.matches(regex);
    }
    /**
     * 检查验证码
     * @param code
     * @return
     */
    public static boolean checkVerifyCode(String code) {
        if (code == null) {
            return false;
        }
        String regex = "^[0-9A-Za-z]{6}$";//字母或数字
        return code.matches(regex);
    }

    /**
     * 检查ip
     * @param ip
     * @return
     */
    public static boolean checkIp(String ip) {
        if (ip == null) {
            return false;
        }
        String regex = "^((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])\\.){3}(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])$";
        return ip.matches(regex);
    }


    /**
     * 生成随机盐
     * @return
     */
    public static String getSalt() {
        return Encodes.encodeHex(Digests.generateSalt(4));
    }


    /**
     * 验证证书
     * @param certificate
     * @return
     */
    public static boolean checkCertificate(String certificate) {
        if (certificate == null) {
            return false;
        }
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,15}$";//数字、字母组合
        // String regex = "^[0-9A-Za-z~!@#$%^&*()_+:\"|<>?`=;',./-]{6,18}$";//字母、数字、特殊字符
        return certificate.matches(regex);
    }

    /**
     * 全角转半角
     * @param input String.
     * @return 半角字符串
     * @author carl.shen
     */
    public static String ToDBC(String input) {


        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '\u3000') {
                c[i] = ' ';
            } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
                c[i] = (char) (c[i] - 65248);

            }
        }
        String returnString = new String(c);

        return returnString;
    }

    /**
     * 半角转全角
     * @param input String.
     * @return 全角字符串.
     * @author carl.shen
     */
    public static String ToSBC(String input) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ' ') {
                c[i] = '\u3000';
            } else if (c[i] < '\177') {
                c[i] = (char) (c[i] + 65248);

            }
        }
        return new String(c);
    }

    /**
     * 登录密码解密
     * base64解码并去掉 前3位，后3位
     * @param pwd
     * @return
     */
    public static String getBases64Decrypt(String pwd){
        String s = null;
        try {
            String k = new String(Base64Utils.decode(pwd.getBytes()), "UTF-8");
            s = k.substring(3,k.length()-3);
        }catch (Exception e){

        }
        return s;
    }

    /**
     * 文本自定义加密
     * base64
     * @param pwd
     * @return
     */
    public static String getBases64Encode(String pwd){
        String s = null;
        try {

            String k = new String(Base64Utils.decode(pwd.getBytes()), "UTF-8");
            s = k.substring(3,k.length()-3);
        }catch (Exception e){

        }
        return s;
    }

    /**
     * 正则匹配#{a-zA-Z0-9}
     * @param express
     * @return
     */
    public static Matcher matcherExpressStamp(String express){
        Pattern pattern = Pattern.compile("#\\{([a-zA-Z0-9:]*)\\}");
        Matcher matcher = pattern.matcher(express);
        return matcher;
    }
}