package com.alb.demo.exceptions;

public class UnknownIdException extends IllegalArgumentException{

    public UnknownIdException(String s) {
        super(s);
    }
}
