package com.usk.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "deliveries")
public class Delivary {
 
    @Id
    private String id;
 
    private Order order;

    private LocalDate expectedDate;

    private String deliveryStatus;

    public Delivary() {}
 

 
    public String getId() {

        return id;

    }
 
    public void setId(String id) {

        this.id = id;

    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public LocalDate getExpectedDate() {

        return expectedDate;

    }
 
    public void setExpectedDate(LocalDate expectedDate) {

        this.expectedDate = expectedDate;

    }

    public Delivary(String id, Order order, LocalDate expectedDate, String deliveryStatus) {
        this.id = id;
        this.order = order;
        this.expectedDate = expectedDate;
        this.deliveryStatus = deliveryStatus;
    }

    @Override
    public String toString() {
        return "Delivary{" +
                "id='" + id +
                ", order=" + order +
                ", expectedDate=" + expectedDate +
                '}';
    }
}

 