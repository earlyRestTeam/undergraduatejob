package com.lyx.undergraduatejob.utils;

public class APIResult implements java.io.Serializable {

//    随便测试
    public static void main(String[] args) {
//        int array[] = {31,28,31,30,31,30,31,31,30,31,30,31};
//        int sum =0;
//        for (int i = 0; i < array.length; i++) {
//            if(array[i] % 2 == 0){
//                sum+=(i+1)*2;
//            }else {
//                sum+=(i+1);
//            }
//        }
//        System.out.println(sum);
        int i = 15;
        switch(i){
            default:
                System.out.println("default");
                break;
            case 11:
                System.out.println("case11");

            case 12:
                System.out.println("case12");
                break;

        }
    }



    private String message;
    private boolean result;
    private int code = 200;
    private Object data;

    public APIResult() {
       this(false);
    }

    public APIResult(boolean result) {
        this(result,WebCode.NO_DATA_FOUND);

    }

    public APIResult( boolean result, int code) {
       this(null,result,code);
    }


    public APIResult(String message, boolean result, int code) {
       this(message,result,code,null);
    }


    public APIResult(String message, boolean result, int code, Object data) {
        if(!result && code == 200){
            code = WebCode.NO_DATA_FOUND;
        }
        this.message = message;
        this.result = result;
        this.code = code;
        this.data = data;
    }

    public APIResult(int code, String message,Object data) {
        this.message = message;
        this.result = true;
        this.code = code;
        this.data = data;
    }

    //成功200
    public static APIResult genSuccessApiResponse(String message){
        return genSuccessApiResponse(message,null);
    }

    public static APIResult genSuccessApiResponse(Object data){
        return genSuccessApiResponse(null,data);
    }
    public static APIResult genSuccessApiResponse(String message, Object data){
        return new APIResult(message,true,200,data);
    }



    //失败500
    public static APIResult genFailApiResponse500(String message){
        return genFailApiResponse500(message,null);
    }
    public static APIResult genFailApiResponse500(Object data){
        return  genFailApiResponse500(null,data);
    }

    public static APIResult genFailApiResponse500(String message,Object data){
        return new APIResult(message,false,500,data);
    }

    //失败400
    public static APIResult genFailApiResponse400(String message){
        return genFailApiResponse400(message,null);
    }
    public static APIResult genFailApiResponse400(Object data){
        return  genFailApiResponse400(null,data);
    }
    public static APIResult genFailApiResponse400(String message,Object data){
        return new APIResult(message,true,400,data);
    }

    //失败401

    public static APIResult genFailApiResponse401(String message){
        return genFailApiResponse401(message,null);
    }
    public static APIResult genFailApiResponse401(Object data){
        return  genFailApiResponse401(null,data);
    }
    public static APIResult genFailApiResponse401(String message,Object data){
        return new APIResult(message,true,401,data);
    }

    public static Object genFailApiResponse403(String message) {
        return new APIResult(message,true,406,message);
    }
    public static Object genNotLoginApiResponse(String message) {
        return new APIResult(message,true,403,message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
