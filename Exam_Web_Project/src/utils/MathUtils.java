package utils;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
/**
 * 数学工具类
 * @author Array
 * @see utils
 * @since JDK1.6,J2EE5.0,Tomcat7
 * @version 1.4.1
 */
public class MathUtils {

	/**
	 * 从size中随机获取不重复的n个整数
	 * @param n
	 * @param size
	 * @return
	 */
	public static Set<Integer> getSet(int n,List<Integer> ints){
		Random ran=new Random();
		Set<Integer> set=new HashSet<Integer>();
		int m=0;
		while(set.size()<n&&ints.size()>0){
			m=ran.nextInt(ints.size());
			set.add(ints.get(m));
		}
		return set;
	}
	/**
	 * 动态根据需要抽题的数量获取对应的SQL语句
	 * 获取sql语句
	 * @return sql
	 */
	public static String getSql(String srcSql
			,String repString,int num){
		String replace=","+repString;
		StringBuilder buider=new StringBuilder();
		buider.append(srcSql);
		buider.append("("+repString);
		for(int i=0;i<num-1;i++){
			buider.append(replace);
		}
		buider.append(");");
		return buider.toString();
	}
}
