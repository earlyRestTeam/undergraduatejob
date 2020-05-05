package com.lyx.undergraduatejob.utils;

public class CodeUtil {

    public static String getNumeral(){
        String code = "";
        for (int i = 0; i < 6; i++) {
            code = code + (int)(Math.random() * 9);
        }
        return code;
    }

    public static void main(String[] args) {
        System.out.println(getNumeral());
    }

}
