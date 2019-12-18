package com.lyx.undergraduatejob.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class UUIDUtils {
    public static String getTimerCode(String filename)
    {
        int begin = filename.lastIndexOf(".");
        String fix = filename.substring(begin, filename.length());
        System.out.println(fix);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String format = sdf.format(new Date());

        String name = format + Integer.toHexString(filename.hashCode()) + fix;


        return name;
    }
    //length用户要求产生字符串的长度
    public static String genStr(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}