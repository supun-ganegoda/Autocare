package com.inventory.inventory_service.service;

import com.inventory.inventory_service.dto.InventoryRequest;
import com.inventory.inventory_service.model.Inventory;
import com.inventory.inventory_service.repository.InventoryRepository;
import com.inventory.inventory_service.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    public void addItem(InventoryRequest inventoryRequest){
        Inventory inventory = Mapper.mapInventoryReqestToInventory(inventoryRequest);
        inventoryRepository.save(inventory);
        log.info("Inventory item saved successful");
    }

    public boolean isInStock(String skuCode, Integer quantity){
        return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
    }
}
