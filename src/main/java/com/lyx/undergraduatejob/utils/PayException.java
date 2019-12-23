package com.lyx.undergraduatejob.utils;

public class PayException extends RuntimeException {
    public PayException(){
        super();
    }

    public PayException(String message) {
        super(message);
    }
}
