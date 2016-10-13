package sseqiu.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {
	public static final int LENGTH=6;
	public static List<String> split(String str){
		List<String> redBalls=new ArrayList<String>();
		String[] newStrs=new String[LENGTH];
		newStrs=str.split(",");
		for(String newStr:newStrs){
			redBalls.add(newStr);
		}
		return redBalls;
	}
}
