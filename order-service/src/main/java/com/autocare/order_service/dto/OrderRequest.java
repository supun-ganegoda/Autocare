package com.autocare.order_service.dto;

public record OrderRequest(Long id, String orderNumber, String skuCode, double price, int quantity) {
}
