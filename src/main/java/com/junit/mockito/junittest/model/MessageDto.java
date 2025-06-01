package com.junit.mockito.junittest.model;

public class MessageDto {
    private String message;
    public String getMessage() {
        return message;
    }

    public MessageDto(String message) {
        this.message = message;
    }

    public MessageDto() {
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
