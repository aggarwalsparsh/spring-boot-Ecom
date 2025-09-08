package com.usk.demo.dto;

public class DelivaryDto {

	private String order;

	public DelivaryDto(String order) {
		this.order = order;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "DelivaryDto(" + order + ")";
	}
}
