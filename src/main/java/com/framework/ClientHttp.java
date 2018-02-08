package com.framework;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;


import javax.activation.MimetypesFileTypeMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.framework.util.MD5;
import com.framework.util.Tools;
public class ClientHttp {
	private static final Log log = LogFactory.getLog(ClientHttp.class);
	public static String httpRequestPost(String httpUrl,Map<String, String> params){
		URL url;
		try {
			url = new URL(httpUrl);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		//构建请求参数
		StringBuffer sb = new StringBuffer();
		if(params!=null){
		String temp="";
		for (Entry<String, String> e : params.entrySet()) {
		sb.append(e.getKey());
		sb.append("=");
		sb.append(e.getValue());
		sb.append("&");
		if(!e.getKey().equals("method")){
			temp=temp+e.getValue();
		}
		}
		String sign=MD5.MD5Encode(temp+"yztx");
		sb.append("sign");
		sb.append("=");
		sb.append(sign);
		log.info("MD5("+temp+")");
		}
		log.info("send_url:"+url);
		log.info("send_data:"+sb.toString());
		
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		//conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("Charset", "UTF-8");
		conn.setRequestProperty("ContentType","text/xml;charset=utf-8"); 
		OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
		writer.write(sb.toString());
		writer.flush();
		writer.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
		String temp = "";
		StringBuffer buffer = new StringBuffer();
		while((temp = reader.readLine()) != null){
			buffer.append(temp);
		}
        //服务器返回的结果
		String result = buffer.toString();
		reader.close();
		return result;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
    }
	public static String httpRequestPost(String httpUrl,String params){
		URL url;
		try {
			url = new URL(httpUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		//构建请求参数
		log.info("send_url:"+url);
		log.info("send_data:"+params);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		//conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("Charset", "UTF-8");
		conn.setRequestProperty("ContentType","text/xml;charset=utf-8"); 
		OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
		writer.write(params);
		writer.flush();
		writer.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
		String temp = "";
		StringBuffer buffer = new StringBuffer();
		while((temp = reader.readLine()) != null){
			buffer.append(temp);
		}
        //服务器返回的结果
		String result = buffer.toString();
		reader.close();
		return result;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
    }
	/**
	 * 远程请求-参数用&相隔再加密
	 * @param httpUrl
	 * @param params
	 * @return
	 */
    public static String httpRequest(String httpUrl,Map<String, String> params){
			URL url;
			try {
				url = new URL(httpUrl);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//构建请求参数
			StringBuffer sb = new StringBuffer();
			if(params!=null){
			String temp="";
			for (Entry<String, String> e : params.entrySet()) {
			sb.append(e.getKey());
			sb.append("=");
			sb.append(e.getValue());
			sb.append("&");
			temp=temp+e.getValue()+"&";
			}
			temp=temp.substring(0, temp.length() - 1);
			String sign=MD5.MD5Encode(temp);
			sb.append("sign");
			sb.append("=");
			sb.append(sign);
			log.info("MD5("+temp+")");
			}
			log.info("send_url:"+url);
			log.info("send_data:"+sb.toString());
			
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			//conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Charset", "UTF-8");
			conn.setRequestProperty("ContentType","text/xml;charset=utf-8"); 
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
			writer.write(sb.toString());
			writer.flush();
			writer.close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			String temp = "";
			StringBuffer buffer = new StringBuffer();
			while((temp = reader.readLine()) != null){
				buffer.append(temp);
			}
	        //服务器返回的结果
			String result = buffer.toString();
			reader.close();
			return result;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
	    }
	/**
	 * 上传图片
	 * 
	 * @param urlStr
	 * @param textMap
	 * @param fileMap
	 * @return
	 */
	public static String formUpload(String urlStr, Map<String, String> textMap,
			Map<String, File> fileMap) {
		String res = "";
		HttpURLConnection conn = null;
		String BOUNDARY = "---------------------------123821742118716"; //boundary就是request头和上传文件内容的分隔符
		try {
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn
					.setRequestProperty("User-Agent",
							"Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
			conn.setRequestProperty("Content-Type",
					"multipart/form-data; boundary=" + BOUNDARY);

			OutputStream out = new DataOutputStream(conn.getOutputStream());
			// text
			if (textMap != null) {
				StringBuffer strBuf = new StringBuffer();
				Iterator<?> iter = textMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					strBuf.append("\r\n").append("--").append(BOUNDARY).append(
							"\r\n");
					strBuf.append("Content-Disposition: form-data; name=\""
							+ inputName + "\"\r\n\r\n");
					strBuf.append(inputValue);
				}
				out.write(strBuf.toString().getBytes());
			}

			// file
			if (fileMap != null) {
				Iterator iter = fileMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					File file = (File) entry.getValue();
					if (file.length()<=0) {
						continue;
					}
					String filename = file.getName();
					String contentType = new MimetypesFileTypeMap()
							.getContentType(file);
					if (filename.endsWith(".png")) {
						contentType = "image/png";
					}
					if (contentType == null || contentType.equals("")) {
						contentType = "application/octet-stream";
					}

					StringBuffer strBuf = new StringBuffer();
					strBuf.append("\r\n").append("--").append(BOUNDARY).append(
							"\r\n");
					strBuf.append("Content-Disposition: form-data; name=\""
							+ inputName + "\"; filename=\"" + filename
							+ "\"\r\n");
					strBuf.append("Content-Type:" + contentType + "\r\n\r\n");

					out.write(strBuf.toString().getBytes());

					DataInputStream in = new DataInputStream(
							new FileInputStream(file));
					int bytes = 0;
					byte[] bufferOut = new byte[1024];
					while ((bytes = in.read(bufferOut)) != -1) {
						out.write(bufferOut, 0, bytes);
					}
					in.close();
				}
			}

			byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
			out.write(endData);
			out.flush();
			out.close();

			// 读取返回数据
			StringBuffer strBuf = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				strBuf.append(line).append("\n");
			}
			res = strBuf.toString();
			reader.close();
			reader = null;
		} catch (Exception e) {
			System.out.println("发送POST请求出错。" + urlStr);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		return res;
	}
	public static String httpRequestGet(String httpUrl){
		URL url;
		try {
			url = new URL(httpUrl);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.connect(); 
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
		String temp = "";
		StringBuffer buffer = new StringBuffer();
		while((temp = reader.readLine()) != null){
			buffer.append(temp);
		}
        //服务器返回的结果
		String result = buffer.toString();
		reader.close();
		return result;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
    }
	public static void main(String[] args) throws Exception{
		File f = new File("test.gif");  
	    System.out.println("Mime Type of " + f.getName() + " is " +  
	                         new MimetypesFileTypeMap().getContentType(f));  
	}

}
