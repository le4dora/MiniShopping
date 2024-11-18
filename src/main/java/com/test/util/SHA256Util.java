package com.test.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Util {

    /**
     * 计算字符串的SHA-256哈希值
     *
     * @param input 输入的字符串
     * @return 返回64位的SHA-256哈希字符串
     */
    public static String toSHA256(String input) {
        try {
            // 获取SHA-256 MessageDigest实例
            if(input == null || input.equals("")){
                return null;
            }
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // 计算SHA-256哈希值
            byte[] hash = digest.digest(input.getBytes());
            // 将字节转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

    // 测试SHA256Util类
    public static void main(String[] args) {
        String originalString = "MiniShopping.edg.gdut";
        String sha256String = SHA256Util.toSHA256(originalString);
        System.out.println("Original String: " + originalString);
        System.out.println("SHA-256 Hash: " + sha256String);
    }
}