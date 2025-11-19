package com.ecommerce.ecommerce.dto.statusResponse;

public class BlockStatus {
    private String message;
    private boolean status;

    public BlockStatus() {
    }

    public BlockStatus(String message, boolean status) {
        this.message = message;
        this.status = status;
    }

    public String getmessage() {
        return message;
    }

    public void setmessage(String massage) {
        this.message = massage;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
