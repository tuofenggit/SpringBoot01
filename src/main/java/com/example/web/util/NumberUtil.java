package com.example.web.util;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class NumberUtil {
	
	private static Logger logger = Logger.getLogger(NumberUtil.class);
	
	// 默认除法运算精度
	private static final Integer DEF_DIV_SCALE = 2;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(div(1D, 3D));
	}
	
	/**
	 * @Title: isNumber   
	 * @Description: 验证是否为数字(所有数值型，包括正负整数、小数、0)
	 * @param str
	 * @return boolean 返回类型 
	 * @throws
	 */
	public static boolean isNumber(String str) {
		logger.info("str==" + str);
		boolean flag = false;
		if (!StringUtil.isEmpty(str)) {
			Pattern pattern = Pattern.compile("0|(-?((0\\.\\d+)|(([1-9]\\d*)(\\.\\d+)?)))");
			flag = pattern.matcher(str).matches();
			pattern = null;
		}
		return flag;
	}

	/**
	 * 验证是否为整型数字(<0、=0、>0的整数)
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str) {
		logger.info("str==" + str);
		boolean flag = false;
		if (!StringUtil.isEmpty(str)) {
			Pattern pattern = Pattern.compile("-?\\d+");
			// Pattern pattern=Pattern.compile("0|(-?[1-9]\\d*)");
			flag = pattern.matcher(str).matches();
			pattern = null;
		}
		return flag;
	}

	/**
	 * 验证是否正整数(>0)
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isPosInteger(String str) {
		logger.info("str==" + str);
		boolean flag = false;
		if (!StringUtil.isEmpty(str)) {
			Pattern pattern = Pattern.compile("\\d+");
			flag = pattern.matcher(str).matches();
			pattern = null;
		}
		return flag;
	}

	/**
	 * 验证是否非负整数(>=0)
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNonInteger(String str) {
		logger.info("str==" + str);
		boolean flag = false;
		if (!StringUtil.isEmpty(str)) {
			Pattern pattern = Pattern.compile("0|([1-9]\\d*)");
			flag = pattern.matcher(str).matches();
			pattern = null;
		}
		return flag;
	}

	/**
	 * 将字符串转成Integer型
	 * 
	 * @param str
	 * @return
	 */
	public static Integer parseInteger(String str) {
		logger.info("str==" + str);
		Integer integer = null;
		if (isInteger(str)) {
			integer = Integer.valueOf(str);
		}
		return integer;
	}

	/**
	 * 将字符串转成Integer型,默认为0
	 * 
	 * @param str
	 * @return
	 */
	public static Integer parseIntegerDefaultZero(String str) {
		logger.info("str==" + str);
		Integer integer = null;
		if (isInteger(str)) {
			integer = Integer.valueOf(str);
		}
		if (integer == null) {
			integer = 0;
		}
		return integer;
	}

	/**
	 * 将字符串转成长整型
	 * 
	 * @param str
	 * @return
	 */
	public static Long parseLong(String str) {
		logger.info("str==" + str);
		Long lng = null;
		if (isInteger(str)) {
			lng = Long.valueOf(str);
		}
		return lng;
	}

	/**
	 * 将字符串转成长整型 ,默认为0
	 * 
	 * @param str
	 * @return
	 */
	public static Long parseLongDefaultZero(String str) {
		logger.info("str==" + str);
		Long lng = null;
		if (isInteger(str)) {
			lng = Long.valueOf(str);

		}
		if (lng == null) {
			lng = 0l;
		}
		return lng;
	}

	/**
	 * 将字符串转成Double型
	 * 
	 * @param str
	 * @return
	 */
	public static Double parseDouble(String str) {
		logger.info("str==" + str);
		Double d = null;
		if (isNumber(str)) {
			d = Double.valueOf(str);
		}
		return d;

	}

	/**
	 * 将字符串转成Double型,如果不正确则默认0
	 * 
	 * @param str
	 * @return
	 */
	public static Double parseDoubleDefaultZero(String str) {
		logger.info("str==" + str);
		Double d = null;
		if (isNumber(str)) {
			d = Double.valueOf(str);
		}
		if (d == null) {
			d = 0d;
		}
		return d;
	}

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param value1
	 *            被加数
	 * @param value2
	 *            加数
	 * @return 两个参数的和
	 */
	public static Double add(Number value1, Number value2) {
		BigDecimal b1 = new BigDecimal(Double.toString(value1.doubleValue()));
		BigDecimal b2 = new BigDecimal(Double.toString(value2.doubleValue()));
		return b1.add(b2).doubleValue();
	}

	/**
	 * 提供精确的减法运算。
	 * 
	 * @param value1
	 *            被减数
	 * @param value2
	 *            减数
	 * @return 两个参数的差
	 */
	public static double sub(Number value1, Number value2) {
		BigDecimal b1 = new BigDecimal(Double.toString(value1.doubleValue()));
		BigDecimal b2 = new BigDecimal(Double.toString(value2.doubleValue()));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param value1
	 *            被乘数
	 * @param value2
	 *            乘数
	 * @return 两个参数的积
	 */
	public static Double mul(Number value1, Number value2) {
		BigDecimal b1 = new BigDecimal(Double.toString(value1.doubleValue()));
		BigDecimal b2 = new BigDecimal(Double.toString(value2.doubleValue()));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时， 精确到小数点以后2位，以后的数字四舍五入。
	 * 
	 * @param dividend
	 *            被除数
	 * @param divisor
	 *            除数
	 * @return 两个参数的商
	 */
	public static Double div(Double dividend, Double divisor) {
		return div(dividend, divisor, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。 当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入。
	 * 
	 * @param dividend
	 *            被除数
	 * @param divisor
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static Double div(Double dividend, Double divisor, Integer scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(dividend));
		BigDecimal b2 = new BigDecimal(Double.toString(divisor));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

}
