package com.bisa.health.shop.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bisa.health.shop.utils.PhoneTypeUtil;

/**
 * 下载app的控制器
 * @author Administrator
 */

@Controller
public class AppDownloadController {

    @Value("${apk.name}")
    private String apk_name;
    @Value("${apk.path}")
    private String apk_path;

    private Logger logger = LogManager.getLogger(AppDownloadController.class);

    /**
     * 二维码扫码下载app地址
     * @return
     */
    @RequestMapping(value = "/appDownload", method = RequestMethod.GET)
    public String requestReport(HttpServletRequest request, @RequestParam(value = "phoneType", required = false) String phoneType) {

        if (StringUtils.isEmpty(phoneType)) {
            // 判断手机终端类型，跳转不同的页面
            String userAgent = request.getHeader("USER-AGENT").toLowerCase();
            logger.info("USER-AGENT: " + userAgent);
            phoneType = PhoneTypeUtil.phoneType(userAgent); // 手机类型
        }
        if (phoneType.equals("android")) {
            return "redirect:/appdownload/android";
        } else if (phoneType.equals("iphone")) {
            return "redirect:https://itunes.apple.com/hk/app/app-1933-kmb-lwb/id1170003707?l=zh&mt=8";
        } else {
            return "appDownload/download";
        }
    }

    /**
     * 安卓apk下载接口
     */
    @RequestMapping(value = "/appdownload/android", method = RequestMethod.GET, produces = "application/octet-stream;charset=UTF-8")
    public void downloadLocal(HttpServletResponse response) throws IOException {

        InputStream inStream = null;
        // 下载本地文件
        String fileName = apk_name.toString(); // 文件的默认保存名
        try {
            // 读到流中
            inStream = new FileInputStream(apk_path);// 文件的存放路径
            // 设置输出的格式
            response.reset();
            // response.setContentType("bin");
            response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setContentType("application/vnd.android.package-archive");// 设置response内容的类型
            response.setContentLength(inStream.available());
            // 设置文件大小
            // 循环取出流中的数据
            byte[] b = new byte[1024];
            int len;
            while ((len = inStream.read(b)) > 0) {
                response.getOutputStream().write(b, 0, len);
            }
            response.getOutputStream().flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inStream != null) {
                    inStream.close();
                }
                if (response.getOutputStream() != null) {
                    response.getOutputStream().close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
