package com.bisa.health.shop.utils;

import java.util.UUID;

/**
 * 生成有限长度UUID
 * @author Administrator
 */
public class EightByteUUIDKit {
    public static String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8",
            "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"};

    public static String[] nums = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public static String generateShortUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");

        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }

    public static String randomShortUuidNum() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        //取当前时间的毫秒数
        String time = String.valueOf(System.currentTimeMillis());
        //随机数确定截取的字符起始位置
        int index = (int) (Math.random() * 10 + 1);
        //截取毫秒数起始位置后四位
        String time_str = time.substring(time.length() - 4, time.length());
        //截取UUID起始位置后四位
        String str = uuid.substring(index, index + 4);
        shortBuffer.append(str + time_str);
        return shortBuffer.toString();
    }

    //生成6位uuid
    public static String randomSixUuidNum() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        //随机数确定截取的字符起始位置
        int index = (int) (Math.random() * 10 + 1);
        //截取UUID起始位置后四位
        String str = uuid.substring(index, index + 6);
        shortBuffer.append(str);
        return shortBuffer.toString();
    }


}
