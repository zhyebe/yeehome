package com.yeehome.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeehome.util.StringUtils;

/**
 * 微信公众平台验证
 * @author zhangyong
 *
 */
@Controller
public class WeiXinController {
	
	@RequestMapping(value="/weixin.do",method=RequestMethod.GET)
	@ResponseBody
	public String accessing(String signature,String timestamp,String nonce,String echostr){  
		if(StringUtils.checkSignature(signature, timestamp, nonce)){
			System.out.println("微信公众平台开发验证通过");
			return echostr;
		}else{
			return null;
		}
	}
}
