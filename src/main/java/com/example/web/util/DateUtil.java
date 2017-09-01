package com.example.web.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;


public class DateUtil {
	
	public static final long BJ_TIME_OFFSET = 28800000l; 
	
	public static void main(String[] args) {
	
	}
	/**
	 * @Title: isTime 
	 * @Description: 判断参数格式是否是时间格式   HH:mm:ss
	 * @param  参数描述
	 * @return boolean 返回类型 
	 * @throws
	 */
	public static boolean isTime(String str){
		boolean flag = false;
		if (!StringUtil.isEmpty(str)) {
			Pattern pattern = Pattern.compile("(([0-1]?\\d)|(2[0-3]))(:([0-5]?\\d)){2}");
			flag = pattern.matcher(str).matches();
			pattern = null;
		}
		return flag;
	}
	
	/**
	 * @Title: scoreTime   
	 * @Description: 计算两个时间的分钟差 
	 * @param startTime
	 * @param endTime
	 * @return int 返回类型 
	 * @throws
	 */
	public static int scoreTime(Date startTime,Date endTime){
		
		Calendar c0 = Calendar.getInstance();
		c0.setTime(startTime);
		Calendar c1 = Calendar.getInstance();
		c1.setTime(endTime);
		
		long seconds = (c1.getTimeInMillis() - c0.getTimeInMillis())/(1000);
		int durationMinutes  = (int) (seconds / 60);
		if(seconds % 60 != 0){
			durationMinutes++;
		}
		return durationMinutes;
	}
	
	/**
	 * @Title: getDateOffset   
	 * @Description: 获得本地时区于GMT时区的 毫秒差 
	 * @return long 返回类型 
	 * @throws
	 */
	public static long getDateOffset() {
		long offset = 0;
		Calendar calendar = Calendar.getInstance();
		offset = calendar.getTimeZone().getOffset(calendar.getTime().getTime());
		calendar = null;
		return offset;
	}
	
	
	/**
	 * 获取GMT时间
	 * @param date:如果Date 为空，则取当前时间的GMT时间
	 * @return
	 */
	public static Date getGmtDate(Date date){
		Date gmtDate=null;
		if(date==null){
			date=new Date();
		}
		long offset=0;
		Calendar calendar=Calendar.getInstance();
		offset=calendar.getTimeZone().getOffset(calendar.getTime().getTime());
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, (int)(-1*offset/(1000*3600)));
		gmtDate=calendar.getTime();
		calendar=null;
		return gmtDate;
	}
	
	/**
	 * 根据当前时间与TimeZone取GMT时间
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getGmtDateByTimeZone(Date date,Integer offset){
		Date gmtDate=null;
		if(date==null){
			date=new Date();
		}
		if( offset==null){
			offset=0;
		}
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, (int)(-1*offset/(1000*3600)));
		gmtDate=calendar.getTime();
		calendar=null;
		return gmtDate;
	}
	
	/**
	 * 时间增加多少分钟
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date addDateMinutes(Date date,Integer minute){
		return addDateField(date, Calendar.MINUTE, minute);
	}
	
	/**
	 * 时间增加多少秒
	 * second 可以为负数，相当于减了多少秒
	 * 2013-6-4
	 */
	public static Date addDateSecond(Date date, Integer second){
		return addDateField(date, Calendar.SECOND, second);
	}
	
	private static Date addDateField(Date date, int calendarField, Integer fieldValue){
		if(date==null){
			date=new Date();
		}
		if( fieldValue==null){
			fieldValue=0;
		}
		Date retDate=null;
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendarField, fieldValue);
		retDate=calendar.getTime();
		calendar=null;
		return retDate;
	}
	
	/**
	 * GMT时间转换为北京时间
	 * @param date:如果Date 为空，则取当前时间的GMT时间
	 * @return
	 */
	public static Date getBejingDateByGmtDate(Date gmtDate){
		Date bjDate=null;
		if(gmtDate==null){
			gmtDate=new Date();
		}
		long offset = 28800000;    //北京时区
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(gmtDate);
		calendar.add(Calendar.HOUR, (int)(offset/(1000*3600)));
		bjDate=calendar.getTime();
		calendar=null;
		return bjDate;
	}

	/**
	 * 以当前时间为准，取多少小时    前    的GMT时间
	 * @param hour
	 * @return
	 */
	public static Date getGmtDateByBeforeMinute(Integer minute){
		Date gmtDate=null;
		long offset=0;
		Calendar calendar=Calendar.getInstance();
		offset=calendar.getTimeZone().getOffset(calendar.getTime().getTime());
		calendar.add(Calendar.HOUR, (int)(-1 * offset/(1000*3600)));
		calendar.add(Calendar.MINUTE, -1 * minute );
		gmtDate=calendar.getTime();
		calendar=null;
		return gmtDate;
	}
	
	/**
	 * 以当前时间为准，取多少小时    前    的GMT时间
	 * @param hour
	 * @return
	 */
	public static Date getGmtDateByBeforeHour(Integer hour){
		Date gmtDate=null;
		long offset=0;
		Calendar calendar=Calendar.getInstance();
		offset=calendar.getTimeZone().getOffset(calendar.getTime().getTime());
		calendar.add(Calendar.HOUR, (int)(-1*offset/(1000*3600)));
		calendar.add(Calendar.HOUR, -1*hour );
		gmtDate=calendar.getTime();
		calendar=null;
		return gmtDate;
	}
	
	/**
	 *以当前时间为准， 取多少小时    后      的GMT时间
	 * @param hour
	 * @return
	 */
	public static Date getGmtDateByAfterHour(Integer hour){
		Date gmtDate=null;
		long offset=0;
		Calendar calendar=Calendar.getInstance();
		offset=calendar.getTimeZone().getOffset(calendar.getTime().getTime());
		calendar.add(Calendar.HOUR, (int)(-1*offset/(1000*3600)));
		calendar.add(Calendar.HOUR, hour );
		gmtDate=calendar.getTime();
		calendar=null;
		return gmtDate;
	}
	
	/**
	 *以当前时间为准， 取多少ms 后的GMT时间
	 * @param ms
	 * @return
	 */
	public static Date getGmtDateByAfterMs(long ms){
		Date gmtDate=null;
		long offset=0;
		Calendar calendar=Calendar.getInstance();
		offset=calendar.getTimeZone().getOffset(calendar.getTime().getTime());
		calendar.add(Calendar.HOUR, (int)(-1*offset/(1000*3600)));
		calendar.add(Calendar.MILLISECOND, (int)ms );
		gmtDate=calendar.getTime();
		calendar=null;
		return gmtDate;
	}
	
	

	/**
	 * 格式 化日期，返回符合格式的字符串
	 * @param date
	 * @param formater   如:yyyy-MM-dd HH:mm:ss
	 * @return
	 * 
	 */
	public static String getDateStrCompact(Date date, String formater) {
		if (date == null){
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat(formater);
		String str = format.format(date);
		return str;
	}

	public static String dateToString(Date date,String formatStr) {
		if(formatStr==null || "".equals(formatStr.trim())){
			formatStr="yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat formatDate = new SimpleDateFormat(formatStr);
		String datestr = formatDate.format(date);
		return datestr;
	}
	
	/**
	 * 格式化日期字符串，返回符合格式的date
	 * @param dateStr
	 * @param formater   如:yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date StringToDate(String dateStr, String formater){
		Date date = null;
		if(formater==null || "".equals(formater.trim())){
			formater = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat format = new SimpleDateFormat(formater);
		if(!StringUtil.isNotBlank(dateStr)){
			return date;
		}
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	
	public static long dateDiff(Date date1,Date date2){
		long diff=0;
		if(date1.before(date2)){
			Calendar calendar1=Calendar.getInstance();
			calendar1.setTime(date1);
			Calendar calendar2=Calendar.getInstance();
			calendar2.setTime(date2);
			long diffMillSeconds=calendar2.getTimeInMillis()-calendar1.getTimeInMillis();
			if(diffMillSeconds>0){
				diff=(diffMillSeconds/1000)/60;
			}
			calendar2=null;
			calendar1=null;
		}
		
		return diff;
	}
	

	public static long diffMinute(Date date1,Date date2){
		long diff=0;
		if(date1.before(date2)){
			Calendar calendar1=Calendar.getInstance();
			calendar1.setTime(date1);
			Calendar calendar2=Calendar.getInstance();
			calendar2.setTime(date2);
			long diffMillSeconds=calendar2.getTimeInMillis()-calendar1.getTimeInMillis();
			if(diffMillSeconds>0){
				diff=(diffMillSeconds/1000)/60;
			}
			calendar2=null;
			calendar1=null;
		}
		
		return diff;
	}

	public static long diffSecond(Date date1,Date date2){
		long diff=0;
		if(date1.before(date2)){
			Calendar calendar1=Calendar.getInstance();
			calendar1.setTime(date1);
			Calendar calendar2=Calendar.getInstance();
			calendar2.setTime(date2);
			long diffMillSeconds=calendar2.getTimeInMillis()-calendar1.getTimeInMillis();
			if(diffMillSeconds>0){
				diff=(diffMillSeconds/1000);
			}
			calendar2=null;
			calendar1=null;
		}
		
		return diff;
	}
	
	/**
	 * 根据GMT时间与时区生成当前时间
	 * @param gmtDate
	 * @param offset
	 * @return
	 */
	public static Date getOffsetDateByGmtDate(Date gmtDate,Long offset){
		Date offsetDate=null;
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(gmtDate);
		calendar.add(Calendar.HOUR, (int) (offset/(1000*3600)));
		offsetDate=calendar.getTime();
		calendar=null;
		return offsetDate;
	}

	/**
	 * 将日期增减 n 天
	 * @param date  参照日期,如果为null则取当前日期
	 * @param days  增减的天数 (为正数则增加，为负数则减少)
	 * @return Date
	 */
	public static Date addDate(Date date, int days) {
		return addDate(date, days, Calendar.DATE);
	}
	
	/**
	 * 取某一日期增减 n 值后的日期, n 由 dateField 决定是年、月、日 根据增加or减少的时间得到新的日期
	 * @param date 参照日期
	 * @param counts 增减的数值
	 * @param dateField
	 *            int 需操作的日期字段, 取值范围如下 Calendar.YEAY 年 Calendar.MONTH 月
	 *            Calendar.DATE 日 .... Calendar.SECOND 秒
	 * @return Date
	 */
	private static Date addDate(Date date, int counts, int dateField) {
		GregorianCalendar curGc = new GregorianCalendar();
		if (date != null)
			curGc.setTime(date);
		curGc.add(dateField, counts);
		return curGc.getTime();
	}
 
	   
	/**
	 * 获得月份中最大的天数
	 * @param date
	 * @return
	 */
	public static Integer getMaxDayFromDate(Date date){
		Integer maxDay=0;
		if(date==null){
			return maxDay;
		}
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		if(calendar!=null){
			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1,1);
			calendar.setTimeInMillis(calendar.getTimeInMillis()-(24*3600*1000));
			maxDay=calendar.get(Calendar.DAY_OF_MONTH);
		}
		calendar=null;
		return maxDay;
	}
	
	/**
	 * 获取给定日期所在月份的第几个周几的日期
	 * @param beginDate      日期
	 * @param sequence       第几个
	 * @param weekValue      周几
	 */
	private static Date getDaysByDate(Date beginDate,int sequenceValue, int weekValue){
		Calendar beginCalendar = Calendar.getInstance();
		beginCalendar.setTime(beginDate);
		int dateInWhichMonth = beginCalendar.get(Calendar.MONTH);   //beginDate所在月份,java月份是从0-11,月份设置时要减1    1:2月份;2:3月份;3:4月份;4:5月份,以此类推
		int dateInWhichYear = beginCalendar.get(Calendar.YEAR);     //beginDate所在年 
		//得到beginDate所在月份的第1日的日期 
		Calendar firstDayCalendar = new GregorianCalendar(dateInWhichYear, dateInWhichMonth, 1,beginCalendar.get(Calendar.HOUR_OF_DAY),beginCalendar.get(Calendar.MINUTE),beginCalendar.get(Calendar.SECOND)); 
		Calendar firstSeqCalendar = null;     //第一个周几
		//从该月1日开始遍历找到第一个指定“星期几”
		int dayInWeek = 0;     //dayInWeek:     1:周日；2：周一；3：周二；4：周三；5：周四；6：周五；7：周六
		for(int i=0;i<7;i++){
			Calendar eachDayCalendar = firstDayCalendar;
			dayInWeek = eachDayCalendar.get(Calendar.DAY_OF_WEEK);   //当天是一周中的第几天
			if(dayInWeek == weekValue){
				firstSeqCalendar = eachDayCalendar;
				break;
			}
			eachDayCalendar.add(Calendar.DAY_OF_MONTH, +1);    //取当前日期的后一天
		}
		if(sequenceValue >= 1){
			//sequenceValue如果为3，即第三个周几，那么就是第一个周几+（3-1）*7,即加上14天
			firstSeqCalendar.add(Calendar.DAY_OF_MONTH, +((sequenceValue - 1) * 7));
			if(dateInWhichMonth == firstSeqCalendar.get(Calendar.MONTH)){
				return firstSeqCalendar.getTime();
			}
		}
		return null;
	}
	
	/**
	 * 获取每月第几天
	 * 每月第几天 ：一个单独的数值数据     如：5,7,30或者  30
	 */
	private static List<Date> getSequenceDay(Date beginDate, Date endDate, String dayValue, int cycleDateLimit){
		int day = IntegerUtil.parseIntegerWithDefaultZero(dayValue); 
		List<Date> dailyList = new ArrayList<Date>();
		List<Date> dateList = new ArrayList<Date>();
		Calendar eachCalendar = Calendar.getInstance();
		eachCalendar.setTime(beginDate);
		
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDate);
		
		eachCalendar.set(Calendar.DATE, 1);
		while(!eachCalendar.after(endCalendar)){
			dateList.add(eachCalendar.getTime());
			eachCalendar.add(Calendar.MONTH, +1);
		}
		
		int dateSize = dateList.size();
		for(int i=0;i<dateSize;i++){
			if(dailyList.size() == cycleDateLimit){   //最多获取30个有效日期
				break;
			}
			eachCalendar.setTime(dateList.get(i));
			int dateInWhichMonth = eachCalendar.get(Calendar.MONTH);   //beginDate所在月份,java月份是从0-11,月份设置时要减1    1:2月份;2:3月份;3:4月份;4:5月份,以此类推
			int dateInWhichYear = eachCalendar.get(Calendar.YEAR);     //beginDate所在年 
			//得到date所在月份的第1天的日期 
			Calendar firstDayCalendar = new GregorianCalendar(dateInWhichYear, dateInWhichMonth, 1,eachCalendar.get(Calendar.HOUR_OF_DAY),eachCalendar.get(Calendar.MINUTE),eachCalendar.get(Calendar.SECOND));
			//取月份要加1.判断当前月份的最大天数: 
			int maxDay = firstDayCalendar.getActualMaximum(Calendar.DAY_OF_MONTH); 
			if(maxDay >= (day-1)){      //每月第几天必须小于等于当月最大天数
				firstDayCalendar.add(Calendar.DAY_OF_MONTH, (day-1)<0 ? 0 : (day-1));
				if(firstDayCalendar.getTime().before(beginDate)){
					continue;
				}
				
				if(i == (dateSize-1)){
					if(firstDayCalendar.getTime().compareTo(endDate) <= 0){
						dailyList.add(firstDayCalendar.getTime());
						break;
					}
				}else{
					dailyList.add(firstDayCalendar.getTime());
				}
			}
		}
		return dailyList;
	}
	
 
	/**
	 * @param offsetmill 时区值
	 * @return
	 */
	public static Date getTodayStartDate(long offsetmill){
		Date now = getGmtDate(null);
		Date localDate = getOffsetDateByGmtDate(now, offsetmill);//获取此时的当地时间
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			localDate = sdfp.parse(sdf.format(localDate)+" 00:00:00");//当地“今天” 0点0分0秒时间点
			
			return getGmtDateByTimeZone(localDate, new Integer((int)offsetmill)); //获取当地 0点0分0秒所对应的GMT时间
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取一周的结束日期
	 * @param offsetmill
	 * @return
	 */
	public static Date getWeekEndDate(Date weekstart){
		long timemill = weekstart.getTime()+7*24*3600000l;
		return new Date(timemill);
	}
	
	/**
	 * @Title: getMonthStartDate   
	 * @Description: 获得这个月的开始日期 
	 * @param offsetmill
	 * @return Date 返回类型 
	 * @throws
	 */
	public static Date getMonthStartDate(long offsetmill){
		//获取localtime
		Date date = getOffsetDateByGmtDate(getGmtDate(null),offsetmill);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);	
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.DAY_OF_MONTH,1);
		return getGmtDateByTimeZone(calendar.getTime(), (int)offsetmill);
		
	}
	
	/**
	 * @Title: getMonthEndDate   
	 * @Description: 获得这个月的结束日期
	 * @param monthstart
	 * @return Date 返回类型 
	 * @throws
	 */
	public static Date getMonthEndDate(Date monthstart){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(monthstart);
		calendar.add(Calendar.MONTH, 1);
		int mon = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		if(mon==1 || mon ==3 || mon ==5 || mon==7 ||mon ==8 ||mon ==10 || mon ==12){
			if(day>27){
				calendar.set(Calendar.DAY_OF_MONTH, 31);
			}
		}
		return calendar.getTime();
	}
	
	
	public static int getDateDiff(Date date1,Date date2){
		long diffDays=0;
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date1);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MILLISECOND,0);
		long time1 = calendar.getTimeInMillis();
		calendar.setTime(date2);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MILLISECOND,0);
		long time2 = calendar.getTimeInMillis();
		diffDays = (time2 - time1) / (1000 * 3600 * 24);
		calendar.clear();
		calendar=null;
		return Integer.parseInt(String.valueOf(diffDays));            

	}

	/**
	 * 验证是否日期
	 * @param str 格式 ："yyyy-MM-dd"  或者  “yyyy-MM-dd hh:mm:ss" 或者  “yyyy-MM-dd h:m:s" 
	 * @return
	 */
	public static boolean isDate(String str) {
		boolean flag = false;
		if (!StringUtil.isEmpty(str)) {
			Pattern pattern = Pattern.compile("((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-" +
					"(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-" +
					"(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|" +
					"(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|" +
					"((16|[2468][048]|[3579][26])00))-0?2-29-))" +
					"( (([0-1]?\\d)|(2[0-3]))(:([0-5]?\\d)){2})?");
			flag = pattern.matcher(str).matches();
			pattern = null;

		}
		return flag;
	}
	
	/**
	 * 将日期格式的字符转转成日期类型
	  * @param str 格式 ："yyyy-MM-dd"  或者  “yyyy-MM-dd hh:mm:ss" 或者  “yyyy-MM-dd h:m:s" 
	 * @return
	 */
	public static Date parseDate(String str){
		Date date=null;
		if(isDate(str)){
			String[] tmpArray=str.split("[-\\s:]");
			if(tmpArray!=null && (tmpArray.length==3 ||tmpArray.length==6)){
				Calendar calendar=Calendar.getInstance();
				calendar.clear();
				calendar.set(Calendar.YEAR, NumberUtil.parseInteger(tmpArray[0]));
				calendar.set(Calendar.MONTH, NumberUtil.parseInteger(tmpArray[1])-1);
				calendar.set(Calendar.DAY_OF_MONTH, NumberUtil.parseInteger(tmpArray[2]));
				if(tmpArray.length==6){
					calendar.set(Calendar.HOUR, NumberUtil.parseInteger(tmpArray[3]));
					calendar.set(Calendar.MINUTE, NumberUtil.parseInteger(tmpArray[4]));
					calendar.set(Calendar.SECOND, NumberUtil.parseInteger(tmpArray[5]));
				}
				date=calendar.getTime();
				calendar=null;
			}
			tmpArray=null;
		}
		return date;
	}


    /**
     * @Title: format   
     * @Description: 格式化日期，如果没有指定格式化规则，默认使用  yyyy-MM-dd HH:mm:ss
     * @param date
     * @param formatStr
     * @return String 返回类型 
     * @throws
     */
	public static String format(Date date, String formatStr) {
		if (date == null) {
			return null;
		}
		if (formatStr == null || "".equals(formatStr.trim())) {
			formatStr = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		String dateStr = format.format(date);
		format = null;
		return dateStr;
	}
	
	
	public static int getBeijingBillCurrentYear(){
		Date beijingDate = getBejingDateByGmtDate(getGmtDate(new Date()));
		Calendar c = Calendar.getInstance();
		c.setTime(beijingDate);
		int year = c.get(Calendar.YEAR);
//		if(c.get(Calendar.MONTH) == 0) year--;
		return year;
	}
	
	public static int getBeijingBillCurrentMonth(){
		Date beijingDate = getBejingDateByGmtDate(getGmtDate(new Date()));
		Calendar c = Calendar.getInstance();
		c.setTime(beijingDate);
		int month = c.get(Calendar.MONTH)+1;
		if(month == 1) month = 12;
		return month;
	}
	
	
	public static Date getTadayGMTDate(long timeoffset){

		Date localDate = getOffsetDateByGmtDate(DateUtil.getGmtDate(null), timeoffset);
		
		Calendar c = Calendar.getInstance();
		c.setTime(localDate);
		c.set(Calendar.MILLISECOND, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.HOUR_OF_DAY, 0);
		
		int offset = Long.valueOf(timeoffset).intValue();
		Date localGmtDate = getGmtDateByTimeZone(c.getTime(), offset);
		
		return localGmtDate;
	}
	
	/**
	 * 根据指定的一个时间 获取这个时间7天前的日期
	 * @param startDate
	 * @return
	 */
	public static Date getRecentWeekEndDate(long timeoffset){
		Date startDate = getTadayGMTDate(timeoffset);
		Date endDate = new Date(startDate.getTime()- 7*24*3600*1000l);
		return endDate;
	}
	
	
	public static Date getRecentMonthEndDate(long timeoffset){
		Date startDate = getTadayGMTDate(timeoffset);
		return new Date(startDate.getTime()- 30*24*3600*1000l);
	}
	
	/**
	 * @Title: getCurrentTimeInMillis   
	 * @Description: 获得当前时间的时间戳
	 * @return long 返回类型 
	 * @throws
	 */
	public static long getCurrentTimeInMillis(){
		Calendar cal = Calendar.getInstance();
		return cal.getTimeInMillis();
	}
	
	/**
	 * 获取上一个月的日期
	 * @param date
	 * @return
	 */
	public static Date getPreMonth(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int mon = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		int maxday = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		if(mon == 0){
			c.add(Calendar.YEAR, -1);
			c.set(Calendar.MONTH, Calendar.DECEMBER);
		}else if(mon == 1 && day == maxday){
			c.add(Calendar.MONTH, -1);
			int cuurentmaxday = c.getActualMaximum(Calendar.DAY_OF_MONTH);
			c.set(Calendar.DAY_OF_MONTH, cuurentmaxday);
		}else{
			c.add(Calendar.MONTH, -1);
		}
		return c.getTime();
	}
	
	/**
	 * 获取当前月份的最后一天
	 * @param date
	 * @return
	 */
	public static Date getCurrentMonthEnd(Date date){
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int maxday = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		c.set(Calendar.DAY_OF_MONTH, maxday);
		return c.getTime();
	}
	
	public static Date getMonthStartTime(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	public static Date getMonthEndTime(Date date){
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int maxday = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		c.set(Calendar.DAY_OF_MONTH, maxday);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	/**
	 * 获得上个月第一天
	 * @return
	 */
	public static Date getLastMonthFirstDay(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}
	
	/**
	 * 获得某分钟后的时间
	 * @param startDate
	 * @param space
	 * @return
	 */
	public static Date getNextTime(Date startDate, Integer space){
		Calendar now = Calendar.getInstance();   
        now.setTime(startDate);
        now.add(Calendar.MINUTE, space);
        return now.getTime();
	}
	
	/**
	 * 检测是否在工作时间，如果不是则返回 后一天的工作时间, 默认工作时间是  8 - 20 点 
	 * @param startDate
	 * @return
	 */
	public static Date getWorkTime(Date startDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		int hours = calendar.get(Calendar.HOUR);
		if (8 <= hours && hours <= 20) {
			return startDate;
		} else {
			int day = calendar.get(Calendar.DATE);
			calendar.set(Calendar.DATE, day + 1);
			calendar.set(Calendar.HOUR, 8);
			calendar.set(Calendar.MINUTE, 0);
			return calendar.getTime();
		}

	}
	
	/**
	 * @Title: getDifference   
	 * @Description: 获得时间差
	 * @param startTime
	 * @param endTime
	 * @return long 返回类型 
	 * @throws
	 */
	public static long getDifference(Date startTime, Date endTime) {
		long total_minute = (long) ((endTime.getTime() - startTime.getTime()) / (1000 * 60));
		return total_minute;
	}
	
	
	/**
	 * 检测是否在工作时间
	 * @param startDate
	 * @return
	 */
	public static boolean checkIsWorkTime(Date startDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		int hours = calendar.get(Calendar.HOUR);

		if (8 <= hours && hours <= 20) {
			return true;
		}

		return false;
	}
	
	/**
	 * 检查传入时间戳是否与服务器当前时间是否间隔不超过两天
	 * @param timeStamp
	 * @return
	 * @author binson
	 * @2017-7-3
	 */
	public static boolean isLimitTime(long timeStamp ){
		 boolean bool = false;
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		 String dateparm= sdf.format(new Date(timeStamp));
		 String datenow=sdf.format(new Date());
		 if(Math.abs(Integer.parseInt(datenow)-Integer.parseInt(dateparm))<2){
			 bool=true;
		 }
		 return bool;
	}
	
	/**
	 * @Title: getWeekDayiByDate   
	 * @Description: 根据时间获得中国格式的星期 
	 * @param date
	 * @return String 返回类型 
	 * @throws
	 */
    public static String getWeekDayiByDate(Date date){
    	
    	String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if(week_index<0){
			week_index = 0;
		} 
		return weeks[week_index];
    	
    }
 
	
}
