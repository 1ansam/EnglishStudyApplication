package com.night.basecore.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 身份证工具类
 */

public class IdCardUtil {
    private static final Map<Integer, String> ZONE_NUM = new HashMap<>();
    private static final int[] PARITYBIT = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
    private static final int[] POWER_LIST = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    static {
        ZONE_NUM.put(11, "北京");
        ZONE_NUM.put(12, "天津");
        ZONE_NUM.put(13, "河北");
        ZONE_NUM.put(14, "山西");
        ZONE_NUM.put(15, "内蒙古");
        ZONE_NUM.put(21, "辽宁");
        ZONE_NUM.put(22, "吉林");
        ZONE_NUM.put(23, "黑龙江");
        ZONE_NUM.put(31, "上海");
        ZONE_NUM.put(32, "江苏");
        ZONE_NUM.put(33, "浙江");
        ZONE_NUM.put(34, "安徽");
        ZONE_NUM.put(35, "福建");
        ZONE_NUM.put(36, "江西");
        ZONE_NUM.put(37, "山东");
        ZONE_NUM.put(41, "河南");
        ZONE_NUM.put(42, "湖北");
        ZONE_NUM.put(43, "湖南");
        ZONE_NUM.put(44, "广东");
        ZONE_NUM.put(45, "广西");
        ZONE_NUM.put(46, "海南");
        ZONE_NUM.put(50, "重庆");
        ZONE_NUM.put(51, "四川");
        ZONE_NUM.put(52, "贵州");
        ZONE_NUM.put(53, "云南");
        ZONE_NUM.put(54, "西藏");
        ZONE_NUM.put(61, "陕西");
        ZONE_NUM.put(62, "甘肃");
        ZONE_NUM.put(63, "青海");
        ZONE_NUM.put(64, "宁夏");
        ZONE_NUM.put(65, "新疆");
        ZONE_NUM.put(71, "台湾");
        ZONE_NUM.put(81, "香港");
        ZONE_NUM.put(82, "澳门");
        ZONE_NUM.put(91, "外国");
    }

    /**
     * 验证身份证是否合法
     *
     * @param input 号码内容
     * @return 是否有效 null和"" 都是false
     */
    public static boolean validateIDCard(String input) {
        if (input == null || (input.length() != 15 && input.length() != 18))
            return false;
        final char[] cs = input.toUpperCase().toCharArray();
        //校验位数
        int power = 0;
        for (int i = 0; i < cs.length; i++) {
            if (i == cs.length - 1 && cs[i] == 'X')
                break;//最后一位可以 是X或x
            if (cs[i] < '0' || cs[i] > '9')
                return false;
            if (i < cs.length - 1) {
                power += (cs[i] - '0') * POWER_LIST[i];
            }
        }
        //校验区位码
        if (!ZONE_NUM.containsKey(Integer.valueOf(input.substring(0, 2)))) {
            return false;
        }
        //校验年份
        String year = input.length() == 15 ? getIdcardCalendar() + input.substring(6, 8) : input.substring(6, 10);
        final int iyear = Integer.parseInt(year);
        if (iyear < 1900 || iyear > Calendar.getInstance().get(Calendar.YEAR))
            return false;//1900年的PASS，超过今年的PASS
        //校验月份
        String month = input.length() == 15 ? input.substring(8, 10) : input.substring(10, 12);
        final int imonth = Integer.parseInt(month);
        if (imonth < 1 || imonth > 12) {
            return false;
        }
        //校验天数
        String day = input.length() == 15 ? input.substring(10, 12) : input.substring(12, 14);
        final int iday = Integer.parseInt(day);
        if (iday < 1 || iday > 31)
            return false;
        //校验"校验码"
        if (input.length() == 15)
            return true;
        return cs[cs.length - 1] == PARITYBIT[power % 11];
    }

    private static int getIdcardCalendar() {
        GregorianCalendar curDay = new GregorianCalendar();
        int curYear = curDay.get(Calendar.YEAR);
        int year2bit = Integer.parseInt(String.valueOf(curYear).substring(2));
        return year2bit;
    }

    /**
     * 根据身份编号获取年龄
     *
     * @param input
     * @return
     */
    public static int getAgeByIdCard(String input) {
        int iAge;
        String year = input.substring(6, 10);
        Calendar cal = Calendar.getInstance();
        int iCurrYear = cal.get(Calendar.YEAR);
        int iCurrentMonth = cal.get(Calendar.MONTH) + 1;
        iAge = iCurrYear - Integer.valueOf(year) - 1;
        if (getMonthByIdCard(input) < iCurrentMonth || (getMonthByIdCard(input) == iCurrentMonth && getDateByIdCard(input) <= cal.get(Calendar.DATE))) {
            iAge++;
        }
        return iAge;
    }

    /**
     * 根据身份编号获取生日年
     *
     * @param input
     * @return
     */
    public static Short getYearByIdCard(String input) {
        return Short.valueOf(input.substring(6, 10));
    }

    /**
     * 根据身份编号获取生日月
     *
     * @param idCard
     * @return
     */
    public static Short getMonthByIdCard(String idCard) {
        return Short.valueOf(idCard.substring(10, 12));
    }

    /**
     * 根据身份编号获取生日天
     *
     * @param idCard
     * @return
     */
    public static Short getDateByIdCard(String idCard) {
        return Short.valueOf(idCard.substring(12, 14));
    }
}
