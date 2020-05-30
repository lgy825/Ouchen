package com.yunmu.core.util;

import java.security.SecureRandom;
import java.util.Date;
import java.util.UUID;

/**
 * 封装各种生成唯一性ID算法的工具类.
 * @author Lomis
 */
public class IdUtils {
	
	final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8',  
	        '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',  
	        'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',  
	        'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',  
	        'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',  
	        'Z' };  
	
	final static char[] digits2 = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};  

	private static SecureRandom random = new SecureRandom();

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间有-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid2() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 使用SecureRandom随机生成Long.
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}

	/**
	 * 基于Base62编码的SecureRandom随机生成bytes.
	 */
	public static String randomBase62(int length) {
		byte[] randomBytes = new byte[length];
		random.nextBytes(randomBytes);
		return Encodes.encodeBase62(randomBytes);
	}
	
	/** 
	 * 随机ID生成器，由数字、小写字母和大写字母组成 
	 * @param size 
	 * @return 
	 */  
	public static String getId(int size) {  
	    char[] cs = new char[size];  
	    for (int i = 0; i < cs.length; i++) {  
	        cs[i] = digits[random.nextInt(digits.length)];  
	    }  
	    return new String(cs);  
	} 
	
	/** 
	 * 随机ID生成器，由数字、小写字母和大写字母组成 
	 * @param size 
	 * @return 
	 */  
	public static String getRandNum(int size) {  
		char[] cs = new char[size];  
		for (int i = 0; i < cs.length; i++) {  
			cs[i] = digits2[random.nextInt(digits2.length)];  
		}  
		return new String(cs);  
	} 
	
	/**
	 * 【订单号】生成器
	 * @return
	 */
	public static String orderCodeGeneration(){
		return DateUtils.formatDate(new Date(), "yyyyMMddHHmmss") + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 4);
	}

	public static String getRandomByUUId() {
		int machineId = 1;//最大支持1-9个集群机器部署
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if(hashCodeV < 0) {//有可能是负数
			hashCodeV = - hashCodeV;
		}
		// 0 代表前面补充0
		// 4 代表长度为4
		// d 代表参数为正数型
		return machineId+String.format("%015d", hashCodeV);
	}
	
	public static void main(String[] args) {
		System.out.println(getId(12));
		System.out.println(getId(12));
		System.out.println(getId(12));
		System.out.println(getId(12));
		System.out.println(getId(12));
		System.out.println(getId(12));
		System.out.println(getId(12));
		System.out.println(getId(12));
	}
}
