package com.rain.utils.encrypt;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

/**
 * Created by Administrator on 2018\2\5 0005.
 */
public class MD5Util {
    private static final String DEFAULT_HASH = "HmacSHA256";
    public static final String KEY_SHA = "SHA";
    public static final String KEY_MD5 = "MD5";
    public static final String KEY_MAC = "HmacMD5";
    private static final String DEFAULT_CHARSET = "UTF-8";

    public static String sha256Hex(String signingKey, String stringToSign) {
        try {
            Mac mac = Mac.getInstance(DEFAULT_HASH);
            mac.init(new SecretKeySpec(signingKey.getBytes(DEFAULT_CHARSET), DEFAULT_HASH));
            byte[] result = mac.doFinal(stringToSign.getBytes(DEFAULT_CHARSET));
            return Encodes.encodeHex(result);
        } catch (Exception ex) {
        }
        return null;
    }
}
