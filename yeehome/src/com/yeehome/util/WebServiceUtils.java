package com.yeehome.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * 接口数据统一封装
 * @author zhyeb
 *
 */
public class WebServiceUtils {
	private static final String UL="https://api.weixin.qq.com/cgi-bin/token?";
	private static final String GT="grant_type=";
	private static final String AP="appid=";
	private static final String ST="secret=";
	
	private static final String grant_type="client_credential";
	private static final String appid="wx028bb0ba00fd7918";
	private static final String secret="342d3ab25fea511bcb1d36c46aea7e97";
	
	public static String execute() throws IOException{
		URL url=new URL(UL+GT+grant_type+"&"+AP+appid+"&"+ST+secret);
		HttpsURLConnection conn=getConnection(url, "GET", "text/html;charset=UTF-8");
		conn.connect();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				conn.getInputStream(), "UTF-8"));
		// 返回值结果
		String ResultMsg = in.readLine();
		in.close();
		return ResultMsg;
	}
	
	private static HttpsURLConnection getConnection(URL url, String method, String ctype)  
            throws IOException {  
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();  
        conn.setRequestMethod(method);  
        conn.setDoInput(true);  
        conn.setDoOutput(true);  
        conn.setRequestProperty("Accept", "text/xml,text/javascript,text/html");  
        conn.setRequestProperty("User-Agent", "stargate");  
        conn.setRequestProperty("Content-Type", ctype);  
        return conn;  
    }  
}
