package com.usk.demo.service;

import com.usk.demo.dto.Request;
import com.usk.demo.dto.Response;
import com.usk.demo.entity.*;

import java.util.List;


public interface EcomService {

	 Response updateDeliveryStatus(Request request);

	void addDeliveryDetails(Delivary delivery);
}
