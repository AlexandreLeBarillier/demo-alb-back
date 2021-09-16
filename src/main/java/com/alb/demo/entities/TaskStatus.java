package com.alb.demo.entities;

public enum TaskStatus {
    to_do(1),
    in_progress(2),
    to_review(3),
    done(4);


    private final int code;

    TaskStatus(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
    public static TaskStatus fromCode(final Integer code) {
        for(TaskStatus status: values()){
            if (status.code() == code){
                return status;
            }
        }
        return null;
    }
}
