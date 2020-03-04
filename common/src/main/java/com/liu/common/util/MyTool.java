package com.liu.common.util;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * @author Frank.liu
 * @create 2019-02-22 下午 3:08
 **/
public class MyTool {
    /**
     * 获取UUID
     * @return 32位UUID
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 获取标准时间
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static  String getStandardTime(){
        SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatTime.format(new Date());
    }
    /**
     * 获取时间序列
     * @return Timeformat ("yyyyMMddhhmmssSSS")
     */
    public static  String getTimeSeries(String Timeformat){
        SimpleDateFormat formatTime = new SimpleDateFormat(Timeformat);
        return formatTime.format(new Date());
    }
    /**
     * 删除单个文件
     *
     * @param fileName
     *            要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            return file.delete();
        } else {
            return false;
        }
    }
    
    public static Date dayAddNum(Date time, Integer num){
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Date date = format.parse(time);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.DAY_OF_MONTH, num);
        Date newTime = calendar.getTime();
        return newTime;
    }

    /**
     *功能描述:给定一个带格式的字符串日期，增加指定的天数，再按照同一样的格式字符串日期
     * @author Frank.liu
     * @date 2019/11/6
     * @param date
     * @param num
     * @param formatStr
     * @return
     */
    public static String stringDayAddNum(String date, Integer num, String formatStr) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(formatStr);
        return df.format(dayAddNum(df.parse(date), num));
    }
    
    /**
	    *  利用反射将子类的值赋值给父类
	 * @author frank
	*/
    public static <T> void fatherToChild(T father,T child) throws Exception {
        if (child.getClass().getSuperclass()!=father.getClass()){
            throw new Exception("child 不是 father 的子类");
        }
        Class<?> fatherClass = father.getClass();
        Field[] declaredFields = fatherClass.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            Field field = declaredFields[i];
            Method method = fatherClass.getDeclaredMethod("get" + upperHeadChar(field.getName()));
            Object obj = method.invoke(father);
            field.setAccessible(true);
            field.set(child, obj);
        }

    }

    /**
              * 首字母大写，in:deleteDate，out:DeleteDate
     * @author frank
     */
    public static String upperHeadChar(String in) {
        String head = in.substring(0, 1);
        String out = head.toUpperCase() + in.substring(1, in.length());
        return out;
    }

    public static void main(String[] args) {
        try {
            System.out.println(stringDayAddNum("2019-11-30", 1, "yyyy-MM-dd"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
