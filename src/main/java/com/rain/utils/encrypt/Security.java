package com.rain.utils.encrypt;


import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by tpeng on 2016/5/26.
 */
@Component("securityService")
public class Security {

    private static String SALT_LOGIN = "(_#ESON#_)";

    /**
     * 获取加密后的密码
     * @param pwd
     * @param pwdSalt
     * @return
     */
    public static String hashLoginPwd(String pwd, String pwdSalt) {
        return MD5Util.sha256Hex(pwd + SALT_LOGIN, pwdSalt);
    }
    /**
     * 生成SessionKey
     * @return
     */
    public static String generateSessionKey(){
        return UUID.randomUUID().toString();
    }

}
