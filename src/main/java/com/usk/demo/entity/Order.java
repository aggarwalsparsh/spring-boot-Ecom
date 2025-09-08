package com.usk.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document
public class Order {

	@Id
	private String orderId;
	private LocalDateTime orderDate;
	private double totalAmount;
	private String paymentStatus;
	private UserInfo user;
	
//	@OneToMany(mappedBy="order",cascade=CascadeType.ALL)
//	@JsonManagedReference
	private List<OrderItem> item=new ArrayList<>();

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public UserInfo getCustomer() {
		return user;
	}

	public void setCustomer(UserInfo customer) {
		this.user = customer;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public List<OrderItem> getItem() {
		return item;
	}

	public void setItem(List<OrderItem> item) {
		this.item = item;
	}
}
