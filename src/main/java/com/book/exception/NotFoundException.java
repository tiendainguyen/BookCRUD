package com.book.exception;

public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = 20L;
    private int status;
    private String code;
    private String message;
    public NotFoundException(){
        this.status = 404;
        this.code = "NotFoundException";
        this.message ="Không tìm thấy dữ liệu";
    }
}
