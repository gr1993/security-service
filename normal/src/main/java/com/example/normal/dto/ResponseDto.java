package com.example.normal.dto;

import lombok.Data;

@Data
public class ResponseDto {

    public ResponseDto() {

    }

    public ResponseDto(boolean success) {
        this.success = success;
    }

    public ResponseDto(String msg) {
        this.msg = msg;
        this.success = true;
    }

    public ResponseDto(boolean success, String msg) {
        this.msg = msg;
        this.success = success;
    }

    private boolean success;
    private String msg;
    private Object data;
}
