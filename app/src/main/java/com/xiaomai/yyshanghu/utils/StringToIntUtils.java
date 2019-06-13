package com.xiaomai.yyshanghu.utils;

import java.text.DecimalFormat;

/**
 * Created by EDZ on 2019/4/28.
 */

public class StringToIntUtils {

    /**
     * 保存后两位
     * */
    public static String StringToInt(String str){
        try {
            int number = Integer.valueOf(str).intValue();
            DecimalFormat df=new DecimalFormat("0.00");
            String string =  df.format((float)number/100);
            return string;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 截取字符串后两位
     * */
    public static String stringSubString(String str){
        if (str.equals("")){
            return null;
        }
        String s = str;
        s = s.substring(0,s.length() - 2);
        return s;
    }
}
