package com.usk.demo.controller;

import com.usk.demo.dto.Request;
import com.usk.demo.dto.Response;
import com.usk.demo.entity.Delivary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import com.usk.demo.service.EcomService;

@RestController
public class EcomController {
	
	@Autowired
	EcomService customerService;
	
	@Autowired
	  KafkaTemplate<String, String> kafkaTemplate1 ;

	@PostMapping("/addDeliveryDetails")
	public void addDeliveryDetails(@RequestBody Delivary delivery){
		 customerService.addDeliveryDetails(delivery);
	}

	@PutMapping("/updateDeliveryStatus")
	public Response UpdateStatus(@RequestBody Request request) {
   		return customerService.updateDeliveryStatus(request);
	}



}

