package com.bisa.health.shop.utils;

/**
 * 字符串遮盖处理
 * 124*****23
 * @author Administrator
 */
public class StringCoverKit {
    /**
     * cover the cellphone number
     * 133****1234
     * @param cellphone
     * @return
     */
    public static String phoneNumCover(String cellphone) {

        //cellphone length
        int length = cellphone.length();

        if (length == 11) {
            cellphone = cellphone.replaceAll("(\\d{3})\\d{4}(\\d{3})", "$1****$2");
        } else if (length > 6 && length < 11) {
            cellphone = cellphone.replaceAll("(\\d{3})\\d{3}(\\d{2})", "$1****$2");
        } else {
            cellphone = cellphone.replaceAll("(\\d{2})(\\d{2})", "$1****");
        }
        return cellphone;
    }

    /**
     * cover email address
     * 38****669@qq.com
     * @param email
     * @return
     */
    public static String mailCover(String mail) {

        StringBuffer email = new StringBuffer(mail);

        int index = email.indexOf("@");

        int half_eamil = (int) index / 2;

        email = email.replace(half_eamil, index, "****");

        mail = email.toString();

        return mail;

    }


}
