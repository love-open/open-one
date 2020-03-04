package com.liu.common.util.holiday;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     * 获得指定时间格式的Date时间
     * @author wangzhe@piesat.cn
     * @date 2018年7月21日
     * @param time 时间字符串
     * @param plan 时间格式
     * @return 时间
     */
    public static Date getDateByString(String time,String plan){
        Long times = getLongTimeByString(time, plan);
        return  new Date(times);
    }

    /**
     * 获得指定时间格式的时间
     * @author wangzhe@piesat.cn
     * @date 2018年7月21日
     * @param time 时间字符串
     * @param plan 时间格式
     * @return 时间
     */
    public static String getStringDateByString(Date time,String plan){
        DateFormat format = new SimpleDateFormat(plan);
        return format.format(time);
    }

    /**
     * 获得指定时间格式的毫秒值
     * @author wangzhe@piesat.cn
     * @date 2018年7月17日
     * @param time 时间字符串
     * @param plan 时间格式
     * @return 毫秒值
     */
    public static Long getLongTimeByString(String time,String plan){
        DateFormat format = new SimpleDateFormat(plan);
        Long result = null;
        try {
            result = format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  result;
    }

}
