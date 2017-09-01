package com.example.web.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;


import org.apache.commons.lang.StringUtils;

/**
 * @ClassName: StringUtil 
 * @Description: 字符串工具类 
 * @date 2017年9月1日 下午3:17:35 
 */
public class StringUtil {

    /**
     * @Title: readInputStream 
     * @Description: 读取InputStream
     * @param @param inStream
     * @param @return
     * @param @throws Exception    设定文件 
     * @return byte[]    返回类型 
     * @throws
     */
	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		byte[] data = outStream.toByteArray();// 网页的二进制数据
		outStream.close();
		inStream.close();
		return data;
	}
	
	/**
	 * @Title: splitByLength 
	 * @Description: 根据长度将字符串分割一个字符List
	 * @param @param buffer
	 * @param @param splitLength
	 * @return List<String>    返回类型 
	 * @throws
	 */
	public static List<String> splitByLength(StringBuffer buffer, int splitLength) {
		long time1 = System.currentTimeMillis();
		List<String> tmpList = null;
		if (buffer != null && buffer.length() > 0 && splitLength > 0) {
			int bufferLength = buffer.length();

			System.out.println("String bufferLength-->>" + bufferLength);
			tmpList = new ArrayList<String>();
			if (splitLength > bufferLength) {
				tmpList.add(buffer.toString());
			} else {
				int listSize = bufferLength / splitLength;
				if (bufferLength / splitLength > 0) {
					listSize++;
				}
				for (int ii = 1; ii <= listSize; ii++) {
					if (ii == listSize) {
						tmpList.add(buffer.substring(splitLength * (ii - 1)));
					} else {
						tmpList.add(buffer.substring(splitLength * (ii - 1), splitLength * ii));
					}
				}

			}
		}

		long time2 = System.currentTimeMillis();
		System.out.println("buffer User Time-->>" + (time2 - time1));
		return tmpList;
	}

	/**
	 * 
	 * @Title: splitByLength 
	 * @Description: 根据长度将字符串分割一个字符List 
	 * @param @param str
	 * @param @param splitLength
	 * @param @return    设定文件 
	 * @return List<String>    返回类型 
	 * @throws
	 */
	public static List<String> splitByLength(String str,int splitLength){
		long time1=System.currentTimeMillis();
		List<String> tmpList=null;
		if(str!=null && str.length() >0 && splitLength > 0){
			int strLength=str.length();
			System.out.println("String strLength-->>"+strLength);
			tmpList=new ArrayList<String>();
			if(splitLength > strLength){
				tmpList.add(str.toString());
			}else{
				int listSize=strLength / splitLength;
				if(strLength / splitLength > 0){
					listSize++;
				}
				for(int ii=1;ii<=listSize;ii++){
					if(ii==listSize){
						tmpList.add(str.substring(splitLength*(ii-1)));
					}else{
						tmpList.add(str.substring(splitLength*(ii-1),splitLength*ii));
					}
				}
				
				
			}
		}
		long time2=System.currentTimeMillis();
		System.out.println("String User Time-->>"+(time2-time1));
		return tmpList;
	}
	
    /**
     * 检查字符串是否为空
     * <p>Checks if a String is not empty (""), not null and not whitespace only.</p>
     *
     * <pre>
     * StringUtils.isNotBlank(null)      = false
     * StringUtils.isNotBlank("")        = false
     * StringUtils.isNotBlank("bob")     = true
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if the String is
     *  not empty and not null and not whitespace
     */
    public static boolean isNotBlank(String str) {
    	boolean isBlank = true;
    	if (str == null || "".equals(str.trim())) {
    		isBlank = false;
		}
        return isBlank;
    }
    
    /**
     * @Title: isEmpty 
     * @Description:   验证字符串是否是空字符串， 空为 true  非空为false
     * @param @param str
     * @param @return    设定文件 
     * @return boolean    返回类型 
     * @throws
     */
	public static boolean isEmpty(String str) { 
		boolean empty=false;
		if(str==null || str.trim().length() <=0){
			empty=true;
		}
		return empty;
	}
	
	/**
	 * @Title: firstUpper 
	 * @Description:  首字母转大写
	 * @param @param str
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	public static String firstUpper(String str) {
		if (isEmpty(str)) {
			return null;
		}
		char[] chars;
		chars = str.toCharArray();
		chars[0] -= 32;
		String upperStr = new String(chars);
		chars = null;
		return upperStr;
	}
	
	/**
	 * 获得真实IP
	 * @Title: getIpAddr 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param request
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	public static String getIpAddr(HttpServletRequest request) {
		
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		System.out.println("getIpAddr:" + ip);
		return ip;
	
	}
		
	// 将127.0.0.1形式的IP地址转换成十进制整数，这里没有进行任何错误处理
	public static long ipToNumber(String strIp) {
		long[] ip = new long[4];
		// 先找到IP地址字符串中.的位置
		int position1 = strIp.indexOf(".");
		int position2 = strIp.indexOf(".", position1 + 1);
		int position3 = strIp.indexOf(".", position2 + 1);
		// 将每个.之间的字符串转换成整型
		ip[0] = Long.parseLong(strIp.substring(0, position1));
		ip[1] = Long.parseLong(strIp.substring(position1 + 1, position2));
		ip[2] = Long.parseLong(strIp.substring(position2 + 1, position3));
		ip[3] = Long.parseLong(strIp.substring(position3 + 1));
		return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
	}
	      
	// 将十进制整数形式转换成127.0.0.1形式的ip地址
	public static String longToIP(long longIp) {
		StringBuffer sb = new StringBuffer("");
		// 直接右移24位
		sb.append(String.valueOf((longIp >>> 24)));
		sb.append(".");
		// 将高8位置0，然后右移16位
		sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
		sb.append(".");
		// 将高16位置0，然后右移8位
		sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
		sb.append(".");
		// 将高24位置0
		sb.append(String.valueOf((longIp & 0x000000FF)));
		return sb.toString();
	}

	// 这个方法是处理mysql中的特殊字符
	public static String escapeStr(String str) {
		if (isEmpty(str)) {
			return "";
		}
		String newKey = str;
		newKey = newKey.replaceAll("\\\\", "\\\\\\\\");
		newKey = newKey.replaceAll("'", "\\\\'");
		newKey = newKey.replaceAll("_", "\\\\_");
		newKey = newKey.replaceAll("%", "\\\\%");
		newKey = newKey.replaceAll("\"", "\\\\\"");

		return newKey;
	}
		
	/**
	 * @Title: getRandomStr   
	 * @Description: 获得随机数组成的字符串
	 * @return String 返回类型 
	 * @throws
	 */
	public static String getRandomStr() {

		int random1 = new Random().nextInt(1000);
		int random2 = new Random().nextInt(1000);
		int random3 = new Random().nextInt(1000);
		int random4 = new Random().nextInt(1000);
		int random5 = new Random().nextInt(1000);
		int random6 = new Random().nextInt(1000);

		StringBuilder pwdBuilder = new StringBuilder(20);

		pwdBuilder.append(random1);
		pwdBuilder.append(random3);
		pwdBuilder.append(random5);
		pwdBuilder.append(random2);
		pwdBuilder.append(random4);
		pwdBuilder.append(random6);
		return pwdBuilder.toString();
		
	}
		
	/**
	 * @Title: transcoding   
	 * @Description: 编码转化 
	 * @param name
	 * @return String 返回类型 
	 * @throws
	 */
	public static String transcoding(String name) {
		String name1 = name;
		try {
			if (!"".equals(name)) {
				name1 = new String(name.getBytes("iso8859-1"), "utf-8");
				return name1;
			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return name1;

	}
		
	/**
	 * 生成一个八位的随机密码
	 * 
	 * @return
	 */
	public static String genRandomPWD() {
		int length = 8;
		String val = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字
			if ("char".equalsIgnoreCase(charOrNum)) // 字符串
			{
				int choice = random.nextInt(2) % 2 == 0 ? 97 : 97; // 取得大写字母还是小写字母
				val += (char) (choice + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(charOrNum)) // 数字
			{
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}

	public static String genRandomNumberPWD() {
		int length = 10;
		String val = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			val += String.valueOf(random.nextInt(10));
		}
		return val;
	}

	public static void main(String[] args) {
	}
		 
}
