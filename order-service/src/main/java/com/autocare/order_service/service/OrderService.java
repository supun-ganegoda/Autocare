package com.autocare.order_service.service;

import com.autocare.order_service.client.InventoryClient;
import com.autocare.order_service.dto.OrderRequest;
import com.autocare.order_service.model.Order;
import com.autocare.order_service.order.event.OrderPlacedEvent;
import com.autocare.order_service.repository.OrderRepository;
import com.autocare.order_service.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacedEvent> orderPlaceKafkaTemplate;

    public void makeOrder(OrderRequest orderRequest) {
        /*
        before making the order, it need to be checked if the order quantity is available in the inventory,
        therefore it need to communicate with inventory service for getting the available quota.
        For inter-service communication, spring cloud openFeign
        */

        boolean inStock = inventoryClient.checkInStock(orderRequest.skuCode(), orderRequest.quantity());
        if (inStock) {
            Order order = Mapper.mapOrderRequestToOrder((orderRequest));
            orderRepository.save(order);
            log.info("Order saved successful");

            /*
            after placing the order, it needs to send the notification using the kafka message queue.
            below is the configuration of kafka message queue
            */
            OrderPlacedEvent placedOrder = new OrderPlacedEvent(orderRequest.id().toString(), "user@gmail.com", "user", "user");
            log.info("Start sending kafka topic");
            orderPlaceKafkaTemplate.send("Order placed", placedOrder);
            log.info("End sending kafka topic");

        }else{
            throw new RuntimeException("The item with name "+orderRequest.skuCode()+" is not available");
        }
    }
}
