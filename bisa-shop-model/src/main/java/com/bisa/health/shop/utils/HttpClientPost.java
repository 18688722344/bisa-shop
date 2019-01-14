package com.bisa.health.shop.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

public class HttpClientPost {

	private static Logger logger = Logger.getLogger(HttpClientPost.class);
	private static final String DEAFULT_CHARSET = "UTF-8";
	private Map<String, String> parameter = new HashMap<String, String>();
	private HttpServletResponse response;

	public HttpClientPost(HttpServletResponse response) {
		this.response = response;
	}

	public HttpClientPost(HttpServletResponse response, Map<String, String> parameter) {
		this.parameter = parameter;
		this.response = response;
	}

	public void setParameter(String key, String value) {
		this.parameter.put(key, value);
	}

	public void sendByPost(String url) {
		this.sendByPost(url, DEAFULT_CHARSET);
	}

	public void sendByPost(String url, String charset) {
		this.response.setContentType("text/html");
		this.response.setCharacterEncoding(charset);
		PrintWriter out = null;
		try {
			out = this.response.getWriter();
		} catch (IOException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		String html = this.buildPostHTML(url, charset);
		logger.info(html);
		out.println(html);
		out.flush();
		out.close();
	}

	public void sendByPostTest(String url) {
		this.sendByPostTest(url, DEAFULT_CHARSET);

	}

	public void sendByPostTest(String url, String charset) {
		//		this.response.setContentType("text/html");
		//		this.response.setCharacterEncoding(charset);
		//		PrintWriter out = null;
		//		try {
		//			out = this.response.getWriter();
		//		} catch (IOException e) {
		//			logger.error(ExceptionUtils.getStackTrace(e));
		//		}
		String html = this.buildPostHTML(url, charset);
		logger.info(html);
		//		out.println(html);
		//		out.flush();
		//		out.close();
	}

	public String buildPostHTML(String url) {
		return this.buildPostHTML(url, DEAFULT_CHARSET);
	}

	/**
	 * 构建自动提交表单页面
	 * 
	 * @param url
	 *            post地址
	 * @return 提交表单页面
	 * @throws IOException
	 */
	public String buildPostHTML(String url, String charset) {
		StringBuilder html = new StringBuilder();
		html.append("<!DOCTYPE HTML>");
		html.append("<html>");
		html.append("<head>");
		html.append("<title>plase wait...</title>");
		html.append("<meta charset=\"" + charset + "\"/>");
		html.append("</head>");
		html.append("<body>");
		html.append("<form name=\"submitForm\" action=\"" + url + "\" accept-charset=\"" + charset + "\" method=\"post\">");
		Iterator<String> it = this.parameter.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			html.append("<input type=\"hidden\" name=\"" + key + "\" value=\"" + this.parameter.get(key) + "\"/>");
		}
		html.append("</form>");
		html.append("<script>window.document.submitForm.submit();</script> ");
		html.append("</body>");
		html.append("</html>");
		return html.toString();
	}
}
