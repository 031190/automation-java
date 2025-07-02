package com.testapi.pojo.petApiPojos;

public enum Status {
    AVAILABLE("available"),
    PENDING("pending"),
    SOLD("sold");

    public final String status;

    Status(String status) {
        this.status = status;
    }
}
