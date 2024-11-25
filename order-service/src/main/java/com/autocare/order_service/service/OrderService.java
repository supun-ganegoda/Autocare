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
        Order order = Mapper.mapOrderRequestToOrder((orderRequest));
        orderRepository.save(order);
        log.info("Order saved successful");
    }
}
