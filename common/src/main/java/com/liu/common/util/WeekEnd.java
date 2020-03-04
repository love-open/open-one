package com.liu.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class WeekEnd {

	//判断某个日期是否周六周日
	public static boolean WeekendMethod(String date) {
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String Date = date;  //传入的date格式是yyyy-MM-dd
		Date bdate=null;
		try {
			bdate = format1.parse(Date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(bdate);
	    if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
		  //System.out.println("是周末");
			return true;
		}
		else
			//System.out.println("不是周末！");
			return false;
	}

	//获取所有的周末
	/**
	 *功能描述
	 * @author Frank.liu
	 * @date 2019/12/27
	 * @param year
	 * @param vacationMode single, double
	 * @return
	 */
	public static List<String> getAllWeekEndDays(int year, String vacationMode) {
			List<String> dateList = new ArrayList<>();
			SimpleDateFormat simdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = new GregorianCalendar(year, 0, 1);
			int i = 1;
			while (calendar.get(Calendar.YEAR) < year + 1) {
				calendar.set(Calendar.WEEK_OF_YEAR, i++);
				calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
				if (calendar.get(Calendar.YEAR) == year) {
					System.out.println("周日：" + simdf.format(calendar.getTime()));
					dateList.add(simdf.format(calendar.getTime()));
				}
				calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
				if (calendar.get(Calendar.YEAR) == year) {
					if("double".equals(vacationMode)){
						System.out.println("周六：" + simdf.format(calendar.getTime()));
						dateList.add(simdf.format(calendar.getTime()));
					}
				}
			}
			System.out.println(dateList.size());
			return dateList;
	}

	public static List<String> getHolidays (){
		// 法律规定的放假日期
		List<String> lawHolidays   = Arrays.asList("2017-12-30", "2017-12-31",
				"2018-01-01", "2018-02-15", "2018-02-16", "2018-02-17", "2018-02-18",
				"2018-02-19", "2018-02-20", "2018-02-21", "2018-04-05", "2018-04-06",
				"2018-04-07", "2018-04-29", "2018-04-30", "2018-05-01", "2018-06-16",
				"2018-06-17", "2018-06-18", "2018-09-22", "2018-09-23", "2018-09-24",
				"2018-10-01", "2018-10-02", "2018-10-03", "2018-10-04", "2018-10-05",
				"2018-10-06", "2018-10-07");
		return lawHolidays;
	}

	public static void main(String[] args) {
//		String date = "2019-12-21";//周六
//		boolean flag = WeekendMethod(date);
//		if(flag==true) {
//			System.out.println("该日是周末！");
//		}else {
//			System.out.println("该日不是周末！");
//		}
		getAllWeekEndDays(2020, "single");
	}
}
