package com.autocare.order_service.service;

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
    public void makeOrder(OrderRequest orderRequest){
        /*
        before making the order, it need to be checked if the order quantity is available in the inventory,
        therefore it need to communicate with inventory service for getting the available quota.
        For inter-service communication, spring cloud openFeign
        */

        Order order = Mapper.mapOrderRequestToOrder((orderRequest));
        orderRepository.save(order);
        log.info("Order saved successful");
    }
}
