package com.ocean.angel.tool.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.KeyUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;

public class AESUtil {

    private static final String DB_AES_KEY = "HIGt#hbjj&GU2lgj";

    /**
     * 解密
     */
    public static String decrypt(String value) {
        byte[] key = KeyUtil.generateKey(SymmetricAlgorithm.AES.getValue(), DB_AES_KEY.getBytes()).getEncoded();
        AES aes = SecureUtil.aes(key);
        return aes.decryptStr(value, CharsetUtil.CHARSET_UTF_8);
    }

    /**
     * 加密
     */
    public static String encrypt(String value) {
        byte[] key = KeyUtil.generateKey(SymmetricAlgorithm.AES.getValue(), DB_AES_KEY.getBytes()).getEncoded();
        AES aes = SecureUtil.aes(key);
        return aes.encryptHex(value);
    }
}
