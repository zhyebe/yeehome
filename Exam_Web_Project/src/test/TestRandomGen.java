package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * 尝试制作一个方法，生成一个从整数1～n取出m个不重复的随机的数
 * m<=n;
 * @author Array
 * @see text
 * @since JDK1.6,J2EE5.0,Tomcat7
 * @version 1.4.3
 *
 */

public class TestRandomGen {
	/**
	 * 从1～n生成m个随机数的
	 * @param n
	 * @param m
	 * @return 随机数集合
	 */
	public static List<Integer> randomGen(int n,int m){
		List<Integer>result=new ArrayList<Integer>();
		Random random=new Random();
		while(result.size()<m){
			boolean flag=false;
			int i=random.nextInt(n+1);
			for(Integer e:result){
				if(i==e||i==0){
					flag=true;
				}
			}
			if(flag){
				continue;
			}else{
				result.add(new Integer(i));
			}
		}
		return result;
	}
	/**
	 * 直接用set集合不重复！
	 * @param n
	 * @param m
	 * @return
	 */
	public static Set<Integer>randomGen2(int n,int m){
		Set<Integer>result=new HashSet<Integer>();
		Random random=new Random();
		while(result.size()<m){
			int i=random.nextInt(n+1);
			if(i!=0){
				result.add(i);
			}
		}
		return result;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		List<Integer>result=randomGen(200,100);
//		for(Integer e:result){
//			System.out.println(e);
//		}
		Set<Integer>result2=randomGen2(200,100);
		for(Integer e:result2){
			System.out.println(e);
		}
	}

}
