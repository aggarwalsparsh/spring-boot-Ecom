package com.usk.demo.dto;

public class Request {

private String deliveryid;
private String deliveryStatus;

    public String getDeliveryid() {
        return deliveryid;
    }

    public void setDeliveryid(String deliveryid) {
        this.deliveryid = deliveryid;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Request() {
    }

    public Request(String deliveryid, String deliveryStatus) {
        this.deliveryid = deliveryid;
        this.deliveryStatus = deliveryStatus;
    }
}
