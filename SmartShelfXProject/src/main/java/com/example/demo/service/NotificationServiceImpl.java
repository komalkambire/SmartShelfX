package com.example.demo.service;

import com.example.demo.entity.StockLevel;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Override
    public void notifyVendorLowStock(Long vendorId, StockLevel stockLevel) {
        System.out.println(
            "ALERT VENDOR " + vendorId +
            ": Low stock for product " +
            stockLevel.getProduct().getName() +
            " (Qty: " + stockLevel.getQuantity() + ")"
        );
    }

    @Override
    public void notifyWarehouseManager(String warehouse, StockLevel stockLevel) {
        System.out.println(
            "ALERT WAREHOUSE " + warehouse +
            ": Low stock for product " +
            stockLevel.getProduct().getName() +
            " (Qty: " + stockLevel.getQuantity() + ")"
        );
    }
}
