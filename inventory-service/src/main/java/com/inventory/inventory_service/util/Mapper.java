package com.inventory.inventory_service.util;

import com.inventory.inventory_service.dto.InventoryRequest;
import com.inventory.inventory_service.model.Inventory;

public class Mapper {
    public static Inventory mapInventoryReqestToInventory(InventoryRequest inventoryRequest){
        return new Inventory(inventoryRequest.id(), inventoryRequest.skuCode(), inventoryRequest.quantity());
    }
}
