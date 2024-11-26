package com.inventory.inventory_service.controller;

import com.inventory.inventory_service.dto.InventoryRequest;
import com.inventory.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addItem(@RequestBody InventoryRequest inventoryRequest){
        inventoryService.addItem(inventoryRequest);
        return "Item added successful";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity){
        return inventoryService.isInStock(skuCode, quantity);
    }

}
