package com.usk.demo.service;

import com.usk.demo.dto.Request;
import com.usk.demo.dto.Response;
import com.usk.demo.entity.Delivary;
import com.usk.demo.exception.ResourceNotFoundException;
import com.usk.demo.repository.EcomRepository;

import org.apache.kafka.common.KafkaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class EcomServiceImpl implements EcomService {

	@Autowired
	EcomRepository ecomRepository;

/*
	@Autowired
	KafkaSender kafkaSender;
*/

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public Response updateDeliveryStatus(Request request) {
		Response response= new Response();
		try{
		Optional<Delivary>  delivery = ecomRepository.findById(request.getDeliveryid());

		if(delivery.isEmpty()){
			throw new ResourceNotFoundException("Delivery id does not exist!!!");
		}

		Query query = new Query(Criteria.where("id").is(request.getDeliveryid()));
		Update update = new Update().set("deliveryStatus", request.getDeliveryStatus());
		mongoTemplate.updateFirst(query, update, Delivary.class);

		response.setStatus("Success");
		response.setMessage("Delivery Status updated successfully!!");

		kafkaTemplate.send("deliveryApp","Order related to deliveryId: "+ request.getDeliveryid() + "is " + request.getDeliveryStatus());


		System.out.println("Order related to deliveryId: "+ request.getDeliveryid() + "is " + request.getDeliveryStatus());

		} catch (ResourceNotFoundException ex) {
			response.setStatus("Failure");
			response.setMessage(ex.getMessage());
		} catch (KafkaException ex) {
			response.setStatus("Failure");
			response.setMessage("Failed to send Kafka message.");
		} catch (Exception ex) {
			response.setStatus("Failure");
			response.setMessage("An unexpected error occurred.");
		}


		return response;
	}

	@Override
	public void addDeliveryDetails(Delivary delivery) {
		ecomRepository.save(delivery);
	}
}