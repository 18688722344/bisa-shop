package com.bisa.health.shop.utils;

public class MessageUtils {

    private int count = 0;

    private static final String MIN_REPORT_MESSAGE = "新的15分钟报告已生成，点击查看。";

    private static final String HOUR_REPORT_MESSAGE = "新的24小时报告已生成，点击查看。";

    private String MIN_SERVICE_MESSAGE = "您的15分钟报告剩余次数不足" + count + "次。";

    private String HOUR_SERVICE_MESSAGE = "您的24小时报告剩余次数不足" + count + "次。";

    private String ALARM_SERVICE_MESSAGE = "您的平安钟服务将于" + count + "天后到期。";

    private String NEED_UPLOAD_DATA = "您的报告数据异常，请重新监测。";

    /**
     * @param count 服务剩余次数
     */
    public MessageUtils(int count) {
        super();
        this.count = count;
    }

    public MessageUtils() {
        super();
    }

    /**
     * @param type 1-15min report
     *             2-24hour report
     *             9-alarm service
     *             10-15min service
     *             11-24hour service
     * @return
     */
    public String takeMessageByType(int type) {

        switch (type) {
            case 1:
                return MIN_REPORT_MESSAGE;
            case 2:
                return HOUR_REPORT_MESSAGE;
            case 3:
                return NEED_UPLOAD_DATA;
            case 9:
                if (count == 0) {
                    return null;
                }
                return ALARM_SERVICE_MESSAGE;
            case 10:
                if (count == 0) {
                    return MIN_REPORT_MESSAGE;
                }
                return MIN_SERVICE_MESSAGE;
            case 11:
                if (count == 0) {
                    return HOUR_REPORT_MESSAGE;
                }
                return HOUR_SERVICE_MESSAGE;

            default:
                return null;
        }

    }

}
