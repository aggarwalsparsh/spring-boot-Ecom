package com.usk.demo;

import com.usk.demo.dto.Request;
import com.usk.demo.dto.Response;
import com.usk.demo.entity.Delivary;
import com.usk.demo.exception.ResourceNotFoundException;
import com.usk.demo.repository.EcomRepository;
import com.usk.demo.service.EcomService;
import com.usk.demo.service.EcomServiceImpl;
import org.apache.kafka.common.KafkaException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static com.mongodb.client.model.Filters.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EcomServiceTest {

    @InjectMocks
    private EcomServiceImpl deliveryService; // Replace with your actual service class name

    @Mock
    private EcomRepository ecomRepository;

    @Mock
    private MongoTemplate mongoTemplate;

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    public void testUpdateDeliveryStatus_Success() {
        // Arrange
        Request request = new Request();
        request.setDeliveryid("123");
        request.setDeliveryStatus("Delivered");

        Delivary delivary = new Delivary(); // Replace with actual constructor or builder
        Optional<Delivary> optionalDelivary = Optional.of(delivary);

        when(ecomRepository.findById("123")).thenReturn(optionalDelivary);

        // Act
        Response response = deliveryService.updateDeliveryStatus(request);

        // Assert
        assertEquals("Success", response.getStatus());
        assertEquals("Delivery Status updated successfully!!", response.getMessage());
    }

    @Test
    public void testUpdateDeliveryStatus_DeliveryNotFound() {

        Request request = new Request();
        request.setDeliveryid("nonExistingId");
        request.setDeliveryStatus("Delivered");

        when(ecomRepository.findById("nonExistingId")).thenReturn(Optional.empty());

        Response response = deliveryService.updateDeliveryStatus(request);

        assertEquals("Failure", response.getStatus());
        assertEquals("Delivery id does not exist!!!", response.getMessage());
    }

    @Test
    public void testUpdateDeliveryStatus_KafkaFailure() {
        Request request = new Request();
        request.setDeliveryid("validId");
        request.setDeliveryStatus("Delivered");


        Delivary mockDelivery = new Delivary();
        when(ecomRepository.findById("validId")).thenReturn(Optional.of(mockDelivery));
        doThrow(new KafkaException("Kafka error")).when(kafkaTemplate).send(anyString(), anyString());

        Response response = deliveryService.updateDeliveryStatus(request);

        assertEquals("Failure", response.getStatus());
        assertEquals("Failed to send Kafka message.", response.getMessage());
    }

}

