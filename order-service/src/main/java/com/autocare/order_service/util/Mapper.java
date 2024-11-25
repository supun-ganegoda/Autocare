package com.autocare.order_service.util;

import com.autocare.order_service.dto.OrderRequest;
import com.autocare.order_service.model.Order;

import java.util.UUID;

public class Mapper {
    public static Order mapOrderRequestToOrder(OrderRequest orderRequest){
        return new Order(orderRequest.id(), UUID.randomUUID().toString(), orderRequest.skuCode(), orderRequest.price(), orderRequest.quantity());
    }
}
