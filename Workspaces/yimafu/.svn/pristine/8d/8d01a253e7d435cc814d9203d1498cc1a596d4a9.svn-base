package com.yeepay.g3.core.ymf.utils.web;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

/**
 * http工具类
 * 
 */

public class HttpUtil {
	// 设置URL缓存时间为1小时
	static {
		System.setProperty("sun.net.inetaddr.ttl", "3600");
	}

	public HttpUtil() {
	}

	private static final Log logger = LogFactory.getLog(HttpUtil.class);

	/**
	 * 默认字符编码
	 */
	public static final String DEFAULT_CHARSET = "GBK";
	public static final String UTF8 = "UTF-8";

	public static final String HTTP_METHOD_POST = "POST";

	public static final String HTTP_METHOD_GET = "GET";

	public static final String HTTP_ERROR_MESSAGE = "http_error_message";

	/**
	 * 默认超时设置(1200秒)
	 */
	public static final int DEFAULT_TIMEOUT = 120000;

	/**
	 * 默认提交方式
	 */
	public static final String HTTP_PREFIX = "http://";

	public static final String HTTPS_PREFIX = "https://";

	// 最多只读取500000字符
	public static final int MAX_FETCHSIZE = 500000;

	public static String httpPost(String url, Map params) throws IOException {
		return httpRequest(url, params, HTTP_METHOD_POST, UTF8);
	}

	public static String httpPost(String url, String queryString) throws IOException {
		return httpRequest(url, queryString, HTTP_METHOD_POST, DEFAULT_CHARSET);
	}

	public static String httpGet(String url, Map params) throws IOException {
		return httpRequest(url, params, HTTP_METHOD_GET, UTF8);
	}

	public static String httpGet(String url, String queryString) throws IOException {
		return httpRequest(url, queryString, HTTP_METHOD_GET, DEFAULT_CHARSET);
	}

	/**
	 * 以建立HttpURLConnection方式发送请求
	 * 
	 * @param url
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @param method
	 *            请求方式
	 * @param charSet
	 * @return 通讯失败返回null, 否则返回服务端输出
	 */
	public static String httpRequest(String url, Map params, String method,
			String charSet) throws IOException {
		String queryString = parseQueryString(params, charSet);
		return httpRequest(url, queryString, method, charSet);
	}

	public static String httpRequest(String targetUrl, String queryString,
			String sMethod, String charSet) throws IOException {

		HttpURLConnection urlConn = null;
		URL destURL = null;
		if (targetUrl == null || targetUrl.trim().length() == 0) {
			throw new IllegalArgumentException("invalid targetUrl : "
					+ targetUrl);
		}
		targetUrl = targetUrl.trim();
		if (!targetUrl.toLowerCase().startsWith(HTTP_PREFIX)
				&& !targetUrl.toLowerCase().startsWith(HTTPS_PREFIX)) {
			targetUrl = HTTP_PREFIX + targetUrl;
		}
		if (queryString != null) {
			queryString = queryString.trim();
		}

		// 把参数sMethod转换为大写的字符串method，然后用method进行后续处理
		String method = null;
		if (sMethod != null) {
			method = sMethod.toUpperCase();
		}
		// POST或GET请求
		if (method == null
				|| !(method.equals(HTTP_METHOD_POST) || method
						.equals(HTTP_METHOD_GET))) {
			throw new IllegalArgumentException("invalid http method : "
					+ method);
		}

		String baseUrl = "";
		String params = "";
		String fullUrl = "";

		int index = targetUrl.indexOf("?");
		if (index != -1) {
			baseUrl = targetUrl.substring(0, index);
			params = targetUrl.substring(index + 1);
		} else {
			baseUrl = targetUrl;
		}
		if (queryString != null && queryString.trim().length() != 0) {
			if (params.trim().length() > 0) {
				params += "&" + queryString;
			} else {
				params += queryString;
			}
		}

		fullUrl = baseUrl + (params.trim().length() == 0 ? "" : ("?" + params));
		logger.info("请求地址：" + fullUrl);
		StringBuffer result = new StringBuffer(2000);
		try {
			if (method.equals(HTTP_METHOD_POST)) {
				destURL = new URL(baseUrl);
			} else {
				destURL = new URL(fullUrl);
			}

			urlConn = (HttpURLConnection) destURL.openConnection();
			logger.info("-->" + urlConn.getClass());
			urlConn.setRequestProperty("Content-Type",
					"text/x-www-form-urlencoded; charset=" + charSet);
			urlConn.setDoOutput(true);
			urlConn.setDoInput(true);
			urlConn.setConnectTimeout(30000);
			urlConn.setReadTimeout(30000);
			urlConn.setAllowUserInteraction(false);
			urlConn.setUseCaches(false);
			urlConn.setRequestMethod(method);
			urlConn.setConnectTimeout(DEFAULT_TIMEOUT);
			urlConn.setReadTimeout(DEFAULT_TIMEOUT);

			if (method.equals(HTTP_METHOD_POST)) {
				OutputStream os = urlConn.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os, charSet);
				osw.write(params);
				osw.flush();
				osw.close();
			}

			BufferedInputStream is = new BufferedInputStream(
					urlConn.getInputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(is,
					charSet));
			String temp = null;

			while ((temp = br.readLine()) != null) {
				result.append(temp);
				result.append("\n");

				if (result.length() > MAX_FETCHSIZE) {
					break;
				}
			}
			int responseCode = urlConn.getResponseCode();
			logger.info("ResponseCode[" + responseCode
					+ "], and resultString["+result.toString()+"]");
			if (responseCode != HttpURLConnection.HTTP_OK) {
//				ThreadContext.getContext().addMessage(HTTP_ERROR_MESSAGE,
//						"HttpResponseCode : " + responseCode);
				return null;
			}
			return result.toString();
		} catch (IOException e) {
			logger.warn("connection error : " + e.getMessage());
//			ThreadContext.getContext().addMessage(HTTP_ERROR_MESSAGE,
//					"connection error : " + e.getClass());
			throw e ;
		} finally {
			if (urlConn != null) {
				urlConn.disconnect();
			}
		}
	}

	public static String httpRequest(String targetUrl, String queryString,
			String sMethod, String charSet, String requestProperty) throws IOException {
		HttpURLConnection urlConn = null;
		URL destURL = null;
		if (targetUrl == null || targetUrl.trim().length() == 0) {
			throw new IllegalArgumentException("invalid targetUrl : "
					+ targetUrl);
		}
		targetUrl = targetUrl.trim();
		if (!targetUrl.toLowerCase().startsWith(HTTPS_PREFIX)
				&& !targetUrl.toLowerCase().startsWith(HTTP_PREFIX)) {// 如果不是https和http协议请求，则按http协议处理
			targetUrl = HTTP_PREFIX + targetUrl;
		}
		if (queryString != null) {
			queryString = queryString.trim();
		}
		// 把参数sMethod转换为大写的字符串method，然后用method进行后续处理
		String method = null;
		if (sMethod != null) {
			method = sMethod.toUpperCase();
		}
		// POST或GET请求
		if (method == null
				|| !(method.equals(HTTP_METHOD_POST) || method
						.equals(HTTP_METHOD_GET))) {
			throw new IllegalArgumentException("invalid http method : "
					+ method);
		}

		String baseUrl = "";
		String params = "";
		String fullUrl = "";

		int index = targetUrl.indexOf("?");
		if (index != -1) {
			baseUrl = targetUrl.substring(0, index);
			params = targetUrl.substring(index + 1);
		} else {
			baseUrl = targetUrl;
		}
		if (queryString != null && queryString.trim().length() != 0) {
			if (params.trim().length() > 0) {
				params += "&" + queryString;
			} else {
				params += queryString;
			}
		}

		fullUrl = baseUrl + (params.trim().length() == 0 ? "" : ("?" + params));
		StringBuffer result = new StringBuffer(2000);
		try {
			if (method.equals(HTTP_METHOD_POST)) {
				destURL = new URL(baseUrl);
			} else {
				destURL = new URL(fullUrl);
			}

			urlConn = (HttpURLConnection) destURL.openConnection();
			logger.info("-->" + urlConn.getClass());
			urlConn.setRequestProperty("Content-Type", requestProperty);
			urlConn.setDoOutput(true);
			urlConn.setDoInput(true);
			urlConn.setAllowUserInteraction(false);
			urlConn.setUseCaches(false);
			urlConn.setRequestMethod(method);
			urlConn.setConnectTimeout(DEFAULT_TIMEOUT);
			urlConn.setReadTimeout(DEFAULT_TIMEOUT);

			if (method.equals(HTTP_METHOD_POST)) {
				OutputStream os = urlConn.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os, charSet);
				osw.write(params);
				osw.flush();
				osw.close();
			}

			BufferedInputStream is = new BufferedInputStream(
					urlConn.getInputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(is,
					charSet));
			String temp = null;

			while ((temp = br.readLine()) != null) {
				result.append(temp);
				result.append("\n");

				if (result.length() > MAX_FETCHSIZE) {
					break;
				}
			}
			int responseCode = urlConn.getResponseCode();
			logger.info("------------------ResponseCode[" + responseCode
					+ "],and targetUrl[" + targetUrl + "] queryString["
					+ queryString + "]" + "and resultLength[" + result.length()
					+ "]");
			if (responseCode != HttpURLConnection.HTTP_OK) {
//				ThreadContext.getContext().addMessage(HTTP_ERROR_MESSAGE,
//						"HttpResponseCode : " + responseCode);
				return null;
			}
			return result.toString();
		} catch (IOException e) {
			logger.warn("connection error : " + e.getMessage());
//			ThreadContext.getContext().addMessage(HTTP_ERROR_MESSAGE,
//					"connection error : " + e.getClass());
			throw e ;
		} finally {
			if (urlConn != null) {
				urlConn.disconnect();
			}
		}
	}

	/**
	 * 把参数map转换成URL
	 * 
	 * @param params
	 * @param charSet
	 * @return
	 */
	public static String parseQueryString(Map params, String charSet) {
		if (null == params || params.keySet().size() == 0) {
			return "";
		}
		StringBuffer queryString = new StringBuffer(2000);
		for (Iterator i = params.keySet().iterator(); i.hasNext();) {
			String key = String.valueOf(i.next());
			Object obj = params.get(key);
			String value = "";
			if (obj != null) {
				value = obj.toString();
			}
			try {
				value = URLEncoder.encode(value, charSet);
			} catch (UnsupportedEncodingException ex) {
				logger.info("encode url error: " + ex.getMessage());
			}
			queryString.append(key);
			queryString.append("=");
			queryString.append(value);
			queryString.append("&");
		}
		String result = queryString.toString();
		if (result.endsWith("&")) {
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}

	public static String parseUrl(String targetUrl, String queryString) {
		if (targetUrl == null || targetUrl.trim().length() == 0) {
			throw new IllegalArgumentException("invalid targetUrl : "
					+ targetUrl);
		}
		targetUrl = targetUrl.trim();

		if (!targetUrl.startsWith("/")
				&& !targetUrl.toLowerCase().startsWith(HTTP_PREFIX)
				&& !targetUrl.toLowerCase().startsWith(HTTPS_PREFIX)) {
			targetUrl = HTTP_PREFIX + targetUrl;
		}

		if (queryString != null) {
			queryString = queryString.trim();
		}
		String baseUrl = "";
		String paramString = "";
		String fullUrl = "";
		int index = targetUrl.indexOf("?");
		if (index != -1) {
			baseUrl = targetUrl.substring(0, index);
			paramString = targetUrl.substring(index + 1);
		} else {
			baseUrl = targetUrl;
		}
		if (queryString != null && queryString.trim().length() != 0) {
			if (paramString.trim().length() > 0) {
				paramString += "&" + queryString;
			} else {
				paramString += queryString;
			}
		}
		fullUrl = baseUrl
				+ (paramString.trim().length() == 0 ? "" : ("?" + paramString));
		return fullUrl;
	}

	public static String parseUrl(String targetUrl, Map params, String charSet) {
		String queryString = parseQueryString(params, charSet);
		return parseUrl(targetUrl, queryString);
	}

	public static Map parseQueryString(String queryString) {
		if (queryString == null) {
			throw new IllegalArgumentException("queryString must be specified");
		}

		int index = queryString.indexOf("?");
		if (index > 0) {
			queryString = queryString.substring(index + 1);
		}

		String[] keyValuePairs = queryString.split("&");
		Map<String, String> map = new HashMap<String, String>();
		for (String keyValue : keyValuePairs) {
			if (keyValue.indexOf("=") == -1) {
				continue;
			}
			String[] args = keyValue.split("=");
			if (args.length == 2) {
				map.put(args[0], args[1]);
			}
			if (args.length == 1) {
				map.put(args[0], "");
			}
		}
		return map;
	}

	public static String parseUrl(String queryString) {
		if (queryString == null) {
			throw new IllegalArgumentException("queryString must be specified");
		}

		int index = queryString.indexOf("?");
		String targetUrl = null;
		if (index > 0) {
			targetUrl = queryString.substring(0, index);
		} else {
			targetUrl = queryString;
		}
		return targetUrl;
	}

	/**
	 * 发送服务器
	 * 
	 * @param url
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @return
	 * @throws Exception
	 */
	public static String sendToServer(String url, Map<String, Object> params)
			throws Exception {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		// 设置参数
		for (String key : params.keySet()) {
			BasicNameValuePair param = new BasicNameValuePair(key,
					(String) params.get(key));
			formParams.add(param);
		}
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams,
				"UTF-8");
		post.setEntity(entity);
		HttpResponse response = client.execute(post);
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == 200) {
			InputStream instream = response.getEntity().getContent();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					instream, "utf-8"));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = in.readLine()) != null) {
				buffer.append(line);
			}
			return buffer.toString();
		} else {
			throw new Exception(url + "不能访问.返回HTTP-" + statusCode + "错误请求");
		}
	}
	public static InputStream sendToServer(String url,
			Map<String, Object> params, Map<String, File> fileParams)
			throws Exception {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		// 设置文件
		MultipartEntity entity = new MultipartEntity();
		for (String key : fileParams.keySet()) {
			entity.addPart(key, new FileBody(fileParams.get(key)));
		}
		// 设置参数
		for (String key : params.keySet()) {
			entity.addPart(key, new StringBody(params.get(key).toString()));
		}
		post.setEntity(entity);
		HttpResponse response = client.execute(post);
		if (response.getStatusLine().getStatusCode() == 200) {
			InputStream instream = response.getEntity().getContent();
			return instream;
		} else {
			throw new Exception("请求地址(" + url + ")不能访问.HTTP-"
					+ response.getStatusLine().getStatusCode() + "错误请求");
		}
	}
}
