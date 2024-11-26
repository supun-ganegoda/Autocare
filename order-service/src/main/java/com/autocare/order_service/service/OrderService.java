package com.autocare.order_service.service;

import com.autocare.order_service.client.InventoryClient;
import com.autocare.order_service.dto.OrderRequest;
import com.autocare.order_service.model.Order;
import com.autocare.order_service.repository.OrderRepository;
import com.autocare.order_service.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

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
        }else{
            throw new RuntimeException("The item with name "+orderRequest.skuCode()+" is not available");
        }
    }
}
