package com.wangsanshi.gank.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CharacterUtil {
    /*
     * 将unicode编码转化为utf-8
     */
    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }

                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }

    /*
     * 计算发布时间距离当前时间的长度
     */
    public static String timeFormat(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss.SSS");
        Calendar calendar = Calendar.getInstance();

        Date publishDate = null;
        try {
            publishDate = sdf.parse(date.replace("T", "-"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date currentDate = new Date();

        calendar.setTime(publishDate);
        int publishYear = calendar.get(Calendar.YEAR);
        int publishMonth = calendar.get(Calendar.MONTH) + 1;
        int publishDay = calendar.get(Calendar.DAY_OF_MONTH);
        int publishHour = calendar.get(Calendar.HOUR_OF_DAY);
        int publishMinute = calendar.get(Calendar.MINUTE);

        calendar.setTime(currentDate);
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);

        if (publishYear != currentYear) {
            return (currentYear - publishYear) + "年前";
        }

        if (publishMonth != currentMonth) {
            return (currentMonth - publishMonth) + "个月前";
        }

        if (publishDay != currentDay) {
            return (currentDay - publishDay) + "天前";
        }

        if (publishHour != currentHour) {
            return (currentHour - publishHour) + "小时前";
        }

        if (publishMinute != currentMinute) {
            return (currentMinute - publishMinute) + "分钟前";
        }

        return "刚刚";
    }
}
