package com.vnpt.media.rims.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {
    /**
     * purpose  : chuyen kieu string -> kieu date
     * in       :
     * - chuoi can chuyen
     * - dinh dang format cua chuoi
     * out      :        yyyyMMdd,yyyyMMddHH,dd/MM/yyyy HH:mm:ss,dd/MM/yyyy,yyyy/MM/dd HH:mm:ss
     * - kieu date tuong ung. trong truong hop loi tra ve null
     */

    public static Date convertStringToTime(String date, String strPattern) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(strPattern);
            return dateFormat.parse(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static String convertDateString(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        if (date == null) {
            return "";
        }
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            return "";
        }
    }

    //so sanh ngay neu bang nhau tra ve true, khac tra ve false -- khong tinh thoi gian
    public static Boolean compareDate(Date date1, Date date2) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if (date1 == null || date2 == null) {
            return false;
        }
        try {
            if (dateFormat.format(date1).equals(dateFormat.format(date2))) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    /*
     *  @author: dungnt
     *  @todo: get sysdate
     *  @return: String sysdate
     */
    public static String getSysdate(String pattern) throws Exception {
        Calendar calendar = Calendar.getInstance();
        return convertDateString(calendar.getTime(), pattern);
    }

    /**
     * @author: ThangPV
     * @todo: convert from java.util.Date to java.sql.Date
     */
    public static java.sql.Date convertToSqlDate(Date utilDate) {
        return new java.sql.Date(utilDate.getTime());
    }

    /**
     * @param
     * @return
     * @anhlt - Get the first day on month selected.
     */
    public static String parseDate(int monthInput) {
        String dateReturn = "01/01/";
        Calendar cal = Calendar.getInstance();
        switch (monthInput) {
            case 1:
                dateReturn = "01/01/";
                break;
            case 2:
                dateReturn = "01/02/";
                break;
            case 3:
                dateReturn = "01/03/";
                break;
            case 4:
                dateReturn = "01/04/";
                break;
            case 5:
                dateReturn = "01/05/";
                break;
            case 6:
                dateReturn = "01/06/";
                break;
            case 7:
                dateReturn = "01/07/";
                break;
            case 8:
                dateReturn = "01/08/";
                break;
            case 9:
                dateReturn = "01/09/";
                break;
            case 10:
                dateReturn = "01/10/";
                break;
            case 11:
                dateReturn = "01/11/";
                break;
            case 12:
                dateReturn = "01/12/";
                break;
        }
        return dateReturn + cal.get(Calendar.YEAR);
    }

    /*
     *  @author: TUNGTV
     *  @todo: get End Of Day
     *  @return: Date
     */
    public static Date getEndOfDay(Date date) throws Exception {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        String strToDate = DateTimeUtils.convertDateString(date, pattern);
        String strTDate = strToDate.substring(0, 10) + " 23:59:59";
        Date tDate = DateTimeUtils.convertStringToTime(strTDate, pattern);
        return tDate;
    }

    /*
     *  @author: TUNGTV
     *  @todo: get Start Of Day
     *  @return: Date
     */
    public static Date getStartOfDay(Date date) throws Exception {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        String strToDate = DateTimeUtils.convertDateString(date, pattern);
        String strTDate = strToDate.substring(0, 10) + " 00:00:00";
        Date tDate = DateTimeUtils.convertStringToTime(strTDate, pattern);
        return tDate;
    }

    /**
     * author tamdt1, 18/09/2009
     * ham cong them ngay
     * dau vao:
     * - ngay can cong
     * - so luong ngay can cong
     * dau ra:
     * - ngay moi sau khi duoc cong them so ngay
     */
    public static Date addDate(Date date, int numberAddedDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, numberAddedDate);
        Date result = calendar.getTime();
        return result;
    }

    public static int distanceBeetwen2Date(Date date1, Date date2) {
        Long time1 = date1.getTime();
        Long time2 = date2.getTime();
        Long distance = time2 - time1;
        Long dayDistance = distance / (24 * 60 * 60 * 1000);
        return dayDistance.intValue();
    }

    /**
     * author tamdt1, 18/09/2009
     * ham cong them ngay
     * dau vao:
     * - ngay can cong
     * - so luong ngay can cong
     * dau ra:
     * - ngay moi sau khi duoc cong them so ngay
     */
    public static Date addMonth(Date date, int numberAddedDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, numberAddedDate);
        Date result = calendar.getTime();
        return result;
    }

    /**
     * author tamdt1, 18/09/2009
     * ham cong them gio
     * dau vao:
     * - ngay can cong
     * - so luong gio can cong
     * dau ra:
     * - ngay moi sau khi duoc cong them so gio
     */
    public static Date addHour(Date date, int numberAddedTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, numberAddedTime);
        Date result = calendar.getTime();
        return result;
    }

    //cong them phut
    public static Date addMINUTE(Date date, int numberAddedDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, numberAddedDate);
        Date result = calendar.getTime();
        return result;
    }

    /**
     * author tamdt1, 18/09/2009
     * ham lay ngay cuoi cung cua thang
     * dau vao:
     * - ngay can lay ngay cuoi cung
     * dau ra:
     * - ngay cuoi cung cua thang cung thang voi ngay dau vao
     */
    public static int getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int lastDay = calendar.getActualMaximum(Calendar.DATE);
        return lastDay;


    }

    /**
     * author tamdt1, 18/09/2009
     * ham lay ngay cuoi cung cua thang
     * dau vao:
     * - ngay can lay ngay cuoi cung
     * dau ra:
     * - ngay cuoi cung cua thang cung thang voi ngay dau vao
     */
    public static Date getLastDateOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int lastDay = calendar.getActualMaximum(Calendar.DATE);
        calendar.set(Calendar.DATE, lastDay);
        Date lastDate = calendar.getTime();
        return lastDate;


    }

    /**
     * author   : tamdt1, 13/11/2009
     * purpose  : ham lay ngay, gio he thong
     * dau ra   :
     * - ngay gio hien tai
     */
    public static Date getSysDate() {
        Date now = new Date();
        return now;
    }
}
