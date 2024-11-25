package com.autocare.product_service.util;

import com.autocare.product_service.dto.ProductResponse;
import com.autocare.product_service.model.Product;

public class ProductMapper {
    public static ProductResponse mapToProductResponse(Product product){
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }
}
