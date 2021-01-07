package com.yijie.libraryManagementSystem.tool;

/**
 * @desc    DateTool.java
 * @author  yijie
 * @date    2021-01-07 15:15
 * @note    2021-01-07 15:15 yijie Created DateTool.java file
 */
public class DateTool {
    /**
     * 获取当前年月的天数
     * @param year  当前年份
     * @param month 当前月份
     * @return 获取当前年月的天数
     */
    public static int getDayByYearAndMonth(int year, int month) {
        if(month == 2) {
            if ((year%4==0 && year%100!=0) || (year%100==0 && year%400==0))
                return 29;
            else return 28;
        }
        else if (month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12) {
            return 31;
        }
        return 30;
    }
}
