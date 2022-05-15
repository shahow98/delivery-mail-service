package top.shahow.deliverymailservice.support;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class MD5 {
    public static String getMD5(String msg) {
        String md5Str = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] input = msg.getBytes();
            byte[] buff = messageDigest.digest(input);
            md5Str = bytesToHex(buff);

        } catch (NoSuchAlgorithmException e) {
            log.error("系统错误: ", e);
        }
        return md5Str;

    }

    private static String bytesToHex(byte[] bytes) {
        StringBuffer md5Str = new StringBuffer();
        int digital;
        for(int i = 0; i < bytes.length; i++) {
            digital = bytes[i];

            if(digital < 0) {
                digital += 256;
            }
            if (digital < 16) {
                md5Str.append("0");
            }
            md5Str.append(Integer.toHexString(digital));
        }
        return md5Str.toString().toLowerCase();
    }
}
