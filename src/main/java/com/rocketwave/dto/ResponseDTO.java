package com.rocketwave.dto;

import java.util.List;

public class ResponseDTO {
    private Integer status;
    private String message;

    public ResponseDTO(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseDTO() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
