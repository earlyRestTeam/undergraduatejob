package com.lyx.undergraduatejob.text;

public class example {
    public static String controller(){

        return "20100516";
    }
    public static String test(String s){
        System.out.println(s);
        return "asd";
    }
    public static void main(String[] args) {
//        String ww = controller();
//        ww.substring(0,4);

        String s = controller();
        try {

            s = s.substring(4,6);
        }catch (Exception e){
            throw new RuntimeException(e);
//            s = controller();
        }finally {
            test(s);
        }
    }
}
