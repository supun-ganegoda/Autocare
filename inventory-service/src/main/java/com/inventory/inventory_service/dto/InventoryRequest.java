package com.inventory.inventory_service.dto;

public record InventoryRequest(Long id, String skuCode, int quantity) {
}
