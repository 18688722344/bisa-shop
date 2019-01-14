package com.bisa.health.shop.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class HttpURLConnHelper {

    /**
     * 从url获取文件内容的字节数组
     * @param fileUrl
     * @return
     */
    public byte[] loadFileByteFromURL(String fileUrl) {

        if (fileUrl.startsWith("http://")) {
            return this.httpConverBytes(fileUrl);
        } else if (fileUrl.startsWith("https://")) {
            return this.httpsConverBytes(fileUrl);
        } else {
            return null;
        }

    }

    /**
     * http链接获取文件流
     * @param fileUrl
     * @return
     */
    public InputStream httpConverStream(String fileUrl) {

        BufferedInputStream inStream = null;
        HttpURLConnection httpConn = null;
        try {
            // 创建url对象
            URL urlObj = new URL(fileUrl);
            // 创建HttpURLConnection对象，通过这个对象打开跟远程服务器之间的连接
            httpConn = (HttpURLConnection) urlObj.openConnection();
            httpConn.setDoInput(true);
            httpConn.setRequestMethod("GET");
            httpConn.setConnectTimeout(5000);
            httpConn.connect();
            // 判断跟服务器的连接状态。如果是200，则说明连接正常，服务器有响应
            if (httpConn.getResponseCode() == 200) {

                // 服务器有响应后，会将访问的url页面中的内容放进inputStream中，使用httpConn就可以获取到这个字节流
                inStream = new BufferedInputStream(httpConn.getInputStream());
                return inStream;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != inStream) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * https链接获取文件流 忽略证书
     * @param fileUrl
     * @return
     */
    public InputStream httpsConverStream(String fileUrl) {
        BufferedInputStream inStream = null;
        try {

            TrustManager[] tm = {new TrustAnyTrustManager()};
            SSLContext sc = SSLContext.getInstance("SSL", "SunJSSE");
            sc.init(null, tm, new java.security.SecureRandom());
            URL console = new URL(fileUrl);

            HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            conn.connect();

            inStream = new BufferedInputStream(conn.getInputStream());
            return inStream;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != inStream) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * @param path
     * @return
     * @MethodName httpConverBytes
     * @Description http路径文件内容获取
     */
    public byte[] httpConverBytes(String fileUrl) {
        BufferedInputStream in = null;
        ByteArrayOutputStream out = null;
        URLConnection conn = null;

        try {
            URL url = new URL(fileUrl);
            conn = url.openConnection();

            in = new BufferedInputStream(conn.getInputStream());

            out = new ByteArrayOutputStream(1024);
            byte[] temp = new byte[1024];
            int size = 0;
            while ((size = in.read(temp)) != -1) {
                out.write(temp, 0, size);
            }
            byte[] content = out.toByteArray();
            return content;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @param url
     * @return
     * @MethodName httpsConverBytes
     * @Description https路径文件内容获取
     */
    private byte[] httpsConverBytes(String fileUrl) {
        BufferedInputStream inStream = null;
        ByteArrayOutputStream outStream = null;

        try {

            TrustManager[] tm = {new TrustAnyTrustManager()};
            SSLContext sc = SSLContext.getInstance("SSL", "SunJSSE");
            sc.init(null, tm, new java.security.SecureRandom());
            URL console = new URL(fileUrl);

            HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            conn.connect();

            inStream = new BufferedInputStream(conn.getInputStream());
            outStream = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }

            byte[] content = outStream.toByteArray();
            return content;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != inStream) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != outStream) {
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    private static class TrustAnyTrustManager implements X509TrustManager {
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    public static String getExtensionName(String filename) {
        if (filename != null && filename.length() > 0) {
            int dot = filename.lastIndexOf('.');
            if (dot > -1 && dot < filename.length() - 1) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * 作用：实现网络访问文件，将获取到的数据保存在指定目录中
     * @param url ：访问网络的url地址
     * @return byte[]
     */
    public boolean saveFileFromURL(String url, File destFile) {
        HttpURLConnection httpConn = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(destFile));
            URL urlObj = new URL(url);
            httpConn = (HttpURLConnection) urlObj.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.setDoInput(true);
            httpConn.setConnectTimeout(10000);
            httpConn.connect();

            if (httpConn.getResponseCode() == 200) {
                bis = new BufferedInputStream(httpConn.getInputStream());
                int c = 0;
                byte[] buffer = new byte[8 * 1024];
                while ((c = bis.read(buffer)) != -1) {
                    bos.write(buffer, 0, c);
                    bos.flush();
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
                httpConn.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 作用：实现网络访问文件，先给服务器通过“POST”方式提交数据，再返回相应的数据
     * @param url    ：访问网络的url地址
     * @param params ：访问url时，需要传递给服务器的参数。格式为：username=wangxiangjun&password=abcde&
     *               qq=32432432
     *               为了防止传中文参数时出现编码问题。采用URLEncoder.encode()对含中文的字符串进行编码处理。
     *               服务器端会自动对进行过编码的字符串进行decode()解码。
     * @return byte[]
     */
    public byte[] doPostSubmit(String url, String params) {
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        HttpURLConnection httpConn = null;
        try {
            URL urlObj = new URL(url);
            httpConn = (HttpURLConnection) urlObj.openConnection();

            // 如果通过post方式给服务器传递数据，那么setDoOutput()必须设置为true。否则会异常。
            // 默认情况下setDoOutput()为false。
            // 其实也应该设置setDoInput()，但是因为setDoInput()默认为true。所以不一定要写。

            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            httpConn.setRequestMethod("POST");
            httpConn.setConnectTimeout(5 * 1000);
            // 设置请求方式。请求方式有两种：POST/GET。注意要全大写。
            // POST传递数据量大，数据更安全，地址栏中不会显示传输数据。
            // 而GET会将传输的数据暴露在地址栏中，传输的数据量大小有限制，相对POST不够安全。但是GET操作灵活简便。

            // 判断是否要往服务器传递参数。如果不传递参数，那么就没有必要使用输出流了。
            if (params != null) {
                byte[] data = params.getBytes();
                bos = new BufferedOutputStream(httpConn.getOutputStream());
                bos.write(data);
                bos.flush();
            }
            // 判断访问网络的连接状态
            if (httpConn.getResponseCode() == 200) {
                bis = new BufferedInputStream(httpConn.getInputStream());
                // 将获取到的输入流转成字节数组
                return streamToByte(bis);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
                httpConn.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 网络访问，上传附件 五个参数： 1、String url：指定表单提交的url地址 2、Map<String, String>
     * map：将上传控件之外的其他控件的数据信息存入map对象 3、String filePath：指定要上传到服务器的文件的客户端路径
     * 4、byte[] body_data：获取到要上传的文件的输入流信息，通过ByteArrayOutputStream流转成byte[]
     * 5、String charset：设置字符集
     */
    public String doPostSubmitBody(String url, Map<String, String> map, String filePath, byte[] body_data,
                                   String charset) {
        // 设置三个常用字符串常量：换行、前缀、分界线（NEWLINE、PREFIX、BOUNDARY）；
        final String NEWLINE = "\r\n";
        final String PREFIX = "--";
        final String BOUNDARY = "#";// 取代---------------------------7df3a01e37070c
        HttpURLConnection httpConn = null;
        BufferedInputStream bis = null;
        DataOutputStream dos = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            // 实例化URL对象。调用URL有参构造方法，参数是一个url地址；
            URL urlObj = new URL(url);
            // 调用URL对象的openConnection()方法，创建HttpURLConnection对象；
            httpConn = (HttpURLConnection) urlObj.openConnection();
            // 调用HttpURLConnection对象setDoOutput(true)、setDoInput(true)、setRequestMethod("POST")；
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            httpConn.setRequestMethod("POST");
            // 设置Http请求头信息；（Accept、Connection、Accept-Encoding、Cache-Control、Content-Type、User-Agent）
            httpConn.setUseCaches(false);
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Accept-Encoding", "gzip, deflate");
            httpConn.setRequestProperty("Cache-Control", "no-cache");
            httpConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
            httpConn.setRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30)");
            // 调用HttpURLConnection对象的connect()方法，建立与服务器的真实连接；
            httpConn.connect();

            // 调用HttpURLConnection对象的getOutputStream()方法构建输出流对象；
            dos = new DataOutputStream(httpConn.getOutputStream());
            // 获取表单中上传控件之外的控件数据，写入到输出流对象（根据HttpWatch提示的流信息拼凑字符串）；
            if (map != null && !map.isEmpty()) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    String key = entry.getKey();
                    String value = map.get(key);
                    dos.writeBytes(PREFIX + BOUNDARY + NEWLINE);
                    dos.writeBytes("Content-Disposition: form-data; " + "name=\"" + key + "\"" + NEWLINE);
                    dos.writeBytes(NEWLINE);
                    dos.writeBytes(URLEncoder.encode(value.toString(), charset));
                    // 或者写成：dos.write(value.toString().getBytes(charset));
                    dos.writeBytes(NEWLINE);
                }
            }

            // 获取表单中上传控件的数据，写入到输出流对象（根据HttpWatch提示的流信息拼凑字符串）；
            if (body_data != null && body_data.length > 0) {
                dos.writeBytes(PREFIX + BOUNDARY + NEWLINE);
                String fileName = filePath.substring(filePath.lastIndexOf(File.separatorChar) + 1);
                dos.writeBytes("Content-Disposition: form-data; " + "name=\"" + "uploadFile" + "\"" + "; filename=\""
                        + fileName + "\"" + NEWLINE);
                dos.writeBytes(NEWLINE);
                dos.write(body_data);
                dos.writeBytes(NEWLINE);
            }
            dos.writeBytes(PREFIX + BOUNDARY + PREFIX + NEWLINE);
            dos.flush();

            // 调用HttpURLConnection对象的getInputStream()方法构建输入流对象；
            byte[] buffer = new byte[8 * 1024];
            int c = 0;
            // 调用HttpURLConnection对象的getResponseCode()获取客户端与服务器端的连接状态码。如果是200，则执行以下操作，否则返回null；
            if (httpConn.getResponseCode() == 200) {
                bis = new BufferedInputStream(httpConn.getInputStream());
                while ((c = bis.read(buffer)) != -1) {
                    baos.write(buffer, 0, c);
                    baos.flush();
                }
            }
            // 将输入流转成字节数组，返回给客户端。
            return new String(baos.toByteArray(), charset);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (dos != null) {
                    dos.close();
                }
                if (bis != null) {
                    bis.close();
                }
                if (baos != null) {
                    baos.close();
                }
                httpConn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static byte[] streamToByte(InputStream is) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int c = 0;
        byte[] buffer = new byte[8 * 1024];
        try {
            while ((c = is.read(buffer)) != -1) {
                baos.write(buffer, 0, c);
                baos.flush();
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 判断url能否正常连接
     * @param pdf_url
     * @return
     */
    public boolean connectURL(String url) {
        HttpURLConnection httpConn = null;
        try {
            // 创建url对象
            URL urlObj = new URL(url);
            // 创建HttpURLConnection对象，通过这个对象打开跟远程服务器之间的连接
            httpConn = (HttpURLConnection) urlObj.openConnection();
            httpConn.setRequestProperty("Accept-Encoding", "identity");
            httpConn.setDoInput(true);
            httpConn.setRequestMethod("GET");
            httpConn.setConnectTimeout(5000);
            httpConn.connect();
            // String type=httpConn.getRequestProperty("Content-Type");
            // System.out.println("type>>>>>>>>>>>"+type);
            // 判断跟服务器的连接状态。如果是200，则说明连接正常，服务器有响应
            if (httpConn.getResponseCode() == 200) {
                if (httpConn.getContentLength() != -1) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 从下载文件的url获取文件头字节 用于判断文件类型
     * @param fileUrl
     * @return
     */
    public String getHeadByteFromFileUrl(String fileUrl) {

        byte[] bytes = loadFileByteFromURL(fileUrl);

        String fileCode = bytesToHexString(bytes);

        String headBytes = fileCode.substring(0, 13);

        return headBytes;
    }

    /**
     * 得到上传文件的文件头
     * @param src
     * @return
     */
    public String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 发送https请求
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return JSONObject(通过JSONObject.get ( key)的方式获取json对象的属性值)
     */
    public static JsonObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new HttpURLConnHelper.TrustAnyTrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);

            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            JsonObject returnData = new JsonParser().parse(buffer.toString()).getAsJsonObject();
            return returnData;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * URL编码（utf-8）
     * @param source
     * @return
     */
    public static String urlEncodeUTF8(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据内容类型判断文件扩展名
     * @param contentType 内容类型
     * @return
     */
    public static String getFileExt(String contentType) {
        String fileExt = "";
        if ("image/jpeg".equals(contentType))
            fileExt = ".jpg";
        else if ("audio/mpeg".equals(contentType))
            fileExt = ".mp3";
        else if ("audio/amr".equals(contentType))
            fileExt = ".amr";
        else if ("video/mp4".equals(contentType))
            fileExt = ".mp4";
        else if ("video/mpeg4".equals(contentType))
            fileExt = ".mp4";
        return fileExt;
    }


}