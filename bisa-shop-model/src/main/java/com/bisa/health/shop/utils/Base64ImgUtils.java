package com.bisa.health.shop.utils;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.util.UUID;

/**
 * Base64位上传图片处理工具类
 * @author Administrator
 */
@SuppressWarnings("restriction")
public class Base64ImgUtils {

    /**
     * base64字符串转byte[]
     * @param base64Str
     * @return
     */
    public static byte[] base64String2Byte(String base64Str) {
        return Base64.decodeBase64(base64Str);
    }

    /**
     * byte[]转base64
     * @param b
     * @return
     */
    public static String byte2Base64String(byte[] b) {
        return Base64.encodeBase64String(b);
    }

    /**
     * 获取随机文件名
     * @param pic_data
     * @return
     */
    public static String getFileNameFromBase64Img(String pic_data) {
        if (null != pic_data) {
            // 处理的文件名
            String writeFileName = UUID.randomUUID().toString().replace("-", "");
            String FileExt = "jpg";
            if (pic_data.startsWith("data:")) {
                int idx = pic_data.indexOf(";base64,");
                if (idx > 0) {
                    int idx2 = pic_data.indexOf('/');
                    if (idx2 > 0 && idx2 < idx) {
                        FileExt = pic_data.substring(idx2 + 1, idx).toLowerCase();
                    }
                }
            }
            writeFileName += '.' + FileExt;
            return writeFileName;
        }
        return null;
    }

    /**
     * 从base64位编码数据获取文件字节数据
     * @param pic_data
     * @return
     */
    public static byte[] getFileByteFromBase64(String pic_data) {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] decoderBytes = null;
        try {
            decoderBytes = decoder.decodeBuffer(pic_data);
            return decoderBytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
