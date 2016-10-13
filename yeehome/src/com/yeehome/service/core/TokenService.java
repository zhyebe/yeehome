package com.yeehome.service.core;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServlet;

import com.yeehome.entity.AccessToken;
import com.yeehome.util.StringUtils;
import com.yeehome.util.WebServiceUtils;

public class TokenService extends HttpServlet{
	private AccessToken accessToken;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init(){
//		accessToken=new AccessToken();
//		try {
//			accessToken.setAcToken(StringUtils.transJson(WebServiceUtils.execute(), "access_token"));
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		Timer timer=new Timer(true);
//		timer.schedule(new TimerTask() {
//			@Override
//			public void run() {
//				try {
//					accessToken.setAcToken(StringUtils.transJson(WebServiceUtils.execute(), "access_token"));
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}, 0, 2*60*60*1000);
//		
	}
}
